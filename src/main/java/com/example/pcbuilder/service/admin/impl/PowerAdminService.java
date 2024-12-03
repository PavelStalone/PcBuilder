package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.repository.product.base.BasePowerRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.PowerDto;
import org.springframework.stereotype.Service;

@Service
public class PowerAdminService extends BaseAdminService<PowerDto, PowerUnit, BasePowerRepository> {

    protected PowerAdminService() {
        super(PowerDto.class, PowerUnit.class);
    }
}
