package com.example.pcbuilder.service.build.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.repository.build.contract.BuildRepository;
import com.example.pcbuilder.service.build.contract.BuildService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuildServiceImpl implements BuildService {

    private final BuildRepository repository;
    private final TypeMap<BuildDto, Build> fromDto = Mapper.createTypeMap(BuildDto.class, Build.class);
    private final TypeMap<Build, BuildDto> fromEntity = Mapper.createTypeMap(Build.class, BuildDto.class);

    @Autowired
    public BuildServiceImpl(BuildRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID create(BuildDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    public Optional<BuildDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    public Page<BuildDto> getAllByFilter(BuildFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<Build> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // region Owner
            Optional.ofNullable(filter.ownerName())
                    .filter((it) -> !it.isEmpty())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("owner").get("nickName")),
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

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sortByCost);

        return repository.getAllByFilter(specification, pageable).map(fromEntity::map);
    }
}
