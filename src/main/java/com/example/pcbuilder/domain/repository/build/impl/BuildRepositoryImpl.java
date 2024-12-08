package com.example.pcbuilder.domain.repository.build.impl;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.build.base.BaseBuildRepository;
import com.example.pcbuilder.domain.repository.build.contract.BuildRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class BuildRepositoryImpl extends BaseRepository<BaseBuildRepository> implements BuildRepository {

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Build> getById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Build create(Build build) {
        return repository.save(build);
    }

    @Override
    public Page<Build> getAllByFilter(
            Specification<Build> spec,
            Pageable pageable
    ) {
        return repository.findAll(spec, pageable);
    }
}
