package com.example.pcbuilder.service.admin.contract;

import java.util.List;

public interface AdminService<D> {

    D addItem(D item);

    List<D> addAll(List<D> items);

    List<D> getAll(int size);

    void removeItem(D item);

    long countsItems();
}
