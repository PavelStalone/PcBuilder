package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.Case;
import com.example.pcbuilder.domain.repository.product.base.BaseCaseRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CaseDto;
import org.springframework.stereotype.Service;

@Service
public class CaseAdminService extends BaseAdminService<CaseDto, Case, BaseCaseRepository> {

    protected CaseAdminService() {
        super(CaseDto.class, Case.class);
    }
}
