package com.example.pcbuilder.domain.repository.other.impl;

import com.example.pcbuilder.domain.entity.Role;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.other.base.BaseUserRoleRepository;
import com.example.pcbuilder.domain.repository.other.contract.UserRoleRepository;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRoleRepositoryImpl extends BaseRepository<BaseUserRoleRepository> implements UserRoleRepository {

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void save(Role role) {
        repository.save(role);
    }

    @Override
    public Optional<Role> findRoleByRole(UserRoles role) {
        return repository.findRoleByRole(role);
    }
}
