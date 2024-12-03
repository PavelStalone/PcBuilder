package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.BaseBuildRepository;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.contract.BuildRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class BuildRepositoryImpl extends BaseRepository<BaseBuildRepository> implements BuildRepository {

    @Override
    public Optional<Build> getBuildById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public void createBuild(Build build) {
        repository.save(build);
    }

    @Override
    public Page<Build> getAllBuildsByFilters(List<Tag> tags, Range<Integer> cost, Pageable pageable) {
        return repository.getAllBuildsByTagsAndCost(
                tags,
                cost.getLowerBound().getValue().get(),
                cost.getUpperBound().getValue().get(),
                pageable
        );
    }
}
