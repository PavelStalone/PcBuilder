package com.example.pcbuilder.domain.repository.build.base;

import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseBuildRepository extends DefaultRepository<Build> {
}
