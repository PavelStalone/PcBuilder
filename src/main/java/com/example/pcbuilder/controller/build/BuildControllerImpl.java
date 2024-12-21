package com.example.pcbuilder.controller.build;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.TagService;
import com.example.pcbuilder.service.build.contract.BuildService;
import edu.rutmiit.example.pcbuildercontracts.controllers.build.BuildController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildForm;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildDetailsViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/build")
public class BuildControllerImpl implements BuildController {

    private final TagService tagService;
    private final BuildService buildService;

    @Autowired
    public BuildControllerImpl(
            TagService tagService,
            BuildService buildService
    ) {
        this.tagService = tagService;
        this.buildService = buildService;
    }

    @Override
    @GetMapping("/users")
    public String usersBuilds(
            @ModelAttribute("filter") BuildFilter filter,
            BindingResult bindingResult,
            Model model
    ) {
        Log.d("usersBuilds called - filter: " + filter);

        var filterIn = new BuildFilter(
                filter.page() != null ? filter.page() : 1,
                filter.size() != null ? filter.size() : 10,
                filter.ownerName(),
                filter.tags(),
                filter.sortType(),
                filter.rateLower(),
                filter.rateUpper(),
                filter.costLower(),
                filter.costUpper()
        );

        var availableTags = tagService.getAvailableTags();
        var result = buildService.getAllByFilter(filterIn);
        var builds = result
                .list()
                .stream()
                .map((it) -> Mapper.createTypeMap(BuildDto.class, BuildViewModel.class).map(it));
        var viewModel = new BuildListViewModel(
                createBaseViewModel("Подбор сборок"),
                availableTags,
                builds.toList(),
                result.pages()
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", bindingResult.hasErrors() ? filter : filterIn);

        Log.i("Open users builds on %d page", filterIn.page());
        return "build/user-builds";
    }

    @Override
    @GetMapping
    public String list(BuildFilter filter, BindingResult bindingResult, Model model) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public String detail(
            UUID id,
            Model model
    ) {
        Log.d("detail called - id: " + id);

        var details = new BuildDetailsViewModel(
                createBaseViewModel("Сборка"),
                buildService.getById(id).orElse(null)
        );

        model.addAttribute("model", details);

        Log.i("Open detail for build with id: " + id);
        return "build/details";
    }

    @Override
    @GetMapping("/create")
    public String createForm(Model model) {
        return null;
    }

    @Override
    @PostMapping("/create")
    public String create(
            @ModelAttribute("form") BuildForm form,
            BindingResult bindingResult,
            Model model
    ) {
        return null;
    }

    @Override
    @GetMapping("/{id}/edit")
    public String editForm(UUID id, Model model) {
        return null;
    }

    @Override
    @PostMapping("/{id}/edit")
    public String edit(
            UUID id,
            @ModelAttribute("form") BuildForm form,
            BindingResult bindingResult,
            Model model) {
        return null;
    }

    @Override
    @GetMapping("/{id}/delete")
    public String delete(UUID id, Model model) {
        return null;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
