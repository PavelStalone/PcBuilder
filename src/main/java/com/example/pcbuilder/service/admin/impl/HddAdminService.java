package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.HDD;
import com.example.pcbuilder.domain.repository.product.base.BaseHddRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.HddDto;
import org.springframework.stereotype.Service;

@Service
public class HddAdminService extends BaseAdminService<HddDto, HDD, BaseHddRepository> {

    protected HddAdminService() {
        super(HddDto.class, HDD.class);
    }
}
