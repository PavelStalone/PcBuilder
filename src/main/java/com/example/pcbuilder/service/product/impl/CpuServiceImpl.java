package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.PageResult;
import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.product.contract.CpuRepository;
import com.example.pcbuilder.service.product.contract.CpuService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
import jakarta.persistence.criteria.Predicate;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CpuServiceImpl implements CpuService {

    private final CpuRepository repository;
    private final ValidationUtil validationUtil;
    private final TypeMap<CpuDto, Processor> fromDto = Mapper.createTypeMap(CpuDto.class, Processor.class);
    private final TypeMap<Processor, CpuDto> fromEntity = Mapper.createTypeMap(Processor.class, CpuDto.class);

    @Autowired
    public CpuServiceImpl(
            CpuRepository repository,
            ValidationUtil validationUtil
    ) {
        this.repository = repository;
        this.validationUtil = validationUtil;
    }

    @Override
    @CacheEvict(value = {"cpu", "builds"}, allEntries = true)
    public UUID create(CpuDto cpu) {
        Log.d("create called - dto: " + cpu);

        return repository.create(fromDto.map(cpu)).getId();
    }

    @Override
    @Cacheable("cpu")
    public Optional<CpuDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    public Optional<CpuDto> findMostPopular() {
        Log.d("findMostPopular called");

        return repository.findMostPopular()
                .map(fromEntity::map);
    }

    @Override
    @CacheEvict(value = {"cpu", "builds"}, allEntries = true)
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "cpu", key = "#filter.hashCode()")
    public PageResult<CpuDto> getAllByFilter(CpuFilter filter) {
        Log.d("getAllByFilter called - cpuFilter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<Processor> specification = (root, query, criteriaBuilder) -> {
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

            // region Year
            Optional.ofNullable(filter.yearLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("year"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.yearUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("year"),
                                    it
                            )
                    ));
            // endregion

            // region Core
            Optional.ofNullable(filter.coreLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("cores"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.coreUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("cores"),
                                    it
                            )
                    ));
            // endregion

            // region Frequency
            Optional.ofNullable(filter.freqLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.or(
                                    criteriaBuilder.greaterThanOrEqualTo(
                                            root.get("baseFreq"),
                                            it
                                    ),
                                    criteriaBuilder.greaterThanOrEqualTo(
                                            root.get("maxFreq"),
                                            it
                                    )
                            )
                    ));
            Optional.ofNullable(filter.freqUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.or(
                                    criteriaBuilder.lessThanOrEqualTo(
                                            root.get("baseFreq"),
                                            it
                                    ),
                                    criteriaBuilder.lessThanOrEqualTo(
                                            root.get("maxFreq"),
                                            it
                                    )
                            )
                    ));
            // endregion

            // region Thread
            Optional.ofNullable(filter.threadLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("threads"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.threadUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("threads"),
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
