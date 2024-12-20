package vn.hoidanit.jobhunter.util.mapper;

import org.springframework.stereotype.Component;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.dto.UserCreateDTO;
import vn.hoidanit.jobhunter.dto.UserDetailDTO;
import vn.hoidanit.jobhunter.dto.UserUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    // Handle convert user to user dto (created)
    public UserCreateDTO mapCreatedUserToDTO(User user) {
        return new UserCreateDTO(
                user.getId(), user.getName(),
                user.getEmail(), user.getAge(),
                user.getGender(), user.getAddress(),
                user.getCreateAt());
    }

    // Handle convert user to user dto (updated)
    public UserUpdateDTO mapUpdatedUserToDTO(User user) {
        return new UserUpdateDTO(
                user.getId(), user.getName(),
                user.getAge(), user.getGender(),
                user.getAddress(), user.getUpdateAt());
    }

    // Handle convert user to user dto (detail)
    public UserDetailDTO mapDetailUserToDTO(User user) {
        return new UserDetailDTO(
                user.getId(), user.getName(),
                user.getEmail(), user.getAge(),
                user.getGender(), user.getAddress(),
                user.getCreateAt(), user.getUpdateAt());
    }

    // Handle convert user list to user dto list (detail)
    public List<UserDetailDTO> mapDetailUserListToDTO(List<User> userList) {
        List<UserDetailDTO> userDetailDTOList = new ArrayList<>();
        for (User user : userList) {
            userDetailDTOList.add(this.mapDetailUserToDTO(user));
        }
        return userDetailDTOList;
    }
}
