package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.MotherboardService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.MotherboardController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.MotherboardDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.MotherboardFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.motherboard.MotherboardDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.motherboard.MotherboardInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.motherboard.MotherboardListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.motherboard.MotherboardViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.example.pcbuilder.common.fake.FakeData.*;

@Controller
@RequestMapping("/product/motherboard")
public class MotherboardControllerImpl implements MotherboardController {

    private final MotherboardService service;

    @Autowired
    public MotherboardControllerImpl(MotherboardService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") MotherboardFilter filter,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new MotherboardFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper(),
                filter.maxMemoryFreqLower(),
                filter.maxMemoryFreqUpper(),
                filter.memorySlotsLower(),
                filter.memorySlotsUpper(),
                filter.maxMemoryFreqUpper(),
                filter.maxMemoryFreqUpper(),
                filter.maxMemoryCapacityLower(),
                filter.maxMemoryCapacityUpper()
        );
        var pages = service.getAllByFilter(filterIn)
                .map((it) -> Mapper.createTypeMap(MotherboardDto.class, MotherboardViewModel.class).map(it));

        var viewModel = new MotherboardListViewModel(
                createBaseViewModel("Материнские платы"),
                pages.toList(),
                pages.getTotalPages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", filterIn);

        return "product/motherboard/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new MotherboardDetailsViewModel(
                createBaseViewModel("Материнская плата"),
                service.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        return "product/motherboard/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new MotherboardInputViewModel(
                createBaseViewModel("Добавление материнской платы"),
                motherboardFormFactors,
                ramType,
                cpuSockets,
                ramFormFactors,
                gpuInterfaces
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new MotherboardDto());

        return "product/motherboard/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") MotherboardDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new MotherboardInputViewModel(
                    createBaseViewModel("Добавление материнской платы"),
                    motherboardFormFactors,
                    ramType,
                    cpuSockets,
                    ramFormFactors,
                    gpuInterfaces
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/motherboard/create";
        }

        var id = service.create(form);

        return "redirect:/product/motherboard/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new MotherboardInputViewModel(
                createBaseViewModel("Редактирование материнской платы"),
                motherboardFormFactors,
                ramType,
                cpuSockets,
                ramFormFactors,
                gpuInterfaces
        );
        var form = service.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        return "product/motherboard/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") MotherboardDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new MotherboardInputViewModel(
                    createBaseViewModel("Редактирование материнской платы"),
                    motherboardFormFactors,
                    ramType,
                    cpuSockets,
                    ramFormFactors,
                    gpuInterfaces
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/motherboard/edit";
        }

        service.create(form);

        return "redirect:/product/motherboard/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        service.remove(id);

        return "redirect:/product/motherboard";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
