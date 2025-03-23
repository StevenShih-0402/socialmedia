package com.example.socialmedia.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {
    private Integer id;
    private String userName;
    private String email;
    private String password;
    private String biography;
    private String phone;
}
