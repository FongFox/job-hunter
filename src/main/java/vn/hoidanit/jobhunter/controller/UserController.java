package vn.hoidanit.jobhunter.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public List<User> fetchAllUser() {
    return userService.handleFetchAllUser();
  }

  @GetMapping("{id}")
  public User fetchUserById(@PathVariable int id) {
    return userService.handleFetchUserById(id);
  }

  @PostMapping("")
  public String createNewUser(@RequestBody User requestUser) {
    User user = new User(requestUser.getEmail(), requestUser.getName(), requestUser.getPassword());

    User responseUser = userService.handleSaveUser(user);

    return "Create " + responseUser.toString();
  }

  @PutMapping("")
  public Object updateUser(@RequestBody User requestUser) {
    if (userService.handleFetchUserById(requestUser.getId()) == null) {
      return "User not found!";
    }

    User user = new User(requestUser.getId(), requestUser.getEmail(), requestUser.getName(), requestUser.getPassword());

    User responseUser = userService.handleSaveUser(user);

    return responseUser;
  }

  @DeleteMapping("{id}")
  public String deleteUser(@PathVariable int id) {
    userService.handleDeleteUser(id);

    return "Delete User with " + id;
  }
}
