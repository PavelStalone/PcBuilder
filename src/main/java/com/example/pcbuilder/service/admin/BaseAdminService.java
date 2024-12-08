package com.example.pcbuilder.service.admin;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.common.validation.ValidationUtil;
import com.example.pcbuilder.domain.repository.DefaultRepository;
import com.example.pcbuilder.service.admin.contract.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

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
    public D addItem(D item) {
        if (!validationUtil.checkItem(item)) throw new RuntimeException("Error when check dto");

        var mappedItem = Mapper.createTypeMap(dtoSource, entitySource).map(item);

        return Mapper.createTypeMap(entitySource, dtoSource).map(adminRepository.save(mappedItem));
    }

    @Override
    public List<D> addAll(List<D> items) {
        if (items.stream().noneMatch(validationUtil::checkItem)) throw new RuntimeException("Error when check dto");

        var typeMapEntity = Mapper.createTypeMap(dtoSource, entitySource);
        var typeMapDto = Mapper.createTypeMap(entitySource, dtoSource);

        return adminRepository.saveAll(items.stream().map(typeMapEntity::map).toList())
                .stream()
                .map(typeMapDto::map)
                .toList();
    }

    @Override
    public List<D> getAll(int size) {
        var typeMapDto = Mapper.createTypeMap(entitySource, dtoSource);

        return adminRepository.findAll(Pageable.ofSize(size))
                .stream()
                .map(typeMapDto::map)
                .toList();
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
