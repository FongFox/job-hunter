package vn.hoidanit.jobhunter.controller;

import java.util.List;
import java.util.Optional;

import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.dto.RestfulPaginationDTO;
import vn.hoidanit.jobhunter.dto.UserCreateDTO;
import vn.hoidanit.jobhunter.dto.UserDetailDTO;
import vn.hoidanit.jobhunter.dto.UserUpdateDTO;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;
import vn.hoidanit.jobhunter.util.exception.EmailExistException;
import vn.hoidanit.jobhunter.util.exception.IdInvalidException;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.util.mapper.UserMapper;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
  private final UserService userService;
  private final UserMapper userMapper;

  public UserController(UserService userService, UserMapper userMapper) {
    this.userService = userService;
      this.userMapper = userMapper;
  }

  @GetMapping("")
  @ApiMessage("Fetch All Users")
  public ResponseEntity<RestfulPaginationDTO> fetchAllUser(@Filter Specification<User> specification, Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.handleFetchAllUsersWithPagination(specification, pageable));
  }

  @GetMapping("{id}")
  public ResponseEntity<Object> fetchUserById(@PathVariable int id) throws IdInvalidException {
    if(!this.userService.handleCheckUserExistsById(id)) {
      throw  new IdInvalidException("User Id Not Found!");
    }

    UserDetailDTO responseUser = this.userMapper.mapDetailUserToDTO(this.userService.handleFetchUserById(id));

    if (responseUser == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    return ResponseEntity.status(HttpStatus.OK).body(responseUser);
  }

  @PostMapping("")
  @ApiMessage("Create a new User")
  public ResponseEntity<Object> createNewUser(@Valid @RequestBody User requestUser) throws EmailExistException {
    // throw exception when email existed
    if(this.userService.handleCheckUserExistsByEmail(requestUser.getEmail())){
      throw new EmailExistException("Email have been existed!");
    }

    User user = new User();
    user.setName(requestUser.getName());
    user.setEmail(requestUser.getEmail());
    user.setPassword(requestUser.getPassword());
    user.setGender(requestUser.getGender());
    user.setAddress(requestUser.getAddress());
    user.setAge(requestUser.getAge());

    UserCreateDTO responseUser = this.userMapper.mapCreatedUserToDTO(this.userService.handleCreateUser(user));

    return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
  }

  @PutMapping("")
  public ResponseEntity<Object> updateUser(@RequestBody User requestUser) throws IdInvalidException {
    if(!this.userService.handleCheckUserExistsById(requestUser.getId())) {
      throw  new IdInvalidException("User Id Not Found!");
    }

    UserUpdateDTO responseUser = this.userMapper.mapUpdatedUserToDTO(this.userService.handleUpdateUser(requestUser));

    if (responseUser == null) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // return responseUser;
    return ResponseEntity.status(HttpStatus.OK).body(responseUser);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable int id) throws IdInvalidException {
    if(!this.userService.handleCheckUserExistsById(id)) {
      throw  new IdInvalidException("User Id Not Found!");
    }

    this.userService.handleDeleteUser(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
  }
}
