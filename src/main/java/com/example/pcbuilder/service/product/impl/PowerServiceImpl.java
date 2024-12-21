package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.PageResult;
import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.repository.product.contract.PowerRepository;
import com.example.pcbuilder.service.product.contract.PowerService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.PowerDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.PowerFilter;
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
public class PowerServiceImpl implements PowerService {

    private final PowerRepository repository;
    private final TypeMap<PowerDto, PowerUnit> fromDto = Mapper.createTypeMap(PowerDto.class, PowerUnit.class);
    private final TypeMap<PowerUnit, PowerDto> fromEntity = Mapper.createTypeMap(PowerUnit.class, PowerDto.class);

    @Autowired
    public PowerServiceImpl(PowerRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = {"power", "builds"}, allEntries = true)
    public UUID create(PowerDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    @Cacheable("power")
    public Optional<PowerDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    @Cacheable(value = "power", key = "'popular'")
    public Optional<PowerDto> findMostPopular() {
        Log.d("findMostPopular called");

        return repository.findMostPopular()
                .map(fromEntity::map);
    }

    @Override
    @CacheEvict(value = {"power", "builds"}, allEntries = true)
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "power", key = "#filter.hashCode()")
    public PageResult<PowerDto> getAllByFilter(PowerFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<PowerUnit> specification = (root, query, criteriaBuilder) -> {
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
                                    root.get("power"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.powerUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("power"),
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
