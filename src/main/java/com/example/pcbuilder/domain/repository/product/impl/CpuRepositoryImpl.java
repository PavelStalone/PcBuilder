package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseProcessorRepository;
import com.example.pcbuilder.domain.repository.product.contract.CpuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CpuRepositoryImpl extends BaseRepository<BaseProcessorRepository> implements CpuRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Processor> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Processor> findMostPopular() {
        return repository.findMostPopular();
    }

    @Override
    public Processor create(Processor processor) {
        return repository.save(processor);
    }

    @Override
    public Page<Processor> getAllByFilter(
            Specification<Processor> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
