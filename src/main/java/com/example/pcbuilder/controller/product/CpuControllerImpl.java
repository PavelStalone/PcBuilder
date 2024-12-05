package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.CpuService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.CpuController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static com.example.pcbuilder.common.fake.FakeData.ramType;

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
                cpuFilter.page() != null ? cpuFilter.page() : 1,
                cpuFilter.size() != null ? cpuFilter.size() : 10,
                "",
                cpuFilter.isDescCost(),
                cpuFilter.costRange(),
                cpuFilter.yearRange(),
                cpuFilter.coreRange(),
                cpuFilter.freqRange(),
                cpuFilter.threadRange()
        );
        var cpuPages = cpuService.getAllByFilter(filter)
                .map((it) -> Mapper.createTypeMap(CpuDto.class, CpuViewModel.class).map(it));

        var cpuVM = new CpuListViewModel(
                createBaseViewModel("Процессоры"),
                cpuPages.toList(),
                cpuPages.getTotalPages()
        );

        model.addAttribute("model", cpuVM);
        model.addAttribute("filter", filter);

        return "product/cpu/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detailCpu(
            UUID id,
            Model model
    ) {
        var details = new CpuDetailsViewModel(
                createBaseViewModel("Процессор"),
                cpuService.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        return "product/cpu/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        var details = new CpuInputViewModel(
                createBaseViewModel("Добавление процессора"),
                ramType
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new CpuDto());

        return "product/cpu/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") CpuDto form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error - " + form);

            var details = new CpuInputViewModel(
                    createBaseViewModel("Добавление процессора"),
                    ramType
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/cpu/create";
        }

        System.out.println(form);

        var id = cpuService.create(form);

        return "redirect:/product/cpu/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        var details = new CpuInputViewModel(
                createBaseViewModel("Редактирование процессора"),
                ramType
        );
        var form = cpuService.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        return "product/cpu/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") CpuDto form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            System.out.println("Error - " + form);

            var details = new CpuInputViewModel(
                    createBaseViewModel("Редактирование процессора"),
                    ramType
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/cpu/edit";
        }

        System.out.println(form);

        cpuService.create(form);

        return "redirect:/product/cpu/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(UUID id, Model model) {
        cpuService.remove(id);

        return "redirect:/product/cpu";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
