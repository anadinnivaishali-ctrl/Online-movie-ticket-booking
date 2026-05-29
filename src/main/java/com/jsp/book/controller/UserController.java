package com.jsp.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.book.dto.LoginDto;
import com.jsp.book.dto.UserDto;
import com.jsp.book.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userDto") UserDto userDto,
                               BindingResult result,
                               RedirectAttributes attributes) {
        return userService.register(userDto, result, attributes);
    }

    @GetMapping("/otp")
    public String showOtpPage() {
        return "otp";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute LoginDto dto,
                            RedirectAttributes ra,
                            HttpSession session) {
        return userService.login(dto, ra, session);
    }

    @GetMapping("/logout")
    public String logoutUser(HttpSession session, RedirectAttributes ra) {
        return userService.logout(session, ra);
    }

    @GetMapping({"/", "/main"})
    public String showHomePage(ModelMap map) {
        return userService.loadMain(map);
    }
}