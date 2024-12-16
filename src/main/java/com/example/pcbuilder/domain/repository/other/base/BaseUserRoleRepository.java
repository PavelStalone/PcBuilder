package com.example.pcbuilder.domain.repository.other.base;

import com.example.pcbuilder.domain.entity.Role;
import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BaseUserRoleRepository extends DefaultRepository<Role> {

    Optional<Role> findRoleByRole(UserRoles role);
}
