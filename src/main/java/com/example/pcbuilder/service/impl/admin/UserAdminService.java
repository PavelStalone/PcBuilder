package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.data.model.UserDto;
import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.repository.BaseUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserAdminService extends BaseAdminService<UserDto, User, BaseUserRepository> {

    protected UserAdminService() {
        super(UserDto.class, User.class);
    }
}
