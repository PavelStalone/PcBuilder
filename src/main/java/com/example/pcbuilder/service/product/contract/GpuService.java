package com.example.pcbuilder.service.product.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.product.GpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.GpuFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface GpuService {

    UUID create(GpuDto dto);

    Optional<GpuDto> getById(UUID id);

    void remove(UUID id);

    Page<GpuDto> getAllByFilter(GpuFilter filter);
}
