package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.Case;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseCaseRepository;
import com.example.pcbuilder.domain.repository.product.contract.CaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class CaseRepositoryImpl extends BaseRepository<BaseCaseRepository> implements CaseRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Case> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Case> findMostPopular() {
        return repository.findMostPopular();
    }

    @Override
    public Case create(Case entity) {
        return repository.save(entity);
    }

    @Override
    public Page<Case> getAllByFilter(
            Specification<Case> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
