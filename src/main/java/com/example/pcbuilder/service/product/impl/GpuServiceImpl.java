package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.PageResult;
import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import com.example.pcbuilder.domain.repository.product.contract.GpuRepository;
import com.example.pcbuilder.service.product.contract.GpuService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.GpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.GpuFilter;
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
public class GpuServiceImpl implements GpuService {

    private final GpuRepository repository;
    private final TypeMap<GpuDto, GraphicsCard> fromDto = Mapper.createTypeMap(GpuDto.class, GraphicsCard.class);
    private final TypeMap<GraphicsCard, GpuDto> fromEntity = Mapper.createTypeMap(GraphicsCard.class, GpuDto.class);

    @Autowired
    public GpuServiceImpl(GpuRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = {"gpu", "builds"}, allEntries = true)
    public UUID create(GpuDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    @Cacheable("gpu")
    public Optional<GpuDto> getById(UUID id) {
        Log.d("getById called - id: " + id);

        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    @Cacheable(value = "gpu", key = "'popular'")
    public Optional<GpuDto> findMostPopular() {
        Log.d("findMostPopular called");

        return repository.findMostPopular()
                .map(fromEntity::map);
    }

    @Override
    @CacheEvict(value = {"gpu", "builds"}, allEntries = true)
    public void remove(UUID id) {
        Log.d("remove called - id: " + id);

        repository.deleteById(id);
    }

    @Override
    @Cacheable(value = "gpu", key = "#filter.hashCode()")
    public PageResult<GpuDto> getAllByFilter(GpuFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<GraphicsCard> specification = (root, query, criteriaBuilder) -> {
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

            // region Frequency
            Optional.ofNullable(filter.freqLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("freq"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.freqUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("freq"),
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

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sortByCost);
        var page = repository.getAllByFilter(specification, pageable).map(fromEntity::map);

        return new PageResult<>(page.toList(), page.getTotalPages());
    }
}
