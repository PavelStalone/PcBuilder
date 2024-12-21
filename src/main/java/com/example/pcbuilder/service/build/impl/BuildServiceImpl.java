package com.example.pcbuilder.service.build.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.PageResult;
import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.repository.build.contract.BuildRepository;
import com.example.pcbuilder.service.build.contract.BuildService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableCaching
public class BuildServiceImpl implements BuildService {

    private final BuildRepository repository;
    private final TypeMap<BuildDto, Build> fromDto = Mapper.createTypeMap(BuildDto.class, Build.class);
    private final TypeMap<Build, BuildDto> fromEntity = Mapper.createTypeMap(Build.class, BuildDto.class);

    @Autowired
    public BuildServiceImpl(BuildRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = "builds", allEntries = true)
    public UUID create(BuildDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    @Cacheable("builds")
    public Optional<BuildDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    @CacheEvict(value = "builds", allEntries = true)
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "builds", key = "#filter.hashCode()")
    public PageResult<BuildDto> getAllByFilter(BuildFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        Sort sort = Sort.by("cost");
        if (filter.sortType() != null) {
            switch (filter.sortType()) {
                case PRICE_ASC, PRICE_DESC -> sort = Sort.by("cost");
                case RATE_ASC, RATE_DESC -> sort = Sort.by("averageRate");
            }
            switch (filter.sortType()) {
                case RATE_DESC, PRICE_DESC -> sort = sort.descending();
            }
        }

        Specification<Build> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // region Owner
            Optional.ofNullable(filter.ownerName())
                    .filter((it) -> !it.isEmpty())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("owner").get("username")),
                                    "%" + it.toLowerCase() + "%"
                            )
                    ));
            // endregion

            // region Rate
            Optional.ofNullable(filter.rateLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("averageRate"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.rateUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("averageRate"),
                                    it
                            )
                    ));
            // endregion

            // region Cost
            Optional.ofNullable(filter.costLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("cost"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.costUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("cost"),
                                    it
                            )
                    ));
            // endregion

            // region Tag
            Optional.ofNullable(filter.tags())
                    .filter((it) -> !it.isEmpty())
                    .ifPresent((it) -> {
                        predicates.add(root.get("tags").get("id").in(it));

                        query.groupBy(root.get("id"));
                        query.having(criteriaBuilder.equal(
                                criteriaBuilder.count(root.get("id")),
                                (long) it.size()
                        ));
                    });
            // endregion

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sort);
        var page = repository.getAllByFilter(specification, pageable).map(fromEntity::map);

        return new PageResult<>(page.toList(), page.getTotalPages());
    }
}
