package com.example.pcbuilder.service.product.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.product.MotherboardDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.MotherboardFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface MotherboardService {

    UUID create(MotherboardDto dto);

    Optional<MotherboardDto> getById(UUID id);

    void remove(UUID id);

    Page<MotherboardDto> getAllByFilter(MotherboardFilter filter);
}
