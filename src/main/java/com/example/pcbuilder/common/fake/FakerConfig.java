package com.example.pcbuilder.common.fake;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.data.model.BuildPrepare;
import com.example.pcbuilder.data.model.RatePrepare;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.TagDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.RateDto;
import edu.rutmiit.example.pcbuildercontracts.dto.other.UserDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static com.example.pcbuilder.common.fake.FakeData.*;
import static com.example.pcbuilder.common.fake.FakerUtil.faker;
import static com.example.pcbuilder.common.fake.FakerUtil.oneOf;

@Configuration
public class FakerConfig {

    @Bean
    public ClassFiller<CpuDto> cpuFiller() {
        return () -> {
            var cpu = new CpuDto();

            cpu.setCost(faker.random().nextInt(10000, 90000));
            cpu.setYear(faker.date().between(new Date(2000, 1, 1), new Date(2024, 1, 1)).getYear());
            cpu.setCores(faker.random().nextInt(1, 16));
            cpu.setThreads(cpu.getCores() * 2);
            cpu.setBaseFreq(faker.random().nextInt(1500, 4500));
            cpu.setMaxFreq((int) (cpu.getBaseFreq() * 1.5));
            cpu.setModel(oneOf(cpuModels));
            cpu.setMaxMemory((int) Math.pow(2, faker.random().nextInt(4, 7)));
            cpu.setSocket(oneOf(cpuSockets));
            cpu.setMemoryType(new String[]{oneOf(ramType)});
            cpu.setHasGraphicsCore(faker.random().nextBoolean());

            return cpu;
        };
    }

    @Bean
    public ClassFiller<SsdDto> ssdFiller() {
        return () -> {
            var ssd = new SsdDto();

            ssd.setCost(faker.random().nextInt(5000, 30000));
            ssd.setTbw(faker.random().nextInt(50, 1000));
            ssd.setMaxPower(faker.random().nextInt(1, 20));
            ssd.setModel(oneOf(ssdModels));
            ssd.setMaxReadSpeed(faker.random().nextInt(400, 5000));
            ssd.setMaxWriteSpeed(faker.random().nextInt(400, 5000));
            ssd.setMemoryCapacity(faker.random().nextInt(250, 1000));
            ssd.setInterfaceType(oneOf(ssdConnectors));

            return ssd;
        };
    }

    @Bean
    public ClassFiller<RamDto> ramFiller() {
        return () -> {
            var ram = new RamDto();

            ram.setCost(faker.random().nextInt(2000, 30000));
            ram.setFreq(faker.random().nextInt(1200, 5000));
            ram.setModel(oneOf(ramModels));
            ram.setMemoryType(oneOf(ramType));
            ram.setFormFactor(oneOf(ramFormFactors));
            ram.setMemoryCapacity((int) Math.pow(2, faker.random().nextInt(2, 5)));

            return ram;
        };
    }

    @Bean
    public ClassFiller<PowerDto> powerFiller() {
        return () -> {
            var power = new PowerDto();

            power.setCost(faker.random().nextInt(6000, 20000));
            power.setPower(faker.random().nextInt(40, 90) * 10);
            power.setModel(oneOf(powerModels));
            power.setFormFactor(oneOf(powerFormFactors));

            return power;
        };
    }

    @Bean
    public ClassFiller<MotherboardDto> motherBoardFiller() {
        return () -> {
            var motherBoard = new MotherboardDto();

            motherBoard.setCost(faker.random().nextInt(6000, 20000));
            motherBoard.setModel(oneOf(motherboardModels));
            motherBoard.setMaxMemoryFreq(faker.random().nextInt(3200, 8000));
            motherBoard.setFormFactor(oneOf(motherboardFormFactors));
            motherBoard.setMemoryType(oneOf(ramType));
            motherBoard.setMaxMemoryCapacity((int) Math.pow(2, faker.random().nextInt(5, 8)));
            motherBoard.setMemorySlotsCounts(faker.random().nextInt(1, 3) * 2);
            motherBoard.setGraphicSlotsCounts(faker.random().nextInt(1, 3));
            motherBoard.setProcessorSocket(oneOf(cpuSockets));
            motherBoard.setMemoryFormFactor(oneOf(ramFormFactors));
            motherBoard.setGraphicsSlotType(oneOf(gpuInterfaces));

            return motherBoard;
        };
    }

