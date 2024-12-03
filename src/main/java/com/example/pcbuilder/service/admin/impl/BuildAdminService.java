package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.repository.build.base.BaseBuildRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import org.springframework.stereotype.Service;

@Service
public class BuildAdminService extends BaseAdminService<BuildDto, Build, BaseBuildRepository> {

    protected BuildAdminService() {
        super(BuildDto.class, Build.class);
    }
}
