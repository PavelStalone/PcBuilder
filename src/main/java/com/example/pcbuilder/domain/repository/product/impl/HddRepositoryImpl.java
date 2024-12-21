package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.HDD;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseHddRepository;
import com.example.pcbuilder.domain.repository.product.contract.HddRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class HddRepositoryImpl extends BaseRepository<BaseHddRepository> implements HddRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<HDD> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<HDD> findMostPopular() {
        return repository.findMostPopular();
    }

    @Override
    public HDD create(HDD entity) {
        return repository.save(entity);
    }

    @Override
    public Page<HDD> getAllByFilter(
            Specification<HDD> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
