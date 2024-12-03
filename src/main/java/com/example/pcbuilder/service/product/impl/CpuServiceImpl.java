package com.example.pcbuilder.service.product.impl;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.product.contract.CpuRepository;
import com.example.pcbuilder.service.product.contract.CpuService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        var entity = fromDto.map(cpu);

        return repository.create(entity).getId();
    }

    @Override
    public Optional<CpuDto> getById(UUID id) {
        return repository.getById(id)
                .map(fromEntity::map);
    }

    @Override
    public void remove(UUID id) {
        // TODO: add remove cpu - shoplikpavel
    }

    @Override
    public Page<CpuDto> getAllByFilter(CpuFilter filter) {
        // TODO: change sorting settings - shoplikpavel
        var pageable = PageRequest.of(filter.page() - 1, filter.size(), Sort.by("cost"));

        return repository.getAllByFilter(pageable).map(fromEntity::map);
    }
}
