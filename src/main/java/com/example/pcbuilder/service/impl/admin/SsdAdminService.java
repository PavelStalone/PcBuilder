package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.SsdDto;
import com.example.pcbuilder.domain.entity.product.SSD;
import com.example.pcbuilder.domain.repository.BaseSsdRepository;
import org.springframework.stereotype.Service;

@Service
public class SsdAdminService extends BaseAdminService<SsdDto, SSD, BaseSsdRepository> {

    protected SsdAdminService() {
        super(SsdDto.class, SSD.class);
    }
}
