package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.entity.product.Case;
import com.example.pcbuilder.domain.repository.product.contract.CaseRepository;
import com.example.pcbuilder.service.product.contract.CaseService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CaseDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CaseFilter;
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
public class CaseServiceImpl implements CaseService {

    private final CaseRepository repository;
    private final TypeMap<CaseDto, Case> fromDto = Mapper.createTypeMap(CaseDto.class, Case.class);
    private final TypeMap<Case, CaseDto> fromEntity = Mapper.createTypeMap(Case.class, CaseDto.class);

    @Autowired
    public CaseServiceImpl(CaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID create(CaseDto dto) {
        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    public Optional<CaseDto> getById(UUID id) {
        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    public void remove(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Page<CaseDto> getAllByFilter(CaseFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<Case> specification = (root, query, criteriaBuilder) -> {
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

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sortByCost);

        return repository.getAllByFilter(specification, pageable).map(fromEntity::map);
    }
}
