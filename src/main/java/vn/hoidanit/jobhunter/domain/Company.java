package vn.hoidanit.jobhunter.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name can't blank!")
    private String name;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    private String address;
    private String logo;

    @Column(name = "create_at")
    private Instant createdAt;

    @Column(name = "update_at")
    private Instant updatedAt;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    public Company() {
    }

    public Company(String name, String description, String address, String logo) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.logo = logo;
    }

    @PrePersist
    public void handleBeforeCreate() {
        this.createBy = "hoidanit";
        this.createdAt = Instant.now();
    }
}
