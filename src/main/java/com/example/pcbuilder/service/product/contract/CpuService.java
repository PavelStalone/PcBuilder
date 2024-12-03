package com.example.pcbuilder.service.product.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CpuService {

    UUID create(CpuDto cpu);

    Optional<CpuDto> getById(UUID id);

    void remove(UUID id);

    Page<CpuDto> getAllByFilter(CpuFilter filter);
}
