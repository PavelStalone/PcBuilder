package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.data.model.BuildPrepare;
import com.example.pcbuilder.data.model.RatePrepare;
import com.example.pcbuilder.service.admin.contract.AdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.OrderDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.RateDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.*;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean
    public DbRandomWriter cpuWriter(
            AdminService<CpuDto> service,
            ClassFiller<CpuDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Cpu");
    }

    @Bean
    public DbRandomWriter ssdWriter(
            AdminService<SsdDto> service,
            ClassFiller<SsdDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Ssd");
    }

    @Bean
    public DbRandomWriter ramWriter(
            AdminService<RamDto> service,
            ClassFiller<RamDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Ram");
    }

    @Bean
    public DbRandomWriter powerWriter(
            AdminService<PowerDto> service,
            ClassFiller<PowerDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Power");
    }

    @Bean
    public DbRandomWriter motherboardWriter(
            AdminService<MotherboardDto> service,
            ClassFiller<MotherboardDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Motherboard");
    }

    @Bean
    public DbRandomWriter hddWriter(
            AdminService<HddDto> service,
            ClassFiller<HddDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Hdd");
    }

    @Bean
    public DbRandomWriter gpuWriter(
            AdminService<GpuDto> service,
            ClassFiller<GpuDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Gpu");
    }

    @Bean
    public DbRandomWriter caseWriter(
            AdminService<CaseDto> service,
            ClassFiller<CaseDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Case");
    }

    @Bean
    public DbRandomWriter userWriter(
            AdminService<UserDto> service,
            ClassFiller<UserDto> filler
    ) {
        return new BaseWriter<>(service, filler, "User");
    }

    @Bean
    public DbRandomWriter tagWriter(
            AdminService<TagDto> service,
            ClassFiller<TagDto> filler
    ) {
        return new BaseWriter<>(service, filler, "Tag");
    }

    @Bean
    public DbRandomWriter buildWriter(
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
            ClassFiller<BuildPrepare> buildPrepare,
            AdminService<MotherboardDto> motherboardService
    ) {
        return new BuildWriter(
                "Build",
                ramService,
                ssdService,
                hddService,
                cpuService,
                gpuService,
                tagService,
                caseService,
                userService,
                powerService,
                buildService,
                buildPrepare,
                motherboardService
        );
    }

    @Bean
    public DbRandomWriter rateWriter(
            AdminService<UserDto> userService,
            AdminService<RateDto> rateService,
            AdminService<BuildDto> buildService,
            ClassFiller<RatePrepare> ratePrepare
    ) {
        return new RateWriter(
                "Rate",
                userService,
                rateService,
                buildService,
                ratePrepare
        );
    }

    @Bean
    public DbRandomWriter orderWriter(
            AdminService<UserDto> userService,
            AdminService<OrderDto> orderService,
            AdminService<BuildDto> buildService
    ) {
        return new OrderWriter(
                "Order",
                userService,
                orderService,
                buildService
        );
    }
}
