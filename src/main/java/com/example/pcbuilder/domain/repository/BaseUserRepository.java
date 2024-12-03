package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.entity.product.Case;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository extends DefaultRepository<User> {
}
