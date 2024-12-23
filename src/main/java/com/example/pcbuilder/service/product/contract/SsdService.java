package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.SsdDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.SsdFilter;

import java.util.Optional;
import java.util.UUID;

public interface SsdService {

    UUID create(SsdDto dto);

    Optional<SsdDto> getById(UUID id);

    Optional<SsdDto> findMostPopular();

    void remove(UUID id);

    PageResult<SsdDto> getAllByFilter(SsdFilter filter);
}
