package com.example.pcbuilder.controller;

import edu.rutmiit.example.pcbuildercontracts.controllers.home.HomeController;
import edu.rutmiit.example.pcbuildercontracts.viewmodel.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.viewmodel.home.HomeViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeControllerImpl implements HomeController {

    @Override
    @GetMapping
    public String index(Model model) {
        var viewModel = new HomeViewModel(createBaseViewModel("PcBuilder"));
        model.addAttribute("model", viewModel);

        return "index";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
