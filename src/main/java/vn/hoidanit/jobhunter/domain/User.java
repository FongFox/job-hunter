package vn.hoidanit.jobhunter.domain;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import vn.hoidanit.jobhunter.util.constant.GenderEnum;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String password;
    private int age;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender; //MALE/FEMALE/OTHER

    private String address;
    private String refreshToken;

    @Column(name = "create_at")
    private Instant createAt;

    @Column(name = "update_at")
    private Instant updateAt;

    @Column(name = "create_by")
    private String createBy;
    @Column(name = "update_by")
    private String updateBy;

    public User() {
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
