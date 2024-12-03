package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.GraphicsCardDto;
import com.example.pcbuilder.domain.entity.product.GraphicsCard;
import com.example.pcbuilder.domain.repository.BaseGraphicsCardRepository;
import org.springframework.stereotype.Service;

@Service
public class GraphicsCardAdminService extends BaseAdminService<GraphicsCardDto, GraphicsCard, BaseGraphicsCardRepository> {

    protected GraphicsCardAdminService() {
        super(GraphicsCardDto.class, GraphicsCard.class);
    }
}
