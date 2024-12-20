package com.example.pcbuilder.controller.user;

import com.example.pcbuilder.common.log.Log;
import com.example.pcbuilder.service.user.contract.AuthService;
import edu.rutmiit.example.pcbuildercontracts.controllers.user.AuthController;
import edu.rutmiit.example.pcbuildercontracts.dto.base.BaseViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.user.UserRegistrationDto;
import edu.rutmiit.example.pcbuildercontracts.dto.user.viewmodel.UserLoginInputViewModel;
import edu.rutmiit.example.pcbuildercontracts.dto.user.viewmodel.UserRegisterInputViewModel;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    @GetMapping("/register")
    public String register(Model model) {
        Log.d("register called");

        model.addAttribute("model", new UserRegisterInputViewModel(createBaseViewModel("Регистрация")));
        model.addAttribute("form", new UserRegistrationDto());

        return "user/register";
    }

    @Override
    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute("form") UserRegistrationDto form,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        Log.d("doRegister called - form: " + form);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegistrationDto", form);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", bindingResult);

            return "redirect:/users/register";
        }

        authService.register(form);

        return "redirect:/users/login";
    }

    @Override
    @GetMapping("/login")
    public String login(Model model) {
        Log.d("login called");

        model.addAttribute("model", new UserLoginInputViewModel(createBaseViewModel("Вход")));

        return "user/login";
    }

    @Override
    @PostMapping("/login-error")
    public String onFailedLogin(
            String username,
            RedirectAttributes redirectAttributes
    ) {
        Log.d("onFailedLogin called - username: " + username);

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("badCredentials", true);

        return "redirect:/user/login";
    }

    @Override
    @GetMapping("/profile")
    public String profile(Principal principal, Model model) {
        return null;
    }

    @Override
    public BaseViewModel createBaseViewModel(String title) {
        return new BaseViewModel(title);
    }
}
