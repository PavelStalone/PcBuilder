package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.FakerUtil;
import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.service.admin.contract.AdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.OrderDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserDto;

import java.util.stream.Stream;

public class OrderWriter implements DbRandomWriter {

    private final String entityName;
    private final AdminService<UserDto> userService;
    private final AdminService<OrderDto> orderService;
    private final AdminService<BuildDto> buildService;

    public OrderWriter(
            String entityName,
            AdminService<UserDto> userService,
            AdminService<OrderDto> orderService,
            AdminService<BuildDto> buildService
    ) {
        this.entityName = entityName;
        this.userService = userService;
        this.orderService = orderService;
        this.buildService = buildService;
    }

    @Override
    public void write(int repeat) {
        var itemCounts = orderService.countsItems();

        if (itemCounts <= 0 && repeat > 0) {
            Log.d("%s not found, starting writing...", entityName);

            var userList = userService.getAll(repeat);
            var buildList = buildService.getAll(repeat);

            var orders = Stream.generate(() -> {
                        var order = new OrderDto();

                        order.setCost(FakerUtil.faker.random().nextInt(70_000, 450_000));

                        return order;
                    })
                    .limit(repeat)
                    .toList();

            for (int i = 0; i < orders.size(); i++) {
                orders.get(i).setUser(userList.get(i));
                orders.get(i).setBuild(FakerUtil.oneOf(buildList));
            }

            orderService.addAll(orders);
            Log.d("%s finished", entityName);
        } else {
            Log.d("%s already have %d items, skipping...", entityName, itemCounts);
        }
    }
}
