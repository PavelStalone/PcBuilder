package com.example.pcbuilder.service.product.contract;

import com.example.pcbuilder.domain.PageResult;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CaseDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CaseFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CaseService {

    UUID create(CaseDto dto);

    Optional<CaseDto> getById(UUID id);

    Optional<CaseDto> findMostPopular();

    void remove(UUID id);

    PageResult<CaseDto> getAllByFilter(CaseFilter filter);
}
