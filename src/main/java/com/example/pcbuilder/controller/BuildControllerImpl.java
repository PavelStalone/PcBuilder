package com.example.pcbuilder.controller;

import com.example.pcbuilder.service.BuildService;
import edu.rutmiit.example.pcbuildercontracts.controllers.build.BuildController;
import edu.rutmiit.example.pcbuildercontracts.dto.build.BuildForm;
import edu.rutmiit.example.pcbuildercontracts.dto.build.filter.BuildFilter;
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

    private final BuildService buildService;

    @Autowired
    public BuildControllerImpl(BuildService buildService) {
        this.buildService = buildService;
    }

    @Override
    @GetMapping("/users")
    public String usersBuilds(
            @ModelAttribute("filter") BuildFilter buildFilter,
            BindingResult bindingResult,
            Model model
    ) {
        var builds = buildService.getAllBuildsByFilter(buildFilter);

        model.addAttribute("builds", builds.toSet());

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
}
