package vn.hoidanit.jobhunter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.hoidanit.jobhunter.util.constant.GenderEnum;

import java.time.Instant;

@Getter
@Setter
@ToString
public class UserDetailDTO {
    private int id;
    private String name;
    private String email;
    private int age;
    private GenderEnum gender; //MALE/FEMALE/OTHER
    private String address;
    private Instant createAt;
    private Instant updateAt;

    public UserDetailDTO() {
    }

    public UserDetailDTO(int id, String name, String email, int age, GenderEnum gender, String address, Instant createAt, Instant updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
