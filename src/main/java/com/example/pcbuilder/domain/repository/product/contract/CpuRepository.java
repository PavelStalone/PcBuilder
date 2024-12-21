package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.Processor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface CpuRepository {

    void deleteById(UUID id);

    Optional<Processor> getById(UUID id);

    Optional<Processor> findMostPopular();

    Processor create(Processor processor);

    Page<Processor> getAllByFilter(Specification<Processor> spec, Pageable pageable);

}
