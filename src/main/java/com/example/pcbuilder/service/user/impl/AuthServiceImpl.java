package com.example.pcbuilder.service.user.impl;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.domain.entity.Role;
import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.repository.other.contract.UserRepository;
import com.example.pcbuilder.domain.repository.other.contract.UserRoleRepository;
import com.example.pcbuilder.service.user.contract.AuthService;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserLoginDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRegistrationDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public AuthServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            UserRoleRepository userRoleRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void register(UserRegistrationDto form) {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new RuntimeException("passwords.match");
        }

        Optional<User> byEmail = userRepository.findByEmail(form.getEmail());

        if (byEmail.isPresent()) {
            throw new RuntimeException("email.used");
        }

        var roles = new ArrayList<Role>();

        roles.add(userRoleRepository.findRoleByRole(UserRoles.USER).orElseThrow());
        if (form.getAdmin()) roles.add(userRoleRepository.findRoleByRole(UserRoles.ADMIN).orElseThrow());

        User user = new User(
                Date.valueOf(LocalDate.now()),
                form.getEmail(),
                form.getUsername(),
                passwordEncoder.encode(form.getPassword()),
                form.getFullName()
        );

        user.setRoles(roles);

        userRepository.createUser(user);
    }

    @Override
    public void login(UserLoginDto form) {
        Optional<User> byEmail = this.userRepository.findByEmail(form.getEmail());

        if (byEmail.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(form.getPassword(), byEmail.get().getPassword())) {
            throw new RuntimeException("Password is wrong");
        }
    }

    @Override
    public UserDto getUser(String username) {
        var toDto = Mapper.createTypeMap(User.class, UserDto.class);

        return userRepository.findByUsername(username)
                .map(toDto::map)
                .orElseThrow(() -> new UsernameNotFoundException(username + " was not found!"));
    }
}
