package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.repository.BaseBuildRepository;
import org.springframework.stereotype.Service;

@Service
public class BuildAdminService extends BaseAdminService<BuildDto, Build, BaseBuildRepository> {

    protected BuildAdminService() {
        super(BuildDto.class, Build.class);
    }
}
