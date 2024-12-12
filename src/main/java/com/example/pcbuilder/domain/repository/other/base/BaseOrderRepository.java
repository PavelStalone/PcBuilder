package com.example.pcbuilder.domain.repository.other.base;

import com.example.pcbuilder.domain.entity.Order;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseOrderRepository extends DefaultRepository<Order> {
}
