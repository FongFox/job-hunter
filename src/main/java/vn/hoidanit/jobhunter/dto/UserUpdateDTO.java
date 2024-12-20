package vn.hoidanit.jobhunter.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.hoidanit.jobhunter.util.constant.GenderEnum;

import java.time.Instant;

@Getter
@Setter
@ToString
public class UserUpdateDTO {
    private int id;
    private String name;
    private String email;
    private int age;
    private GenderEnum gender; //MALE/FEMALE/OTHER
    private String address;
    private Instant updateAt;

    public UserUpdateDTO() {
    }

    public UserUpdateDTO(int id, String name, String email, int age, GenderEnum gender, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public UserUpdateDTO(int id, String name, String email, int age, GenderEnum gender, String address, Instant updateAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.updateAt = updateAt;
    }

    public UserUpdateDTO(int id, String name, int age, GenderEnum gender, String address, Instant updateAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.updateAt = updateAt;
    }
}
