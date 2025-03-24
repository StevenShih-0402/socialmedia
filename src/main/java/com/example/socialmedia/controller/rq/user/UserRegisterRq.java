package com.example.socialmedia.controller.rq.user;

import com.example.socialmedia.valid.phone.Phone;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRq {

    @NotBlank
    @Size(max = 20)
    private String userName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max = 255)
    private String biography;

    @NotBlank
    @Phone
    private String phone;
}
