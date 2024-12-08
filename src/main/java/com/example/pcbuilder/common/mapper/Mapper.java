package com.example.pcbuilder.common.mapper;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.domain.entity.Build;
import com.example.pcbuilder.domain.entity.Tag;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.other.UserDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    private static final Map<String, MapperConfigure<?, ?>> map = Map.ofEntries(
            entry(
                    BuildDto.class.getName() + "-" + BuildViewModel.class.getName(),
                    (MapperConfigure<BuildDto, BuildViewModel>) mapper -> {
                        Converter<UserDto, String> ownerNameConverter = c -> c.getSource().getNickName();

                        return mapper.addMappings(map -> map.using(ownerNameConverter).map(BuildDto::getOwner, BuildViewModel::setOwner));
                    }
            ),
            entry(
                    BuildDto.class.getName() + "-" + Build.class.getName(),
                    (MapperConfigure<BuildDto, Build>) mapper -> {
                        var entityMapper = Mapper.createTypeMap(TagDto.class, Tag.class);

                        Converter<Set<TagDto>, Set<Tag>> tagsConverter = c -> c.getSource().stream().map(entityMapper::map).collect(Collectors.toSet());

                        return mapper.addMappings(map -> map.using(tagsConverter).map(BuildDto::getTags, Build::setTags));
                    }
            )
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
