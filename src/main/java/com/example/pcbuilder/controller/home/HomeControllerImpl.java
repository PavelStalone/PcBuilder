package com.example.pcbuilder.controller.home;

import com.example.pcbuilder.common.log.Log;
import edu.rutmiit.example.pcbuildercontracts.controllers.home.HomeController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.home.HomeViewModel;
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
        Log.d("index called");

        var viewModel = new HomeViewModel(createBaseViewModel("PcBuilder"));
        model.addAttribute("model", viewModel);

        Log.i("Open main screen");
        return "index";
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
