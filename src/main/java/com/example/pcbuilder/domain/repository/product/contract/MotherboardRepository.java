package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.Motherboard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface MotherboardRepository {

    void deleteById(UUID id);

    Optional<Motherboard> getById(UUID id);

    Motherboard create(Motherboard entity);

    Page<Motherboard> getAllByFilter(Specification<Motherboard> spec, Pageable pageable);
}
