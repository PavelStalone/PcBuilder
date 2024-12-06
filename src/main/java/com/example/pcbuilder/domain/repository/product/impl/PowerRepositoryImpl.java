package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BasePowerRepository;
import com.example.pcbuilder.domain.repository.product.contract.PowerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PowerRepositoryImpl extends BaseRepository<BasePowerRepository> implements PowerRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PowerUnit> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public PowerUnit create(PowerUnit entity) {
        return repository.save(entity);
    }

    @Override
    public Page<PowerUnit> getAllByFilter(
            Specification<PowerUnit> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
