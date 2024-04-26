package com.example.casestudylibrary.domain;

import com.example.casestudylibrary.domain.dto.res.UserResDto;
import com.example.casestudylibrary.domain.enumration.EClass;
import com.example.casestudylibrary.domain.enumration.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private String email;

    @OneToOne
    private Image image;
    @Enumerated(EnumType.STRING)
    private ERole eRole;
    private LocalDate dob;
    @Enumerated(EnumType.STRING)
    private EClass eClass;
    public UserResDto toUserResDto() {
        return new UserResDto(id, fullName, phone, username, password, email);
    }
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
}
