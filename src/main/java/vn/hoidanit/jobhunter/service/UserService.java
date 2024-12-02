package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.dto.Meta;
import vn.hoidanit.jobhunter.dto.RestfulPaginationDTO;
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
        return this.userRepository.findAll();
    }

    public RestfulPaginationDTO handleFetchAllUsersWithPagination(Specification<User> userSpecification, Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(userSpecification ,pageable);
        RestfulPaginationDTO restfulPaginationDTO = new RestfulPaginationDTO();
        Meta meta = new Meta();

        meta.setPage(userPage.getNumber() + 1);
        meta.setPageSize(userPage.getSize());

        meta.setPages(userPage.getTotalPages());
        meta.setTotal(userPage.getTotalElements());

        restfulPaginationDTO.setMeta(meta);
        restfulPaginationDTO.setResult(userPage.getContent());

        return restfulPaginationDTO;
    }

    public User handleFetchUserById(int id) {
        return this.userRepository.findById(id).orElse(null);
    }

    public User handleFetchUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User handleSaveUser(User user) {
        return this.userRepository.save(user);
    }

    public User handleCreateUser(User user) {
        String userHashPassword = passwordEncoder.encode(user.getPassword());

        User tempUser = new User();
        tempUser.setName(user.getName());
        tempUser.setEmail(user.getPassword());
        tempUser.setPassword(userHashPassword);

        return this.userRepository.save(tempUser);
    }

    public User handleUpdateUser(User user) {
        User dbUser = handleFetchUserById(user.getId());

        if (dbUser == null) {
            return null;
        }

        dbUser.setEmail(user.getEmail());
        dbUser.setName(user.getName());
        dbUser.setPassword(user.getPassword());
        this.userRepository.save(dbUser);

        return dbUser;
    }

    public void handleDeleteUser(int id) {
        this.userRepository.deleteById(id);
    }
}
