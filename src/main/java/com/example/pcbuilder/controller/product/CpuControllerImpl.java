package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.CpuService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.CpuController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CaseDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CpuFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.cpu.CpuViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.pccase.CaseViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String list(
            @ModelAttribute("filter") CpuFilter filter,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new CpuFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper(),
                filter.yearLower(),
                filter.yearUpper(),
                filter.coreLower(),
                filter.coreUpper(),
                filter.freqLower(),
                filter.freqUpper(),
                filter.threadLower(),
                filter.threadUpper()
        );
        var result = cpuService.getAllByFilter(filterIn);
        var pages = result.list()
                .stream()
                .map((it) -> Mapper.createTypeMap(CpuDto.class, CpuViewModel.class).map(it));

        var cpuVM = new CpuListViewModel(
                createBaseViewModel("Процессоры"),
                pages.toList(),
                result.pages()
        );

        model.addAttribute("model", cpuVM);
        model.addAttribute("filter", filterIn);

        return "product/cpu/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

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
        Log.d("createForm called");

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
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new CpuInputViewModel(
                    createBaseViewModel("Добавление процессора"),
                    ramType
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/cpu/create";
        }

        var id = cpuService.create(form);

        return "redirect:/product/cpu/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

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
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new CpuInputViewModel(
                    createBaseViewModel("Редактирование процессора"),
                    ramType
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/cpu/edit";
        }

        cpuService.create(form);

        return "redirect:/product/cpu/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(UUID id, Model model) {
        Log.d("delete called - id: " + id);

        cpuService.remove(id);

        return "redirect:/product/cpu";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
