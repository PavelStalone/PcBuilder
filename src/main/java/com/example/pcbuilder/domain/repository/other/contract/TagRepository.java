package com.example.pcbuilder.domain.repository.other.contract;

import com.example.pcbuilder.domain.entity.Tag;

import java.util.List;
import java.util.Set;

public interface TagRepository {

    void createTag(Tag tag);

    List<Tag> getAvailableTags();
}
