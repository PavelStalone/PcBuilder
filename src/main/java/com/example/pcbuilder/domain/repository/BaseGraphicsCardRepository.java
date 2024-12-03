package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import com.example.pcbuilder.domain.entity.product.Processor;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseGraphicsCardRepository extends DefaultRepository<GraphicsCard> {
}
