package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.PowerDto;
import com.example.pcbuilder.domain.entity.product.PowerUnit;
import com.example.pcbuilder.domain.repository.BasePowerRepository;
import org.springframework.stereotype.Service;

@Service
public class PowerAdminService extends BaseAdminService<PowerDto, PowerUnit, BasePowerRepository> {

    protected PowerAdminService() {
        super(PowerDto.class, PowerUnit.class);
    }
}
