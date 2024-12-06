package com.example.pcbuilder.service.admin.impl;

import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.other.base.BaseTagRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import org.springframework.stereotype.Service;

@Service
public class TagAdminService extends BaseAdminService<TagDto, Tag, BaseTagRepository> {

    protected TagAdminService() {
        super(TagDto.class, Tag.class);
    }
}
