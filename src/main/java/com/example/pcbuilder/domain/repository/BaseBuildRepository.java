package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseBuildRepository extends DefaultRepository<Build> {

    @Query("Select b FROM Build b WHERE :tags MEMBER OF b.tags AND b.cost BETWEEN :minCost AND :maxCost")
    Page<Build> getAllBuildsByTagsAndCost(
            @Param(value = "tags") List<Tag> tags,
            @Param(value = "minCost") int minCost,
            @Param(value = "maxCost") int maxCost,
            Pageable pageable
    );
}
