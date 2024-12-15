package com.example.pcbuilder.service.build.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;

import java.util.Optional;
import java.util.UUID;

public interface BuildService {

    Optional<BuildDto> getById(UUID id);

    UUID create(BuildDto dto);

    void remove(UUID id);

    PageResult<BuildDto> getAllByFilter(BuildFilter filter);
}
