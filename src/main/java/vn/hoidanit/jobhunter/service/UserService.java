package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> handleFetchAllUser() {
        return userRepository.findAll();
    }

    public User handleFetchUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User handleFetchUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        return userRepository.save(user);
    }

    public User handleCreateUser(User user) {
        String userHashPassword = passwordEncoder.encode(user.getPassword());

        User tempUser = new User();
        tempUser.setName(user.getName());
        tempUser.setEmail(user.getPassword());
        tempUser.setPassword(userHashPassword);

        return userRepository.save(tempUser);
    }

    public User handleUpdateUser(User user) {
        User dbUser = handleFetchUserById(user.getId());

        if (dbUser == null) {
            return null;
        }

        dbUser.setEmail(user.getEmail());
        dbUser.setName(user.getName());
        dbUser.setPassword(user.getPassword());
        userRepository.save(dbUser);

        return dbUser;
    }

    public void handleDeleteUser(int id) {
        userRepository.deleteById(id);
    }
}
