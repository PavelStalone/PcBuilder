package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.common.fake.FakerUtil;
import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.data.model.BuildPrepare;
import com.example.pcbuilder.data.model.RatePrepare;
import com.example.pcbuilder.service.admin.contract.AdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.RateDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.UserDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.example.pcbuilder.common.fake.FakerUtil.faker;

public class BuildWriter implements DbRandomWriter {

    private final String entityName;
    private final AdminService<RamDto> ramService;
    private final AdminService<SsdDto> ssdService;
    private final AdminService<HddDto> hddService;
    private final AdminService<CpuDto> cpuService;
    private final AdminService<GpuDto> gpuService;
    private final AdminService<TagDto> tagService;
    private final AdminService<CaseDto> caseService;
    private final AdminService<UserDto> userService;
    private final AdminService<PowerDto> powerService;
    private final AdminService<BuildDto> buildService;
    private final ClassFiller<RatePrepare> ratePrepare;
    private final ClassFiller<BuildPrepare> buildPrepare;
    private final AdminService<MotherboardDto> motherboardService;

    public BuildWriter(
            String entityName,
            AdminService<RamDto> ramService,
            AdminService<SsdDto> ssdService,
            AdminService<HddDto> hddService,
            AdminService<CpuDto> cpuService,
            AdminService<GpuDto> gpuService,
            AdminService<TagDto> tagService,
            AdminService<CaseDto> caseService,
            AdminService<UserDto> userService,
            AdminService<PowerDto> powerService,
            AdminService<BuildDto> buildService,
            ClassFiller<RatePrepare> ratePrepare,
            ClassFiller<BuildPrepare> buildPrepare,
            AdminService<MotherboardDto> motherboardService
    ) {
        this.entityName = entityName;
        this.ramService = ramService;
        this.ssdService = ssdService;
        this.hddService = hddService;
        this.cpuService = cpuService;
        this.gpuService = gpuService;
        this.tagService = tagService;
        this.caseService = caseService;
        this.userService = userService;
        this.powerService = powerService;
        this.buildService = buildService;
        this.ratePrepare = ratePrepare;
        this.buildPrepare = buildPrepare;
        this.motherboardService = motherboardService;
    }

    @Override
    public void write(int repeat) {
        var itemCounts = buildService.countsItems();

        if (itemCounts <= 0 && repeat > 0) {
            Log.d("%s not found, starting writing...", entityName);

            var prepares = Stream.generate(buildPrepare::getFill)
                    .limit(repeat)
                    .toList();

            var userList = userService.addAll(prepares.stream().map(BuildPrepare::owner).toList());
            var ramList = ramService.addAll(prepares.stream().map(BuildPrepare::ram).toList());
            var ssdList = ssdService.addAll(prepares.stream().map(BuildPrepare::ssd).toList());
            var hddList = hddService.addAll(prepares.stream().map(BuildPrepare::hdd).toList());
            var cpuList = cpuService.addAll(prepares.stream().map(BuildPrepare::cpu).toList());
            var gpuList = gpuService.addAll(prepares.stream().map(BuildPrepare::gpu).toList());
            var caseList = caseService.addAll(prepares.stream().map(BuildPrepare::pcCase).toList());
            var powerList = powerService.addAll(prepares.stream().map(BuildPrepare::powerUnit).toList());
            var motherboardList = motherboardService.addAll(prepares.stream().map(BuildPrepare::motherboard).toList());

            var tags = tagService.getAll(repeat); // TODO: Add check if tags is not initialized - shoplikpavel

            var builds = prepares.stream().map(BuildPrepare::build).toList();

            for (int i = 0; i < builds.size(); i++) {
                builds.get(i).setOwner(userList.get(i));
                builds.get(i).setRam(ramList.get(i));
                builds.get(i).setSsd(ssdList.get(i));
                builds.get(i).setHdd(hddList.get(i));
                builds.get(i).setCpu(cpuList.get(i));
                builds.get(i).setGpu(gpuList.get(i));
                builds.get(i).setPcCase(caseList.get(i));
                builds.get(i).setPowerUnit(powerList.get(i));
                builds.get(i).setMotherboard(motherboardList.get(i));

                var takingTags = FakerUtil.manyOf(tags, faker.random().nextInt(1, 5));
                builds.get(i).setTags(takingTags);

//                int finalI = i;
//                var rates = Stream.generate(ratePrepare::getFill)
//                        .limit(faker.random().nextInt(0, 10))
//                        .map((it) -> {
//                            var rate = new RateDto();
//
//                            rate.setRate(it.rate());
//                            rate.setComment(it.comment());
//
//                            rate.setBuild(builds.get(finalI));
//                            rate.setUser(FakerUtil.oneOf(userList));
//
//                            return rate;
//                        })
//                        .collect(Collectors.toSet());
//                builds.get(i).setRates(rates);
            }

            buildService.addAll(builds);

            Log.d("%s finished", entityName);
        } else {
            Log.d("%s already have %d items, skipping...", entityName, itemCounts);
        }
    }
}
