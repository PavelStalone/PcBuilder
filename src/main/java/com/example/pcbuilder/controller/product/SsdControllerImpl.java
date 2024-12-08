package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.SsdService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.SsdController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.SsdDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.SsdFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ssd.SsdDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ssd.SsdInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ssd.SsdListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.ssd.SsdViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.example.pcbuilder.common.fake.FakeData.ssdConnectors;

@Controller
@RequestMapping("/product/ssd")
public class SsdControllerImpl implements SsdController {

    private final SsdService service;

    @Autowired
    public SsdControllerImpl(SsdService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") SsdFilter filter,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new SsdFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper(),
                filter.memoryLower(),
                filter.memoryUpper(),
                filter.maxReadLower(),
                filter.maxReadUpper(),
                filter.maxWriteLower(),
                filter.maxWriteUpper()
        );
        var pages = service.getAllByFilter(filterIn)
                .map((it) -> Mapper.createTypeMap(SsdDto.class, SsdViewModel.class).map(it));

        var viewModel = new SsdListViewModel(
                createBaseViewModel("Ssd диски"),
                pages.toList(),
                pages.getTotalPages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", filterIn);

        return "product/ssd/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new SsdDetailsViewModel(
                createBaseViewModel("Ssd диск"),
                service.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        return "product/ssd/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new SsdInputViewModel(
                createBaseViewModel("Добавление ssd диска"),
                ssdConnectors
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new SsdDto());

        return "product/ssd/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") SsdDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new SsdInputViewModel(
                    createBaseViewModel("Добавление ssd диска"),
                    ssdConnectors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/ssd/create";
        }

        var id = service.create(form);

        return "redirect:/product/ssd/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new SsdInputViewModel(
                createBaseViewModel("Редактирование ssd диска"),
                ssdConnectors
        );
        var form = service.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        return "product/ssd/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") SsdDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new SsdInputViewModel(
                    createBaseViewModel("Редактирование ssd диска"),
                    ssdConnectors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/ssd/edit";
        }

        service.create(form);

        return "redirect:/product/ssd/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        service.remove(id);

        return "redirect:/product/ssd";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}