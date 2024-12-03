package com.example.pcbuilder.service.admin.contract;

import java.util.List;

public interface AdminService<D> {

    void addItem(D item);

    void addAll(List<D> items);

    void removeItem(D item);

    long countsItems();
}
