package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.HDD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface HddRepository {

    void deleteById(UUID id);

    Optional<HDD> getById(UUID id);

    HDD create(HDD entity);

    Page<HDD> getAllByFilter(Specification<HDD> spec, Pageable pageable);
}
