package com.example.pcbuilder.domain;

import java.util.List;

public record PageResult<T>(
        List<T> list,
        int pages
) {
}
