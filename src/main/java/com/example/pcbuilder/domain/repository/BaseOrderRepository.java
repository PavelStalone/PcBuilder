package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseOrderRepository {

    List<Order> findAll();
}
