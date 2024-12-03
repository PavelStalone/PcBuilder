package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.MotherboardDto;
import com.example.pcbuilder.domain.entity.product.Motherboard;
import com.example.pcbuilder.domain.repository.BaseMotherboardRepository;
import org.springframework.stereotype.Service;

@Service
public class MotherboardAdminService extends BaseAdminService<MotherboardDto, Motherboard, BaseMotherboardRepository> {

    protected MotherboardAdminService() {
        super(MotherboardDto.class, Motherboard.class);
    }
}
