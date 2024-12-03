package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.CaseDto;
import com.example.pcbuilder.domain.entity.product.Case;
import com.example.pcbuilder.domain.repository.BaseCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CaseAdminService extends BaseAdminService<CaseDto, Case, BaseCaseRepository> {

    protected CaseAdminService() {
        super(CaseDto.class, Case.class);
    }
}
