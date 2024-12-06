package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.SSD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface SsdRepository {

    void deleteById(UUID id);

    Optional<SSD> getById(UUID id);

    SSD create(SSD entity);

    Page<SSD> getAllByFilter(Specification<SSD> spec, Pageable pageable);
}
