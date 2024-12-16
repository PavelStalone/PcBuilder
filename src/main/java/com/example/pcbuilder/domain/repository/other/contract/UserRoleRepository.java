package com.example.pcbuilder.domain.repository.other.contract;

import com.example.pcbuilder.domain.entity.Role;
import com.example.pcbuilder.domain.entity.User;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;

import java.util.Optional;

public interface UserRoleRepository {

    long count();
    void save(Role role);
    Optional<Role> findRoleByRole(UserRoles role);
}
