package com.example.socialmedia.controller;

import com.example.socialmedia.controller.rq.UserLoginRq;
import com.example.socialmedia.controller.rq.UserRegisterRq;
import com.example.socialmedia.dto.UserRegisterDto;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.response.ResponseMessage;
import com.example.socialmedia.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    // 使用者註冊
    @PostMapping(value = "/register")
    public ResponseEntity<ResponseMessage<String>> register(@Valid @RequestBody UserRegisterRq userRegisterRq){
        String token = userService.register(userRegisterRq);

        return ResponseEntity.ok(ResponseMessage.<String>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(token)
                .build()
        );
    }

    // 使用者登入
    @PostMapping(value = "/login")
    public ResponseEntity<ResponseMessage<String>> login(@Valid @RequestBody UserLoginRq userLoginRq){
        String token = userService.login(userLoginRq);

        return ResponseEntity.ok(ResponseMessage.<String>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(token)
                .build()
        );
    }

    // 使用者登出
    @PostMapping(value = "/logout")
    public ResponseEntity<ResponseMessage<String>> logout(){
        userService.logout();

        return ResponseEntity.ok(ResponseMessage.<String>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data("用戶已登出。")
                .build()
        );
    }
}
