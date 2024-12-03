package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CaseRepository {

    Optional<Case> getById(UUID id);

    UUID create(Case pcCase);

    Page<Case> getAllByFilter(Pageable pageable);
}
