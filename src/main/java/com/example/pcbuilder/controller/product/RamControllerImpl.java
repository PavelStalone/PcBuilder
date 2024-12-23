package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.GpuService;
import com.example.pcbuilder.service.product.contract.RamService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.GpuController;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.RamController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.GpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.PowerDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.RamDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.GpuFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.RamFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.power.PowerViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ram.RamDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ram.RamInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ram.RamListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ram.RamViewModel;
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
@RequestMapping("/product/ram")
public class RamControllerImpl implements RamController {

    private final RamService service;

    @Autowired
    public RamControllerImpl(RamService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") RamFilter filter,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new RamFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper(),
                filter.freqLower(),
                filter.freqUpper(),
                filter.memoryLower(),
                filter.memoryUpper()
        );
        var result = service.getAllByFilter(filterIn);
        var pages = result.list()
                .stream()
                .map((it) -> Mapper.createTypeMap(RamDto.class, RamViewModel.class).map(it));

        var viewModel = new RamListViewModel(
                createBaseViewModel("Оперативная память"),
                pages.toList(),
                result.pages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", bindingResult.hasErrors() ? filter : filterIn);

        Log.i("Open ram list on %d page", filterIn.page());
        return "product/ram/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new RamDetailsViewModel(
                createBaseViewModel("Оперативная память"),
                service.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        Log.i("Open detail for ram with id: " + id);
        return "product/ram/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new RamInputViewModel(
                createBaseViewModel("Добавление оперативной памяти"),
                ramType,
                ramFormFactors
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new RamDto());

        Log.i("Open creating page for ram");
        return "product/ram/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") RamDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new RamInputViewModel(
                    createBaseViewModel("Добавление оперативной памяти"),
                    ramType,
                    ramFormFactors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/ram/create";
        }

        var id = service.create(form);

        Log.i("Create new ram with id: " + id);
        return "redirect:/product/ram/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new RamInputViewModel(
                createBaseViewModel("Редактирование оперативной памяти"),
                ramType,
                ramFormFactors
        );
        var form = service.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        Log.i("Open editing page for ram with id: " + id);
        return "product/ram/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") RamDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new RamInputViewModel(
                    createBaseViewModel("Редактирование оперативной памяти"),
                    ramType,
                    ramFormFactors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/ram/edit";
        }

        service.create(form);

        Log.i("Edit ram with id: " + id);
        return "redirect:/product/ram/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        service.remove(id);

        Log.i("Remove ram with id: " + id);
        return "redirect:/product/ram";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
