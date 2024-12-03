package com.example.pcbuilder.controller.build;

import com.example.pcbuilder.common.mapper.Mapper;
import com.example.pcbuilder.service.build.contract.BuildService;
import com.example.pcbuilder.service.TagService;
import edu.rutmiit.example.pcbuildercontracts.controllers.build.BuildController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildDto;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildForm;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildListViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.build.viewmodel.BuildViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
            @ModelAttribute("filter") BuildFilter buildFilter,
            Model model
    ) {
        var filter = new BuildFilter(
                buildFilter.page() > 0 ? buildFilter.page() : 1,
                buildFilter.size() > 0 ? buildFilter.size() : 1,
                buildFilter.ownerName(),
                buildFilter.tags(),
                buildFilter.isDescRate(),
                buildFilter.isDescCost(),
                buildFilter.rateRange(),
                buildFilter.priceRange()
        );
        var availableTags = tagService.getAvailableTags();
        var builds = buildService.getAllByFilter(filter)
                .map((it) -> Mapper.createTypeMap(BuildDto.class, BuildViewModel.class).map(it))
                .toList();
        var viewModel = new BuildListViewModel(
                createBaseViewModel("Подбор сборок"),
                availableTags,
                builds
        );

        model.addAttribute("model", viewModel);
        model.addAttribute("filter", filter);

        return "build-list";
    }

    @Override
    public String detail(UUID id, Model model) {
        return null;
    }

    @Override
    public String create(BuildForm form, BindingResult bindingResult, Model model) {
        return null;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
