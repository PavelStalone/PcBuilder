package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

public class BaseWriter<T> implements DbRandomWriter {

    private final AdminService<T> itemService;
    private final ClassFiller<T> itemFiller;

    @Autowired
    public BaseWriter(AdminService<T> itemService, ClassFiller<T> itemFiller) {
        this.itemService = itemService;
        this.itemFiller = itemFiller;
    }

    @Override
    public void write(int repeat) {
        var itemCounts = itemService.countsItems();

        if (itemCounts <= 0 && repeat > 0) {
            var items = Stream.generate(itemFiller::getFill)
                    .limit(repeat)
                    .toList();

            if (items.size() == 1) itemService.addItem(items.get(0));
            else itemService.addAll(items);
        }
    }
}
