package com.example.pcbuilder.service;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.contract.TagRepository;
import com.example.pcbuilder.service.TagService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {

    private final TagRepository repository;
    private final ValidationUtil validationUtil;

    @Autowired
    public TagServiceImpl(TagRepository repository, ValidationUtil validationUtil) {
        this.repository = repository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Set<TagDto> getAvailableTags() {
        return repository.getAvailableTags()
                .stream()
                .map((it) -> Mapper.createTypeMap(Tag.class, TagDto.class).map(it))
                .collect(Collectors.toSet());
    }
}
