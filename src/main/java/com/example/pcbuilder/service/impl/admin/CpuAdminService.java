package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.product.CpuDto;
import com.example.pcbuilder.domain.entity.product.Processor;
import com.example.pcbuilder.domain.repository.BaseProcessorRepository;
import org.springframework.stereotype.Service;

@Service
public class CpuAdminService extends BaseAdminService<CpuDto, Processor, BaseProcessorRepository> {

    protected CpuAdminService() {
        super(CpuDto.class, Processor.class);
    }
}
