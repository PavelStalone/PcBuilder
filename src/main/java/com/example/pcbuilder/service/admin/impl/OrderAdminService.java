package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.Order;
import com.example.pcbuilder.domain.repository.other.base.BaseOrderRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.other.OrderDto;
import org.springframework.stereotype.Service;

@Service
public class OrderAdminService extends BaseAdminService<OrderDto, Order, BaseOrderRepository> {

    protected OrderAdminService() {
        super(OrderDto.class, Order.class);
    }
}
