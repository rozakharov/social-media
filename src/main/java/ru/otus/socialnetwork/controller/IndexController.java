package ru.otus.socialnetwork.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

  @GetMapping("")
  public String viewHomePage() {
    return "index";
  }
}
