package com.example.pcbuilder.service;

import com.example.pcbuilder.data.model.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.viewmodel.build.BuildFilter;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface BuildService {

    Optional<BuildDto> getBuildById(UUID id);

    void createBuild(BuildDto build);

    Page<BuildDto> getAllBuildsByFilter(BuildFilter filter);
}
