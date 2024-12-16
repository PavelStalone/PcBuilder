package com.example.pcbuilder.domain.repository.other.impl;

import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.other.base.BaseUserRepository;
import com.example.pcbuilder.domain.repository.other.contract.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl extends BaseRepository<BaseUserRepository> implements UserRepository {

    @Override
    public void createUser(User user) {
        repository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
