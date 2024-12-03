package com.example.pcbuilder.domain.repository.product.contract;

import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface GpuRepository {

    Optional<GraphicsCard> getById(UUID id);

    UUID create(GraphicsCard gpu);

    Page<GraphicsCard> getAllByFilter(Pageable pageable);
}
