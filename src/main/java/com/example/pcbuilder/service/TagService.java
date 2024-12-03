package com.example.pcbuilder.service;

import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;

import java.util.Set;

public interface TagService {

    Set<TagDto> getAvailableTags();
}
