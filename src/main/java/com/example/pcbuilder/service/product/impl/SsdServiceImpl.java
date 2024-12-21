package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.PageResult;
import com.example.pcbuilder.domain.entity.product.SSD;
import com.example.pcbuilder.domain.repository.product.contract.SsdRepository;
import com.example.pcbuilder.service.product.contract.SsdService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.SsdDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.SsdFilter;
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
public class SsdServiceImpl implements SsdService {

    private final SsdRepository repository;
    private final TypeMap<SsdDto, SSD> fromDto = Mapper.createTypeMap(SsdDto.class, SSD.class);
    private final TypeMap<SSD, SsdDto> fromEntity = Mapper.createTypeMap(SSD.class, SsdDto.class);

    @Autowired
    public SsdServiceImpl(SsdRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = {"ssd", "builds"}, allEntries = true)
    public UUID create(SsdDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    @Cacheable("ssd")
    public Optional<SsdDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    public Optional<SsdDto> findMostPopular() {
        Log.d("findMostPopular called");

        return repository.findMostPopular()
                .map(fromEntity::map);
    }

    @Override
    @CacheEvict(value = {"ssd", "builds"}, allEntries = true)
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "ssd", key = "#filter.hashCode()")
    public PageResult<SsdDto> getAllByFilter(SsdFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<SSD> specification = (root, query, criteriaBuilder) -> {
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

            // region ReadSpeed
            Optional.ofNullable(filter.maxReadLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("maxReadSpeed"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.maxReadUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("maxReadSpeed"),
                                    it
                            )
                    ));
            // endregion

            // region WriteSpeed
            Optional.ofNullable(filter.maxWriteLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("maxWriteSpeed"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.maxWriteUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("maxWriteSpeed"),
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
