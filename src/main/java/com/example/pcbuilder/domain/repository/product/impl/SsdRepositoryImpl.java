package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.SSD;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseSsdRepository;
import com.example.pcbuilder.domain.repository.product.contract.SsdRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SsdRepositoryImpl extends BaseRepository<BaseSsdRepository> implements SsdRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<SSD> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public SSD create(SSD entity) {
        return repository.save(entity);
    }

    @Override
    public Page<SSD> getAllByFilter(
            Specification<SSD> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
