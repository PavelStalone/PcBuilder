package com.example.pcbuilder.service.product.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.product.CaseDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CaseFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface CaseService {

    UUID create(CaseDto dto);

    Optional<CaseDto> getById(UUID id);

    void remove(UUID id);

    Page<CaseDto> getAllByFilter(CaseFilter filter);
}
