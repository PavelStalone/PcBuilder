package com.example.pcbuilder.domain.repository.product.impl;

import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.product.base.BaseGraphicsCardRepository;
import com.example.pcbuilder.domain.repository.product.contract.GpuRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class GpuRepositoryImpl extends BaseRepository<BaseGraphicsCardRepository> implements GpuRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<GraphicsCard> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Optional<GraphicsCard> findMostPopular() {
        return repository.findMostPopular();
    }

    @Override
    public GraphicsCard create(GraphicsCard entity) {
        return repository.save(entity);
    }

    @Override
    public Page<GraphicsCard> getAllByFilter(
            Specification<GraphicsCard> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
