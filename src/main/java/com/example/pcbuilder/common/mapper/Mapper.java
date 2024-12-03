package com.example.pcbuilder.common.mapper;

import com.example.pcbuilder.common.log.Log;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Map;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    private static final Map<String, MapperConfigure<?, ?>> map = Map.ofEntries(
    );

    public static <S, D> TypeMap<S, D> createTypeMap(Class<S> sourceClass, Class<D> destinationClass) {
        Log.d("CreateTypeMap called. source: %s, destination: %s", sourceClass.getName(), destinationClass.getName());
        TypeMap<S, D> typeMap = modelMapper.typeMap(sourceClass, destinationClass);
        String key = sourceClass.getName() + "-" + destinationClass.getName();

        if (map.containsKey(key)) {
            Log.d("Property map found. Use special settings");
            typeMap = ((MapperConfigure<S, D>) map.get(key)).configure(typeMap);
        } else {
            Log.d("Property map not found. Use defaults mapper");
        }
        return typeMap;
    }
}
