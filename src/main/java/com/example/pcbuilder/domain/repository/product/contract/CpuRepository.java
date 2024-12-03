package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.Processor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface CpuRepository {

    Optional<Processor> getById(UUID id);

    Processor create(Processor processor);

    Page<Processor> getAllByFilter(Pageable pageable);
}
