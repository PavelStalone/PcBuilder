package com.example.pcbuilder.domain.repository.contract;

import com.example.pcbuilder.domain.entity.Order;

public interface OrderRepository {

    void createOrder(Order order);
}