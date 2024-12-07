package com.example.pcbuilder.service.product.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.product.PowerDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.PowerFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface PowerService {

    UUID create(PowerDto dto);

    Optional<PowerDto> getById(UUID id);

    void remove(UUID id);

    Page<PowerDto> getAllByFilter(PowerFilter filter);
}
