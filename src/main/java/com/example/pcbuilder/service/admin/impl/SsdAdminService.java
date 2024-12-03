package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.SSD;
import com.example.pcbuilder.domain.repository.product.base.BaseSsdRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.SsdDto;
import org.springframework.stereotype.Service;

@Service
public class SsdAdminService extends BaseAdminService<SsdDto, SSD, BaseSsdRepository> {

    protected SsdAdminService() {
        super(SsdDto.class, SSD.class);
    }
}
