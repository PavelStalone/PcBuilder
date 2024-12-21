package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.PowerDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.PowerFilter;

import java.util.Optional;
import java.util.UUID;

public interface PowerService {

    UUID create(PowerDto dto);

    Optional<PowerDto> getById(UUID id);

    Optional<PowerDto> findMostPopular();

    void remove(UUID id);

    PageResult<PowerDto> getAllByFilter(PowerFilter filter);
}
