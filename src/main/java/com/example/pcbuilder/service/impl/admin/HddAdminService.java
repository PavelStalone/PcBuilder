package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.HddDto;
import com.example.pcbuilder.domain.entity.product.HDD;
import com.example.pcbuilder.domain.repository.BaseHddRepository;
import org.springframework.stereotype.Service;

@Service
public class HddAdminService extends BaseAdminService<HddDto, HDD, BaseHddRepository> {

    protected HddAdminService() {
        super(HddDto.class, HDD.class);
    }
}
