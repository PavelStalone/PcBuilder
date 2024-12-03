package com.example.pcbuilder.common.dbinitial;

import com.example.pcbuilder.common.fake.ClassFiller;
import com.example.pcbuilder.data.model.UserDto;
import com.example.pcbuilder.data.model.product.*;
import com.example.pcbuilder.service.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {

    @Bean
    public DbRandomWriter cpuWriter(
            AdminService<CpuDto> service,
            ClassFiller<CpuDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter ssdWriter(
            AdminService<SsdDto> service,
            ClassFiller<SsdDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter ramWriter(
            AdminService<RamDto> service,
            ClassFiller<RamDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter powerWriter(
            AdminService<PowerDto> service,
            ClassFiller<PowerDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter motherboardWriter(
            AdminService<MotherboardDto> service,
            ClassFiller<MotherboardDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter hddWriter(
            AdminService<HddDto> service,
            ClassFiller<HddDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter gpuWriter(
            AdminService<GraphicsCardDto> service,
            ClassFiller<GraphicsCardDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter caseWriter(
            AdminService<CaseDto> service,
            ClassFiller<CaseDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter userWriter(
            AdminService<UserDto> service,
            ClassFiller<UserDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }

    @Bean
    public DbRandomWriter tagWriter(
            AdminService<TagDto> service,
            ClassFiller<TagDto> filler
    ) {
        return new BaseWriter<>(service, filler);
    }
}