    @Bean
    public ClassFiller<HddDto> hddFiller() {
        return () -> {
            var hdd = new HddDto();

            hdd.setCost(faker.random().nextInt(2000, 15000));
            hdd.setMaxPower(faker.random().nextInt(3, 20));
            hdd.setModel(oneOf(hddModels));
            hdd.setCacheMemory((int) Math.pow(2, faker.random().nextInt(5, 9)));
            hdd.setRotationSpeed(faker.random().nextInt(20, 120) * 100);
            hdd.setMemoryCapacity(faker.random().nextInt(500, 20000));
            hdd.setMaxSpeedTransfer(faker.random().nextInt(150, 250));
            hdd.setInterfaceType(oneOf(hddInterfaces));

            return hdd;
        };
    }

    @Bean
    public ClassFiller<GpuDto> gpuFiller() {
        return () -> {
            var gpu = new GpuDto();

            gpu.setCost(faker.random().nextInt(20000, 500000));
            gpu.setFreq(faker.random().nextInt(1500, 5000));
            gpu.setMinPower(faker.random().nextInt(100, 400));
            gpu.setModel(oneOf(gpuModels));
            gpu.setSlotType(oneOf(gpuInterfaces));
            gpu.setMemoryType(oneOf(gpuMemoryTypes));
            gpu.setMemoryCapacity(faker.random().nextInt(1, 32));

            return gpu;
        };
    }

    @Bean
    public ClassFiller<CaseDto> caseFiller() {
        return () -> {
            var pcCase = new CaseDto();

            pcCase.setCost(faker.random().nextInt(2000, 12000));
            pcCase.setModel(oneOf(caseModels));
            pcCase.setPowerFactor(oneOf(powerFormFactors));
            pcCase.setMotherboardFactor(oneOf(motherboardFormFactors));

            return pcCase;
        };
    }

    @Bean
    public ClassFiller<UserDto> userFiller() {
        return () -> {
            var user = new UserDto();

            user.setDate(faker.date().birthday());
            user.setEmail(faker.internet().emailAddress());
            user.setNickName(faker.name().username());

            return user;
        };
    }

    @Bean
    public ClassFiller<TagDto> tagFiller() {
        Set<String> jobs = new HashSet<>();

        return () -> {
            String job;
            int i = 0;
            do {
                job = faker.job().field();
                i++;
                if (i > 50) job += i - 50;
            } while (jobs.contains(job));
            jobs.add(job);

            return new TagDto(job, faker.job().title());
        };
    }

    @Bean
    public ClassFiller<RatePrepare> ratePrepareFiller() {
        return () -> {
            return new RatePrepare(
                    faker.random().nextInt(1, 10),
                    faker.yoda().quote()
            );
        };
    }

    @Bean
    public ClassFiller<BuildPrepare> buildPrepareFiller() {
        return () -> {
            var build = new BuildDto();

            var ram = ramFiller().getFill();
            var ssd = ssdFiller().getFill();
            var hdd = hddFiller().getFill();
            var cpu = cpuFiller().getFill();
            var gpu = gpuFiller().getFill();
            var owner = userFiller().getFill();
            var pcCase = caseFiller().getFill();
            var power = powerFiller().getFill();
            var motherBoard = motherBoardFiller().getFill();

            cpu.setMemoryType(new String[]{motherBoard.getMemoryType()});
            cpu.setSocket(motherBoard.getProcessorSocket());

            pcCase.setMotherboardFactor(motherBoard.getFormFactor());

            gpu.setSlotType(motherBoard.getGraphicsSlotType());

            ram.setMemoryType(motherBoard.getMemoryType());
            ram.setFormFactor(motherBoard.getMemoryFormFactor());

            power.setFormFactor(pcCase.getPowerFactor());

            build.setRamCounts(faker.random().nextInt(1, motherBoard.getMemorySlotsCounts()));
            build.setGpuCounts(faker.random().nextInt(1, motherBoard.getGraphicSlotsCounts()));
            build.setDescription(faker.yoda().quote());

            return new BuildPrepare(
                    build,
                    owner,
                    ram,
                    ssd,
                    hdd,
                    cpu,
                    gpu,
                    pcCase,
                    power,
                    motherBoard
            );
        };
    }
}
