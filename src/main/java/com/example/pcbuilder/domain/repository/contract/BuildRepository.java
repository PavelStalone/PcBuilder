package com.example.pcbuilder.domain.repository.contract;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BuildRepository {

    Optional<Build> getBuildById(UUID id);

    void createBuild(Build build);

    Page<Build> getAllBuildsByFilters(List<Tag> tags, Range<Integer> cost, Pageable pageable);
}
