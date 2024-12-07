package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.entity.product.Motherboard;
import com.example.pcbuilder.domain.repository.product.contract.MotherboardRepository;
import com.example.pcbuilder.service.product.contract.MotherboardService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.MotherboardDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.MotherboardFilter;
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
public class MotherboardServiceImpl implements MotherboardService {

    private final MotherboardRepository repository;
    private final TypeMap<MotherboardDto, Motherboard> fromDto = Mapper.createTypeMap(MotherboardDto.class, Motherboard.class);
    private final TypeMap<Motherboard, MotherboardDto> fromEntity = Mapper.createTypeMap(Motherboard.class, MotherboardDto.class);

    @Autowired
    public MotherboardServiceImpl(MotherboardRepository repository) {
        this.repository = repository;
    }

    @Override
    public UUID create(MotherboardDto dto) {
        Log.d("create called - dto: " + dto);

        return repository.create(fromDto.map(dto)).getId();
    }

    @Override
    public Optional<MotherboardDto> getById(UUID id) {
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
    public Page<MotherboardDto> getAllByFilter(MotherboardFilter filter) {
        Log.d("getAllByFilter called - filter: " + filter);

        var sortByCost = Sort.by("cost");

        if (filter.isDescCost() != null && filter.isDescCost()) sortByCost = sortByCost.descending();

        Specification<Motherboard> specification = (root, query, criteriaBuilder) -> {
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

            // region MemorySlots
            Optional.ofNullable(filter.memorySlotsLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("memorySlotsCounts"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.memorySlotsUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("memorySlotsCounts"),
                                    it
                            )
                    ));
            // endregion

            // region MemoryFrequency
            Optional.ofNullable(filter.maxMemoryFreqLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("maxMemoryFreq"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.maxMemoryFreqUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("maxMemoryFreq"),
                                    it
                            )
                    ));
            // endregion

            // region GpuSlots
            Optional.ofNullable(filter.graphicsSlotsLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("graphicSlotsCounts"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.graphicsSlotsUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("graphicSlotsCounts"),
                                    it
                            )
                    ));
            // endregion

            // region MemoryCapacity
            Optional.ofNullable(filter.maxMemoryCapacityLower())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.greaterThanOrEqualTo(
                                    root.get("maxMemoryCapacity"),
                                    it
                            )
                    ));
            Optional.ofNullable(filter.maxMemoryCapacityUpper())
                    .ifPresent((it) -> predicates.add(
                            criteriaBuilder.lessThanOrEqualTo(
                                    root.get("maxMemoryCapacity"),
                                    it
                            )
                    ));
            // endregion

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        var pageable = PageRequest.of(filter.page() - 1, filter.size(), sortByCost);

        return repository.getAllByFilter(specification, pageable).map(fromEntity::map);
    }
}
