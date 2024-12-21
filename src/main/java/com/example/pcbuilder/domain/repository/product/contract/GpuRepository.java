package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface GpuRepository {

    void deleteById(UUID id);

    Optional<GraphicsCard> getById(UUID id);

    Optional<GraphicsCard> findMostPopular();

    GraphicsCard create(GraphicsCard entity);

    Page<GraphicsCard> getAllByFilter(Specification<GraphicsCard> spec, Pageable pageable);
}
