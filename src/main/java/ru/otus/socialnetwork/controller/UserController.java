package ru.otus.socialnetwork.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.socialnetwork.dto.User;
import ru.otus.socialnetwork.security.MyUserDetails;
import ru.otus.socialnetwork.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public String listUsers() {
    return "users";
  }

  @GetMapping("search")
  public String search(
      @RequestParam String firstName,
      @RequestParam String lastName,
      Model model) {
    model.addAttribute("listUsers", userService.getUsers(firstName, lastName));
    return "users";
  }

  @GetMapping("view/{id}")
  public String view(@PathVariable Long id, Model model) {
    User user = userService.getUser(id);
    model.addAttribute("user", user);
    model.addAttribute("isProfile", id.equals(getLoggedUserId()));
    model.addAttribute("isFriend",
        user.getFriends().stream().anyMatch(friend -> friend.getId().equals(getLoggedUserId())));
    return "users-view";
  }

  @GetMapping("profile")
  public String profile(Model model) {
    return view(getLoggedUserId(), model);
  }

  @PostMapping("make-friends/{friendId}")
  public String makeFriends(@PathVariable Long friendId, Model model) {
    userService.makeFriends(getLoggedUserId(), friendId);
    return view(friendId, model);
  }

  @PostMapping("remove-from-friends/{friendId}")
  public String removeFromFriends(@PathVariable Long friendId, Model model) {
    userService.removeFromFriends(getLoggedUserId(), friendId);
    return view(friendId, model);
  }

  private Long getLoggedUserId() {
    return ((MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
  }
}
