package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.RAM;
import com.example.pcbuilder.domain.repository.product.base.BaseRamRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.RamDto;
import org.springframework.stereotype.Service;

@Service
public class RamAdminService extends BaseAdminService<RamDto, RAM, BaseRamRepository> {

    protected RamAdminService() {
        super(RamDto.class, RAM.class);
    }
}
