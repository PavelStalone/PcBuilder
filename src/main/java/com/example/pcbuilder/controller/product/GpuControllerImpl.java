package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.GpuService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.GpuController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.GpuDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.GpuFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.gpu.GpuViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.example.pcbuilder.common.fake.FakeData.gpuInterfaces;
import static com.example.pcbuilder.common.fake.FakeData.gpuMemoryTypes;

@Controller
@RequestMapping("/product/gpu")
public class GpuControllerImpl implements GpuController {

    private final GpuService service;

    @Autowired
    public GpuControllerImpl(GpuService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") GpuFilter filter,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new GpuFilter(
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
                .map((it) -> Mapper.createTypeMap(GpuDto.class, GpuViewModel.class).map(it));

        var viewModel = new GpuListViewModel(
                createBaseViewModel("Видеокарты"),
                pages.toList(),
                result.pages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", bindingResult.hasErrors() ? filter : filterIn);

        Log.i("Open gpu list on %d page", filterIn.page());
        return "product/gpu/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new GpuDetailsViewModel(
                createBaseViewModel("Видеокарта"),
                service.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        Log.i("Open detail for gpu with id: " + id);
        return "product/gpu/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new GpuInputViewModel(
                createBaseViewModel("Добавление видеокарты"),
                gpuInterfaces,
                gpuMemoryTypes
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new GpuDto());

        Log.i("Open creating page for gpu");
        return "product/gpu/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") GpuDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new GpuInputViewModel(
                    createBaseViewModel("Добавление видеокарты"),
                    gpuInterfaces,
                    gpuMemoryTypes
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/gpu/create";
        }

        var id = service.create(form);

        Log.i("Create new gpu with id: " + id);
        return "redirect:/product/gpu/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new GpuInputViewModel(
                createBaseViewModel("Редактирование видеокарты"),
                gpuInterfaces,
                gpuMemoryTypes
        );
        var form = service.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        Log.i("Open editing page for gpu with id: " + id);
        return "product/gpu/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") GpuDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new GpuInputViewModel(
                    createBaseViewModel("Редактирование видеокарты"),
                    gpuInterfaces,
                    gpuMemoryTypes
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/gpu/edit";
        }

        service.create(form);

        Log.i("Edit gpu with id: " + id);
        return "redirect:/product/gpu/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        service.remove(id);

        Log.i("Remove gpu with id: " + id);
        return "redirect:/product/gpu";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
