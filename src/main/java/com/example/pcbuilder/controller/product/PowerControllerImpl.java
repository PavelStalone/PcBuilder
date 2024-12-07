package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.PowerService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.PowerController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.PowerDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.PowerFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.power.PowerDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.power.PowerInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.power.PowerListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.power.PowerViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

import static com.example.pcbuilder.common.fake.FakeData.powerFormFactors;

@Controller
@RequestMapping("/product/power")
public class PowerControllerImpl implements PowerController {

    private final PowerService service;

    @Autowired
    public PowerControllerImpl(PowerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") PowerFilter filter,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new PowerFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper(),
                filter.powerLower(),
                filter.powerUpper()
        );
        var pages = service.getAllByFilter(filterIn)
                .map((it) -> Mapper.createTypeMap(PowerDto.class, PowerViewModel.class).map(it));

        var viewModel = new PowerListViewModel(
                createBaseViewModel("Блоки питания"),
                pages.toList(),
                pages.getTotalPages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", filterIn);

        return "product/power/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new PowerDetailsViewModel(
                createBaseViewModel("Блок питания"),
                service.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        return "product/power/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new PowerInputViewModel(
                createBaseViewModel("Добавление блока питания"),
                powerFormFactors
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new PowerDto());

        return "product/power/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") PowerDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new PowerInputViewModel(
                    createBaseViewModel("Добавление блока питания"),
                    powerFormFactors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/power/create";
        }

        var id = service.create(form);

        return "redirect:/product/power/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new PowerInputViewModel(
                createBaseViewModel("Редактирование блока питания"),
                powerFormFactors
        );
        var form = service.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        return "product/power/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") PowerDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new PowerInputViewModel(
                    createBaseViewModel("Редактирование блока питания"),
                    powerFormFactors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/power/edit";
        }

        service.create(form);

        return "redirect:/product/power/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        service.remove(id);

        return "redirect:/product/power";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
