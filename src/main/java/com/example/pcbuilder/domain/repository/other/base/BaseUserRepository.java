package com.example.pcbuilder.domain.repository.other.base;

import com.example.pcbuilder.domain.entity.User;
import com.example.pcbuilder.domain.entity.product.Case;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseUserRepository extends DefaultRepository<User> {
}
