package com.example.pcbuilder.domain.repository.other.contract;

import com.example.pcbuilder.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    void createUser(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
