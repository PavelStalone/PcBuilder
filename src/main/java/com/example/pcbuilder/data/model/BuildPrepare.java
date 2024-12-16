package com.example.pcbuilder.data.model;

import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.*;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserDto;

public record BuildPrepare(
        BuildDto build,
        UserDto owner,
        RamDto ram,
        SsdDto ssd,
        HddDto hdd,
        CpuDto cpu,
        GpuDto gpu,
        CaseDto pcCase,
        PowerDto powerUnit,
        MotherboardDto motherboard
) {
}
