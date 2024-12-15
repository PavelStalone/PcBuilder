package com.example.pcbuilder.controller.product;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.product.contract.CaseService;
import edu.rutmiit.example.pcbuildercontracts.controllers.product.CaseController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.CaseDto;
import edu.rutmiit.example.pcbuildercontracts.dto.product.filter.CaseFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.pccase.CaseDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.pccase.CaseInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.product.viewmodel.pccase.CaseListViewModel;
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

import static com.example.pcbuilder.common.fake.FakeData.motherboardFormFactors;
import static com.example.pcbuilder.common.fake.FakeData.powerFormFactors;

@Controller
@RequestMapping("/product/case")
public class CaseControllerImpl implements CaseController {

    private final CaseService caseService;

    @Autowired
    public CaseControllerImpl(CaseService caseService) {
        this.caseService = caseService;
    }

    @Override
    @GetMapping
    public String list(
            @ModelAttribute("filter") CaseFilter filter,
            Model model
    ) {
        Log.d("list called - filter: " + filter);

        var filterIn = new CaseFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.model(),
                filter.isDescCost(),
                filter.costLower(),
                filter.costUpper()
        );
        var result = caseService.getAllByFilter(filterIn);
        var pages = result.list()
                .stream()
                .map((it) -> Mapper.createTypeMap(CaseDto.class, CaseViewModel.class).map(it));

        var caseVM = new CaseListViewModel(
                createBaseViewModel("Корпуса"),
                pages.toList(),
                result.pages()
        );

        model.addAttribute("model", caseVM);
        model.addAttribute("filter", filterIn);

        return "product/case/list";
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new CaseDetailsViewModel(
                createBaseViewModel("Корпус"),
                caseService.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        return "product/case/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        Log.d("createForm called");

        var details = new CaseInputViewModel(
                createBaseViewModel("Добавление корпуса"),
                powerFormFactors,
                motherboardFormFactors
        );

        model.addAttribute("model", details);
        model.addAttribute("form", new CaseDto());

        return "product/case/create";
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") CaseDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("Create called - form: " + form);

        if (bindingResult.hasErrors()) {
            var details = new CaseInputViewModel(
                    createBaseViewModel("Добавление корпуса"),
                    powerFormFactors,
                    motherboardFormFactors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/case/create";
        }

        var id = caseService.create(form);

        return "redirect:/product/case/" + id;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(
            UUID id,
            Model model
    ) {
        Log.d("editForm called - id: " + id);

        var details = new CaseInputViewModel(
                createBaseViewModel("Редактирование корпуса"),
                powerFormFactors,
                motherboardFormFactors
        );
        var form = caseService.getById(id).orElse(null);

        model.addAttribute("model", details);
        model.addAttribute("form", form);

        return "product/case/edit";
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") CaseDto form,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("edit called - id: %s, form: %s", id, form);

        if (bindingResult.hasErrors()) {
            var details = new CaseInputViewModel(
                    createBaseViewModel("Редактирование корпуса"),
                    powerFormFactors,
                    motherboardFormFactors
            );

            model.addAttribute("model", details);
            model.addAttribute("form", form);

            return "product/case/edit";
        }

        caseService.create(form);

        return "redirect:/product/case/" + id;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(
            UUID id,
            Model model
    ) {
        Log.d("delete called - id: " + id);

        caseService.remove(id);

        return "redirect:/product/case";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
