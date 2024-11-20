package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.*;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/users")
  public String createNewUser(@RequestBody User requestUser) {
    User user = new User(requestUser.getEmail(), requestUser.getName(), requestUser.getPassword());

    User responseUser = userService.handleSaveUser(user);

    return "Create User " + responseUser.toString();
  }

  @DeleteMapping("/users/{id}")
  public String deleteUser(@PathVariable int id) {
    userService.handleDeleteUser(id);

    return "Delete User with " + id;
  }
}
