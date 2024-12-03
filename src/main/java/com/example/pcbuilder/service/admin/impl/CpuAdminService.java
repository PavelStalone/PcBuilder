package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.product.base.BaseProcessorRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import org.springframework.stereotype.Service;

@Service
public class CpuAdminService extends BaseAdminService<CpuDto, Processor, BaseProcessorRepository> {

    protected CpuAdminService() {
        super(CpuDto.class, Processor.class);
    }
}
