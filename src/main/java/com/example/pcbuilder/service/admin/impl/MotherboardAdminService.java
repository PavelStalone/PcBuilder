package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.Motherboard;
import com.example.pcbuilder.domain.repository.product.base.BaseMotherboardRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.MotherboardDto;
import org.springframework.stereotype.Service;

@Service
public class MotherboardAdminService extends BaseAdminService<MotherboardDto, Motherboard, BaseMotherboardRepository> {

    protected MotherboardAdminService() {
        super(MotherboardDto.class, Motherboard.class);
    }
}
