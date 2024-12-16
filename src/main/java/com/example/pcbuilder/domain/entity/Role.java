package com.example.pcbuilder.domain.entity;

import com.example.pcbuilder.domain.converter.RoleConverter;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity {

    private UserRoles role;

    protected Role() {
    }

    public Role(UserRoles role) {
        this.role = role;
    }

    @Column(name = "role", unique = true)
    @Convert(converter = RoleConverter.class)
    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }
}
