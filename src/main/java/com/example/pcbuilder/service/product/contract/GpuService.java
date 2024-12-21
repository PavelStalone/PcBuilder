package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.GpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.GpuFilter;

import java.util.Optional;
import java.util.UUID;

public interface GpuService {

    UUID create(GpuDto dto);

    Optional<GpuDto> getById(UUID id);

    Optional<GpuDto> findMostPopular();

    void remove(UUID id);

    PageResult<GpuDto> getAllByFilter(GpuFilter filter);
}
