package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.BaseTagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagAdminService extends BaseAdminService<TagDto, Tag, BaseTagRepository> {

    protected TagAdminService() {
        super(TagDto.class, Tag.class);
    }
}
