package com.example.pcbuilder.service.build.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BuildService {

    Optional<BuildDto> getById(UUID id);

    UUID create(BuildDto dto);

    void remove(UUID id);

    Page<BuildDto> getAllByFilter(BuildFilter filter);
}
