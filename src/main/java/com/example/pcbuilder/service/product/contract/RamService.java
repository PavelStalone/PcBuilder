package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.RamDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.RamFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface RamService {

    UUID create(RamDto dto);

    Optional<RamDto> getById(UUID id);

    void remove(UUID id);

    PageResult<RamDto> getAllByFilter(RamFilter filter);
}
