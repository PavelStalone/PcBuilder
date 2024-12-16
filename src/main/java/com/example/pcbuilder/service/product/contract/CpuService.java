package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;

import java.util.Optional;
import java.util.UUID;

public interface CpuService {

    UUID create(CpuDto cpu);

    Optional<CpuDto> getById(UUID id);

    void remove(UUID id);

    PageResult<CpuDto> getAllByFilter(CpuFilter filter);
}
