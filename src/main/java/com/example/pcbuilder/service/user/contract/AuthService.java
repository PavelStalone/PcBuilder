package com.example.pcbuilder.service.user.contract;

import edu.rutmiit.example.pcbuildercontracts.dto.user.UserDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserLoginDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRegistrationDto;

public interface AuthService {

    void register(UserRegistrationDto form);

    void login(UserLoginDto form);

    UserDto getUser(String username);
}
