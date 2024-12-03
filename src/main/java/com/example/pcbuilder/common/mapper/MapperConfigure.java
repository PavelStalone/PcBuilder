package com.example.pcbuilder.common.mapper;

import org.modelmapper.TypeMap;

interface MapperConfigure<S, D> {

    TypeMap<S, D> configure(TypeMap<S, D> mapper);
}
