package com.example.pcbuilder.service;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.other.contract.TagRepository;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

@Service
@EnableCaching
public class TagServiceImpl implements TagService {

    private final TagRepository repository;
    private final ValidationUtil validationUtil;

    @Autowired
    public TagServiceImpl(TagRepository repository, ValidationUtil validationUtil) {
        this.repository = repository;
        this.validationUtil = validationUtil;
    }

    @Override
    @Cacheable("tags")
    public Set<TagDto> getAvailableTags() {
        Log.d("getAvailableTags called");

        return new LinkedHashSet<>(repository.getAvailableTags()
                .stream()
                .map((it) -> Mapper.createTypeMap(Tag.class, TagDto.class).map(it))
                .sorted(Comparator.comparing(TagDto::getName))
                .toList());
    }
}
