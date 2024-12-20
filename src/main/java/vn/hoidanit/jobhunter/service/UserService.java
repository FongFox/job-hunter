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
import vn.hoidanit.jobhunter.dto.UserDetailDTO;
import vn.hoidanit.jobhunter.repository.UserRepository;
import vn.hoidanit.jobhunter.util.mapper.UserMapper;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    public List<User> handleFetchAllUser() {
        return this.userRepository.findAll();
    }

    public RestfulPaginationDTO handleFetchAllUsersWithPagination(Specification<User> userSpecification, Pageable pageable) {
        Page<User> userPage = this.userRepository.findAll(userSpecification ,pageable);
        RestfulPaginationDTO restfulPaginationDTO = new RestfulPaginationDTO();
        Meta meta = new Meta();

        meta.setPage(pageable.getPageNumber() + 1);
        meta.setPageSize(pageable.getPageSize());

        meta.setPages(userPage.getTotalPages());
        meta.setTotal(userPage.getTotalElements());

        restfulPaginationDTO.setMeta(meta);

        List<UserDetailDTO> userDetailDTOList = this.userMapper.mapDetailUserListToDTO(userPage.getContent());
        restfulPaginationDTO.setResult(userDetailDTOList);

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
        tempUser.setEmail(user.getEmail());
        tempUser.setPassword(userHashPassword);
        tempUser.setGender(user.getGender());
        tempUser.setAddress(user.getAddress());
        tempUser.setAge(user.getAge());

        return this.userRepository.save(tempUser);
    }

    public User handleUpdateUser(User user) {
        User dbUser = handleFetchUserById(user.getId());
        if (dbUser == null) {
            return null;
        }
//        dbUser.setEmail(user.getEmail());
//        if(user.getPassword() != null && !user.getPassword().isEmpty() && !user.getPassword().isBlank()) {
//            String userHashPassword = passwordEncoder.encode(user.getPassword());
//            dbUser.setPassword(userHashPassword);
//        }
        dbUser.setName(user.getName());
        dbUser.setGender(user.getGender());
        dbUser.setAddress(user.getAddress());
        dbUser.setAge(user.getAge());

        return this.userRepository.save(dbUser);
    }

    public void handleDeleteUser(int id) {
        this.userRepository.deleteById(id);
    }

    public boolean handleCheckUserExistsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public boolean handleCheckUserExistsById(int id) {
        return this.userRepository.existsById(id);
    }
}
