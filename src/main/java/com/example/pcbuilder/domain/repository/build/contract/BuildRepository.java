package com.example.pcbuilder.domain.repository.build.contract;

import com.example.pcbuilder.domain.entity.Build;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;
import java.util.UUID;

public interface BuildRepository {

    void deleteById(UUID id);

    Optional<Build> getById(UUID id);

    Build create(Build build);

    Page<Build> getAllByFilter(Specification<Build> spec, Pageable pageable);
}
