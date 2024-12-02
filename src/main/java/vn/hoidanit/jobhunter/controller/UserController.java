package vn.hoidanit.jobhunter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.dto.RestfulPaginationDTO;
import vn.hoidanit.jobhunter.util.exception.IdInvalidException;
import vn.hoidanit.jobhunter.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public ResponseEntity<RestfulPaginationDTO> fetchAllUser(
          @RequestParam("current") Optional<String> currentOptional,
          @RequestParam("pageSize") Optional<String> pageSizeOptional
  ) {
    String sCurrent = currentOptional.orElse("");
    String sPageSize = pageSizeOptional.orElse("");

    int pageCurrent = Integer.parseInt(sCurrent) - 1;
    int pageSize = Integer.parseInt(sPageSize);

    Pageable pageable = PageRequest.of(pageCurrent, pageSize);

    return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleFetchAllUsersWithPagination(pageable));
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> fetchUserById(@PathVariable int id) {
    User responseUser = this.userService.handleFetchUserById(id);

    if (responseUser == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    return ResponseEntity.status(HttpStatus.OK).body(responseUser);
  }

  @PostMapping("")
  public ResponseEntity<User> createNewUser(@RequestBody User requestUser) {
    User user = new User(requestUser.getEmail(), requestUser.getName(), requestUser.getPassword());

    User responseUser = this.userService.handleCreateUser(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
  }

  @PutMapping("")
  public ResponseEntity<Object> updateUser(@RequestBody User requestUser) {
    User responseUser = userService.handleUpdateUser(requestUser);

    if (responseUser == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // return responseUser;
    return ResponseEntity.status(HttpStatus.OK).body(responseUser);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) throws IdInvalidException {
    if (id >= 100) {
      throw new IdInvalidException("Id khong lon hon 100!");
    }

    this.userService.handleDeleteUser(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
