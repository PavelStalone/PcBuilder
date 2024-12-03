package com.example.pcbuilder.service;

import java.util.List;

public interface AdminService<D> {

    void addItem(D item);

    void addAll(List<D> items);

    void removeItem(D item);

    long countsItems();
}
