package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.PageResult;
import com.example.pcbuilder.domain.entity.product.HDD;
import com.example.pcbuilder.domain.repository.product.contract.HddRepository;
import com.example.pcbuilder.service.product.contract.HddService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.HddDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.HddFilter;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
public class HddServiceImpl implements HddService {

    private final HddRepository repository;
    private final TypeMap<HddDto, HDD> fromDto = Mapper.createTypeMap(HddDto.class, HDD.class);
    private final TypeMap<HDD, HddDto> fromEntity = Mapper.createTypeMap(HDD.class, HddDto.class);

    @Autowired
    public HddServiceImpl(HddRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = "hdd", allEntries = true)
    public UUID create(HddDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    @Cacheable("hdd")
    public Optional<HddDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    @CacheEvict(value = {"hdd", "builds"}, allEntries = true)
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "hdd", key = "#filter.hashCode()")
    public PageResult<HddDto> getAllByFilter(HddFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<HDD> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // region Model
            Optional.ofNullable(filter.model())
                    .filter((it) -> !it.isEmpty())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("model")),
                                    "%" + it.toLowerCase() + "%"
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

            // region Power
            Optional.ofNullable(filter.powerLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("maxPower"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.powerUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("maxPower"),
                                    it
                            )
                    ));
            // endregion

            // region Memory
            Optional.ofNullable(filter.memoryLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("memoryCapacity"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.memoryUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("memoryCapacity"),
                                    it
                            )
                    ));
            // endregion

            // region Rotation
            Optional.ofNullable(filter.rotationLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("rotationSpeed"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.rotationUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("rotationSpeed"),
                                    it
                            )
                    ));
            // endregion

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sortByCost);
        var page = repository.getAllByFilter(specification, pageable).map(fromEntity::map);

        return new PageResult<>(page.toList(), page.getTotalPages());
    }
}
