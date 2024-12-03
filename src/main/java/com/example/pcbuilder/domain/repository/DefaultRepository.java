package com.example.pcbuilder.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface DefaultRepository<T> extends JpaRepository<T, UUID> {
}
