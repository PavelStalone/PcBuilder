package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.RamDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.RamFilter;

import java.util.Optional;
import java.util.UUID;

public interface RamService {

    UUID create(RamDto dto);

    Optional<RamDto> getById(UUID id);

    Optional<RamDto> findMostPopular();

    void remove(UUID id);

    PageResult<RamDto> getAllByFilter(RamFilter filter);
}
