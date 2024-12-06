package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.product.contract.CpuRepository;
import com.example.pcbuilder.service.product.contract.CpuService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
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
    public UUID create(CpuDto cpu) {
        return repository.create(fromDto.map(cpu)).getId();
    }

    @Override
    public Optional<CpuDto> getById(UUID id) {
        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    public void remove(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<CpuDto> getAllByFilter(CpuFilter filter) {
        Log.d("getAllByFilter called - cpuFilter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<Processor> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            Optional.ofNullable(filter.model())
                    .filter((it) -> !it.isEmpty())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.like(
                                    criteriaBuilder.lower(root.get("model")),
                                    "%" + it.toLowerCase() + "%"
                            )
                    ));

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

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sortByCost);

        return repository.getAllByFilter(specification, pageable).map(fromEntity::map);
    }
}
