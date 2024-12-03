package com.example.pcbuilder.service.build.impl;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.build.contract.BuildRepository;
import com.example.pcbuilder.service.build.contract.BuildService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BuildServiceImpl implements BuildService {

    private final BuildRepository repository;
    private final ValidationUtil validationUtil;

    @Autowired
    public BuildServiceImpl(BuildRepository repository, ValidationUtil validationUtil) {
        this.repository = repository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Optional<BuildDto> getById(UUID id) {
        return repository
                .getById(id)
                .map((it) -> Mapper.createTypeMap(Build.class, BuildDto.class).map(it));
    }

    @Override
    public void create(BuildDto build) {
        repository.create(Mapper.createTypeMap(BuildDto.class, Build.class).map(build));
    }

    @Override
    public Page<BuildDto> getAllByFilter(BuildFilter filter) {
        var tags = filter.tags()
                .stream()
                .map((it) -> Mapper.createTypeMap(TagDto.class, Tag.class).map(it))
                .toList();
        var costRange = filter.priceRange();
        Pageable pageable = PageRequest.of(filter.page() - 1, filter.size(), Sort.by("cost"));

        return repository.getAllByFilter(tags, costRange, pageable)
                .map((it) -> Mapper.createTypeMap(Build.class, BuildDto.class).map(it));
    }
}
