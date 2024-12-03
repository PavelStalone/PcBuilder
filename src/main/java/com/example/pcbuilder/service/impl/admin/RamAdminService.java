package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.RamDto;
import com.example.pcbuilder.domain.entity.product.RAM;
import com.example.pcbuilder.domain.repository.BaseRamRepository;
import org.springframework.stereotype.Service;

@Service
public class RamAdminService extends BaseAdminService<RamDto, RAM, BaseRamRepository> {

    protected RamAdminService() {
        super(RamDto.class, RAM.class);
    }
}
