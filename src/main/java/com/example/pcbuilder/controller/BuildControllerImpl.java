package com.example.pcbuilder.controller;

import edu.rutmiit.example.pcbuildercontracts.controllers.build.BuildController;
import edu.rutmiit.example.pcbuildercontracts.viewmodel.build.BuildFilter;
import edu.rutmiit.example.pcbuildercontracts.viewmodel.build.BuildForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/build")
public class BuildControllerImpl implements BuildController {

    @Override
    @GetMapping("/users")
    public String usersBuilds(BuildFilter buildFilter, Model model) {
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
