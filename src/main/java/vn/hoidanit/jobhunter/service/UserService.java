package vn.hoidanit.jobhunter.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> handleFetchAllUser() {
    return userRepository.findAll();
  }

  public User handleFetchUserById(int id) {
    return userRepository.findById(id).orElse(null);
  }

  public User handleSaveUser(User user) {
    return userRepository.save(user);
  }

  public void handleDeleteUser(int id) {
    userRepository.deleteById(id);
  }
}
