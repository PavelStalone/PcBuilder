package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.common.fake.FakerUtil;
import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.data.model.RatePrepare;
import com.example.pcbuilder.service.admin.contract.AdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.RateDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.UserDto;

import java.util.stream.Stream;

public class RateWriter implements DbRandomWriter {

    private final String entityName;
    private final AdminService<UserDto> userService;
    private final AdminService<RateDto> rateService;
    private final AdminService<BuildDto> buildService;
    private final ClassFiller<RatePrepare> ratePrepare;

    public RateWriter(
            String entityName,
            AdminService<UserDto> userService,
            AdminService<RateDto> rateService,
            AdminService<BuildDto> buildService,
            ClassFiller<RatePrepare> ratePrepare
    ) {
        this.entityName = entityName;
        this.userService = userService;
        this.rateService = rateService;
        this.buildService = buildService;
        this.ratePrepare = ratePrepare;
    }

    @Override
    public void write(int repeat) {
        var itemCounts = rateService.countsItems();

        if (itemCounts <= 0 && repeat > 0) {
            Log.d("%s not found, starting writing...", entityName);

            var prepares = Stream.generate(ratePrepare::getFill)
                    .limit(repeat);
            var rates = prepares.map((it) -> {
                        var rate = new RateDto();

                        rate.setRate(it.rate());
                        rate.setComment(it.comment());

                        return rate;
                    })
                    .toList();

            var userList = userService.getAll(repeat);
            var buildList = buildService.getAll(repeat);

            for (int i = 0; i < rates.size(); i++) {
                rates.get(i).setUser(userList.get(i));
                rates.get(i).setBuild(FakerUtil.oneOf(buildList));
            }

            rateService.addAll(rates);
            Log.d("%s finished", entityName);
        } else {
            Log.d("%s already have %d items, skipping...", entityName, itemCounts);
        }
    }
}
