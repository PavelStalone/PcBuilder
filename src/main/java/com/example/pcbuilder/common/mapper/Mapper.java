package com.example.pcbuilder.common.mapper;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.domain.entity.*;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.other.OrderDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.RateDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRoles;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.entry;

public class Mapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    private static final Map<String, MapperConfigure<?, ?>> map = Map.ofEntries(
            entry(
                    BuildDto.class.getName() + "-" + BuildViewModel.class.getName(),
                    (MapperConfigure<BuildDto, BuildViewModel>) mapper -> {
                        Converter<UserDto, String> ownerNameConverter = c -> c.getSource().getUsername();

                        return mapper.addMappings(map -> map.using(ownerNameConverter).map(BuildDto::getOwner, BuildViewModel::setOwner));
                    }
            ),
            entry(
                    Build.class.getName() + "-" + BuildDto.class.getName(),
                    (MapperConfigure<Build, BuildDto>) mapper -> {
                        var dtoMapper = Mapper.createTypeMap(Tag.class, TagDto.class);

                        Converter<List<Tag>, Set<TagDto>> tagsConverter = c -> c.getSource() != null ? c.getSource().stream().map(dtoMapper::map).collect(Collectors.toSet()) : new HashSet<>();

                        return mapper.addMappings(map -> map.using(tagsConverter).map(Build::getTags, BuildDto::setTags));
                    }
            ),
            entry(
                    BuildDto.class.getName() + "-" + Build.class.getName(),
                    (MapperConfigure<BuildDto, Build>) mapper -> {
                        var entityMapper = Mapper.createTypeMap(TagDto.class, Tag.class);

                        Converter<Set<TagDto>, List<Tag>> tagsConverter = c -> c.getSource() != null ? c.getSource().stream().map(entityMapper::map).toList() : new ArrayList<>();

                        return mapper.addMappings(map -> map.using(tagsConverter).map(BuildDto::getTags, Build::setTags));
                    }
            ),
            entry(
                    UserRoles.class.getName() + "-" + Role.class.getName(),
                    (MapperConfigure<UserRoles, Role>) mapper -> {
                        Converter<UserRoles, Role> roleConverter = c -> new Role(c.getSource());

                        return mapper.setConverter(roleConverter);
                    }
            ),
            entry(
                    UserDto.class.getName() + "-" + User.class.getName(),
                    (MapperConfigure<UserDto, User>) mapper -> {
                        var entityMapperRate = Mapper.createTypeMap(RateDto.class, Rate.class);
                        var entityMapperRole = Mapper.createTypeMap(UserRoles.class, Role.class);
                        var entityMapperOrder = Mapper.createTypeMap(OrderDto.class, Order.class);

                        Converter<Set<RateDto>, List<Rate>> ratesConverter = c -> c.getSource() != null ? c.getSource().stream().map(entityMapperRate::map).toList() : new ArrayList<>();
                        Converter<Set<UserRoles>, List<Role>> rolesConverter = c -> c.getSource() != null ? c.getSource().stream().map(entityMapperRole::map).toList() : new ArrayList<>();
                        Converter<Set<OrderDto>, List<Order>> ordersConverter = c -> c.getSource() != null ? c.getSource().stream().map(entityMapperOrder::map).toList() : new ArrayList<>();

                        Converter<LocalDate, Date> dateConverter = c -> c.getSource() != null ? Date.valueOf(c.getSource()) : null;

                        return mapper
                                .addMappings(map -> map.using(ratesConverter).map(UserDto::getRates, User::setRates))
                                .addMappings(map -> map.using(rolesConverter).map(UserDto::getRoles, User::setRoles))
                                .addMappings(map -> map.using(ordersConverter).map(UserDto::getOrders, User::setOrders))
                                .addMappings(map -> map.using(dateConverter).map(UserDto::getDate, User::setDate));
                    }
            ),
            entry(
                    User.class.getName() + "-" + UserDto.class.getName(),
                    (MapperConfigure<User, UserDto>) mapper -> {
                        var dtoMapperRate = Mapper.createTypeMap(Rate.class, RateDto.class);
                        var dtoMapperRole = Mapper.createTypeMap(Role.class, UserRoles.class);
                        var dtoMapperOrder = Mapper.createTypeMap(Order.class, OrderDto.class);

                        Converter<List<Rate>, Set<RateDto>> ratesConverter = c -> c.getSource() != null ? c.getSource().stream().map(dtoMapperRate::map).collect(Collectors.toSet()) : new HashSet<>();
                        Converter<List<Role>, Set<UserRoles>> rolesConverter = c -> c.getSource() != null ? c.getSource().stream().map(dtoMapperRole::map).collect(Collectors.toSet()) : new HashSet<>();
                        Converter<List<Order>, Set<OrderDto>> ordersConverter = c -> c.getSource() != null ? c.getSource().stream().map(dtoMapperOrder::map).collect(Collectors.toSet()) : new HashSet<>();

                        Converter<Date, LocalDate> dateConverter = c -> c.getSource() != null ? c.getSource().toLocalDate() : null;

                        return mapper
                                .addMappings(map -> map.using(ratesConverter).map(User::getRates, UserDto::setRates))
                                .addMappings(map -> map.using(rolesConverter).map(User::getRoles, UserDto::setRoles))
                                .addMappings(map -> map.using(ordersConverter).map(User::getOrders, UserDto::setOrders))
                                .addMappings(map -> map.using(dateConverter).map(User::getDate, UserDto::setDate));
                    }
            )
    );

    public static <S, D> TypeMap<S, D> createTypeMap(Class<S> sourceClass, Class<D> destinationClass) {
        Log.v("CreateTypeMap called. source: %s, destination: %s", sourceClass.getName(), destinationClass.getName());
        TypeMap<S, D> typeMap = modelMapper.typeMap(sourceClass, destinationClass);
        String key = sourceClass.getName() + "-" + destinationClass.getName();

        if (map.containsKey(key)) {
            Log.v("Property map found. Use special settings");
            typeMap = ((MapperConfigure<S, D>) map.get(key)).configure(typeMap);
        } else {
            Log.v("Property map not found. Use defaults mapper");
        }
        return typeMap;
    }
}
