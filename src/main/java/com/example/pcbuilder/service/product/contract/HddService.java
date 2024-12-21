package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.HddDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.HddFilter;

import java.util.Optional;
import java.util.UUID;

public interface HddService {

    UUID create(HddDto dto);

    Optional<HddDto> getById(UUID id);

    Optional<HddDto> findMostPopular();

    void remove(UUID id);

    PageResult<HddDto> getAllByFilter(HddFilter filter);
}
