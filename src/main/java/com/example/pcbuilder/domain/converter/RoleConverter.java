package com.example.pcbuilder.domain.converter;

import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<UserRoles, Integer> {

    @Override
    public Integer convertToDatabaseColumn(UserRoles userRoles) {
        return userRoles.getValue();
    }

    @Override
    public UserRoles convertToEntityAttribute(Integer integer) {
        return UserRoles.fromValue(integer);
    }
}
