package com.example.pcbuilder.domain.repository;

import com.example.pcbuilder.domain.entity.Tag;
import com.example.pcbuilder.domain.repository.BaseRepository;
import com.example.pcbuilder.domain.repository.BaseTagRepository;
import com.example.pcbuilder.domain.repository.contract.TagRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepositoryImpl extends BaseRepository<BaseTagRepository> implements TagRepository {

    @Override
    public void createTag(Tag tag) {
        repository.save(tag);
    }

    @Override
    public List<Tag> getAvailableTags() {
        return repository.findAll();
    }
}
