package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.PowerUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface PowerRepository {

    void deleteById(UUID id);

    Optional<PowerUnit> getById(UUID id);

    PowerUnit create(PowerUnit entity);

    Page<PowerUnit> getAllByFilter(Specification<PowerUnit> spec, Pageable pageable);
}
