package com.example.pcbuilder.common.fake;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FakerUtil {

    public static final Faker faker = new Faker();

    public static <T> T oneOf(List<T> list) {
        return list.get(faker.random().nextInt(list.size()));
    }

    public static <T> Set<T> manyOf(List<T> list, int size) {
        var set = new HashSet<T>();
        var copyList = new ArrayList<>(list);

        for (int i = 0; i < size; i++) {
            var random = faker.random().nextInt(copyList.size());

            set.add(copyList.get(random));
            copyList.remove(random);
        }

        return set;
    }
}
