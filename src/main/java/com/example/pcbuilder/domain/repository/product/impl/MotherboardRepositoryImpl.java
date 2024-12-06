package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.Motherboard;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseMotherboardRepository;
import com.example.pcbuilder.domain.repository.product.contract.MotherboardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class MotherboardRepositoryImpl extends BaseRepository<BaseMotherboardRepository> implements MotherboardRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Motherboard> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Motherboard create(Motherboard entity) {
        return repository.save(entity);
    }

    @Override
    public Page<Motherboard> getAllByFilter(
            Specification<Motherboard> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
