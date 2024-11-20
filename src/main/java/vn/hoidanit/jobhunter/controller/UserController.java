package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/users")
  public String createNewUser(@RequestBody User user) {
    User tempUser = new User();

    tempUser.setEmail(user.getEmail());
    tempUser.setName(user.getName());
    tempUser.setPassword(user.getPassword());

    User responseUser = userService.handleSaveUser(user);

    return "Create User " + responseUser.toString();
  }
}
