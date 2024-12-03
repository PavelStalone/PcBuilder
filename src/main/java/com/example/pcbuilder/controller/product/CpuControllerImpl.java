package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.CpuService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.CpuController;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.CpuViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/product/cpu")
public class CpuControllerImpl implements CpuController {

    private final CpuService cpuService;

    @Autowired
    public CpuControllerImpl(CpuService cpuService) {
        this.cpuService = cpuService;
    }

    @Override
    @GetMapping
    public String cpuList(
            @ModelAttribute("filter") CpuFilter cpuFilter,
            Model model
    ) {
        var filter = new CpuFilter(
                cpuFilter.page() > 0 ? cpuFilter.page() : 1,
                cpuFilter.size() > 0 ? cpuFilter.size() : 1,
                cpuFilter.isDescCost(),
                cpuFilter.costRange(),
                cpuFilter.yearRange(),
                cpuFilter.coreRange(),
                cpuFilter.freqRange(),
                cpuFilter.threadRange()
        );
        var cpuList = cpuService.getAllByFilter(filter)
                .map((it) -> Mapper.createTypeMap(CpuDto.class, CpuViewModel.class).map(it))
                .toList();

        model.addAttribute("model", cpuList); // TODO: add viewModel - shoplikpavel
        model.addAttribute("filter", filter);

        return null;
    }

    @Override
    @GetMapping("/{id}")
    public String detailCpu(UUID id, Model model) {
        var cpu = cpuService.getById(id);

        model.addAttribute("model", cpu);

        return null;
    }
}
