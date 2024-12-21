package com.example.pcbuilder.controller.home;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.*;
import edu.rutmiit.example.pcbuildercontracts.controllers.home.HomeController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.home.HomeViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.*;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.hdd.HddViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.motherboard.MotherboardViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.pccase.CaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.power.PowerViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ram.RamViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ssd.SsdViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeControllerImpl implements HomeController {

    private final CpuService cpuService;
    private final GpuService gpuService;
    private final HddService hddService;
    private final RamService ramService;
    private final SsdService ssdService;
    private final CaseService caseService;
    private final PowerService powerService;
    private final MotherboardService motherboardService;

    @Autowired
    public HomeControllerImpl(
            CpuService cpuService,
            GpuService gpuService,
            HddService hddService,
            RamService ramService,
            SsdService ssdService,
            CaseService caseService,
            PowerService powerService,
            MotherboardService motherboardService
    ) {
        this.cpuService = cpuService;
        this.gpuService = gpuService;
        this.hddService = hddService;
        this.ramService = ramService;
        this.ssdService = ssdService;
        this.caseService = caseService;
        this.powerService = powerService;
        this.motherboardService = motherboardService;
    }

    @Override
    @GetMapping
    public String index(Model model) {
        Log.d("index called");

        var cpu = cpuService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(CpuDto.class, CpuViewModel.class).map(it))
                .orElse(null);
        var gpu = gpuService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(GpuDto.class, GpuViewModel.class).map(it))
                .orElse(null);
        var hdd = hddService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(HddDto.class, HddViewModel.class).map(it))
                .orElse(null);
        var ram = ramService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(RamDto.class, RamViewModel.class).map(it))
                .orElse(null);
        var ssd = ssdService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(SsdDto.class, SsdViewModel.class).map(it))
                .orElse(null);
        var pcCase = caseService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(CaseDto.class, CaseViewModel.class).map(it))
                .orElse(null);
        var power = powerService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(PowerDto.class, PowerViewModel.class).map(it))
                .orElse(null);
        var motherboard = motherboardService.findMostPopular()
                .map((it) -> Mapper.createTypeMap(MotherboardDto.class, MotherboardViewModel.class).map(it))
                .orElse(null);

        var viewModel = new HomeViewModel(
                createBaseViewModel("PcBuilder"),
                cpu,
                gpu,
                hdd,
                ram,
                ssd,
                pcCase,
                power,
                motherboard
        );

        model.addAttribute("model", viewModel);

        Log.i("Open main screen");
        return "index";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
