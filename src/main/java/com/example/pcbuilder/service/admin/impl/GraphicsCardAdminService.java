package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import com.example.pcbuilder.domain.repository.product.base.BaseGraphicsCardRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.GpuDto;
import org.springframework.stereotype.Service;

@Service
public class GraphicsCardAdminService extends BaseAdminService<GpuDto, GraphicsCard, BaseGraphicsCardRepository> {

    protected GraphicsCardAdminService() {
        super(GpuDto.class, GraphicsCard.class);
    }
}
