package com.example.socialmedia.controller.rq;

import com.example.socialmedia.valid.phone.Phone;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRq {
    @Phone
    @NotBlank
    private String phone;

    @NotBlank
    private String password;
}
