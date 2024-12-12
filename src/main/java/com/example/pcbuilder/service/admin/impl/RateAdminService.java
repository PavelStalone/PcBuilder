package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.Rate;
import com.example.pcbuilder.domain.repository.other.base.BaseRateRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.other.RateDto;
import org.springframework.stereotype.Service;

@Service
public class RateAdminService extends BaseAdminService<RateDto, Rate, BaseRateRepository> {

    protected RateAdminService() {
        super(RateDto.class, Rate.class);
    }
}
