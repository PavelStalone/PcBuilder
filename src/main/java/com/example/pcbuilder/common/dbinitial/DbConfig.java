package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import edu.rutmiit.example.pcbuildercontracts.dto.other.UserDto;
import com.example.pcbuilder.service.admin.contract.AdminService;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.*;
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
}
