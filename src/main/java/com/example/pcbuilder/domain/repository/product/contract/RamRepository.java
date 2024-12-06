package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.RAM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface RamRepository {

    void deleteById(UUID id);

    Optional<RAM> getById(UUID id);

    RAM create(RAM entity);

    Page<RAM> getAllByFilter(Specification<RAM> spec, Pageable pageable);
}
