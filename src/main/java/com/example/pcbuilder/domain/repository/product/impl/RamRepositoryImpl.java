package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.RAM;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseRamRepository;
import com.example.pcbuilder.domain.repository.product.contract.RamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RamRepositoryImpl extends BaseRepository<BaseRamRepository> implements RamRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<RAM> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<RAM> findMostPopular() {
        return repository.findMostPopular();
    }

    @Override
    public RAM create(RAM entity) {
        return repository.save(entity);
    }

    @Override
    public Page<RAM> getAllByFilter(
            Specification<RAM> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
