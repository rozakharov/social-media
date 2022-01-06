package ru.otus.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.socialnetwork.dto.User;
import ru.otus.socialnetwork.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("register")
public class RegistrationController {

  private final UserService userService;

  @GetMapping
  public String showRegistrationForm(Model model) {
    model.addAttribute("user", new User());
    return "register";
  }

  @PostMapping
  public String processRegister(User user) {
    if (userService.registerNewUser(user) == null) {
      throw new RuntimeException("Can't register new user");
    }
    return "register-success";
  }
}