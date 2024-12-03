package com.example.pcbuilder.service.impl.admin;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import com.example.pcbuilder.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseAdminService<D, E, R extends DefaultRepository<E>> implements AdminService<D> {

    @Autowired
    protected R adminRepository;

    @Autowired
    protected ValidationUtil validationUtil;

    private final Class<D> dtoSource;
    private final Class<E> entitySource;

    protected BaseAdminService(Class<D> dtoSource, Class<E> entitySource) {
        this.dtoSource = dtoSource;
        this.entitySource = entitySource;
    }

    @Override
    public void addItem(D item) {
        if (!validationUtil.checkItem(item)) return;

        var mappedItem = Mapper.createTypeMap(dtoSource, entitySource).map(item);

        adminRepository.save(mappedItem);
    }

    @Override
    public void addAll(List<D> items) {
        if (items.stream().noneMatch(validationUtil::checkItem)) return;

        var typeMap = Mapper.createTypeMap(dtoSource, entitySource);
        adminRepository.saveAll(items.stream().map(typeMap::map).toList());
    }

    @Override
    public void removeItem(D item) {
        if (!validationUtil.checkItem(item)) return;

        var mappedItem = Mapper.createTypeMap(dtoSource, entitySource).map(item);

        adminRepository.delete(mappedItem);
    }

    @Override
    public long countsItems() {
        return adminRepository.count();
    }
}
