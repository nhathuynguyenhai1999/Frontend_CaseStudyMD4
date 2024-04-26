package com.example.casestudylibrary.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResDto {
    private Long id;
    private String fullName;
    private String phone;
    private String username;
    private String password;
    private String email;
}
