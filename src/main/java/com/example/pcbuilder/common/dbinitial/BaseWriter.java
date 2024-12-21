package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.service.admin.contract.AdminService;

import java.util.stream.Stream;

public class BaseWriter<T> implements DbRandomWriter {

    private final AdminService<T> itemService;
    private final ClassFiller<T> itemFiller;
    private final String entityName;
    private final int limit;

    public BaseWriter(
            AdminService<T> itemService,
            ClassFiller<T> itemFiller,
            String entityName
    ) {
        this(itemService, itemFiller, entityName, 0);
    }

    public BaseWriter(
            AdminService<T> itemService,
            ClassFiller<T> itemFiller,
            String entityName,
            int limit
    ) {
        this.itemService = itemService;
        this.itemFiller = itemFiller;
        this.entityName = entityName;
        this.limit = limit;
    }

    @Override
    public void write(int repeat) {
        var itemCounts = itemService.countsItems();

        if (limit > 0 && limit < repeat) repeat = limit;

        if (itemCounts <= 0 && repeat > 0) {
            Log.d("%s not found, starting writing...", entityName);

            var items = Stream.generate(itemFiller::getFill)
                    .limit(repeat)
                    .toList();

            if (items.size() == 1) itemService.addItem(items.get(0));
            else itemService.addAll(items);

            Log.d("%s finished", entityName);
        } else {
            Log.d("%s already have %d items, skipping...", entityName, itemCounts);
        }
    }
}
