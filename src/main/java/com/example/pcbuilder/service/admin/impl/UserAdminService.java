package com.example.pcbuilder.service.admin.impl;

import edu.rutmiit.example.pcbuildercontracts.dto.other.UserDto;
import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.repository.other.base.BaseUserRepository;
import com.example.pcbuilder.service.admin.BaseAdminService;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService extends BaseAdminService<UserDto, User, BaseUserRepository> {

    protected UserAdminService() {
        super(UserDto.class, User.class);
    }
}
