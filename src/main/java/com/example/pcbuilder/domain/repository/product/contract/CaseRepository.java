package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.Case;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface CaseRepository {

    void deleteById(UUID id);

    Optional<Case> getById(UUID id);

    Case create(Case entity);

    Page<Case> getAllByFilter(Specification<Case> spec, Pageable pageable);
}
