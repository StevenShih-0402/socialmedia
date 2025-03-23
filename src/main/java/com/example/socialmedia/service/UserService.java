package com.example.socialmedia.service;

import com.example.socialmedia.Utils.JwtUtils;
import com.example.socialmedia.Utils.PasswordUtils;
import com.example.socialmedia.Utils.ValidUtils;
import com.example.socialmedia.controller.rq.user.UserLoginRq;
import com.example.socialmedia.controller.rq.user.UserRegisterRq;
import com.example.socialmedia.dao.UserDao;
import com.example.socialmedia.dto.user.UserLoginDto;
import com.example.socialmedia.dto.user.UserRegisterDto;
import com.example.socialmedia.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final ValidUtils validUtils;

    public String register(UserRegisterRq userRegisterRq){
        UserRegisterDto userRegisterDto = registerRqToDto(userRegisterRq);

        // 驗證電話是否已被註冊過
        validUtils.phoneIsExist(userRegisterDto.getPhone());

        UserEntity createdUser = userDao.register(userRegisterDto);
        return jwtUtils.generateToken(createdUser.getId());
    }

    public String login(UserLoginRq userLoginRq){
        UserLoginDto userLoginDto = new UserLoginDto();
        BeanUtils.copyProperties(userLoginRq, userLoginDto);

        // 驗證電話是否有被註冊過
        validUtils.phoneIsNotExist(userLoginDto.getPhone());

        // 驗證密碼是否正確
        validUtils.passwordIsCorrect(userLoginDto);

        UserEntity userData = userDao.findUserByPhone(userLoginDto.getPhone());
        return jwtUtils.generateToken(userData.getId());
    }

    public void logout(){
        // 驗證使用者是否有帶合法的 Token
        jwtUtils.validateToken();

        // 從標頭取 Token
        String token = jwtUtils.extractTokenFromAuthHeader();

        // 登出 = 將使用者這次帶的 Token 加入黑名單
        jwtUtils.blacklistToken(token);
    }



    public UserRegisterDto registerRqToDto(UserRegisterRq userRegisterRq){

        return UserRegisterDto.builder()
                .userName(userRegisterRq.getUserName())
                .email(userRegisterRq.getEmail())
                .password(PasswordUtils.hashPassword(userRegisterRq.getPassword()))
                .biography(userRegisterRq.getBiography())
                .phone(userRegisterRq.getPhone())
                .build();
    }
}
