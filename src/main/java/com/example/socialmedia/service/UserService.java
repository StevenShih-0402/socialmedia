package com.example.socialmedia.service;

import com.example.socialmedia.Utils.JwtUtils;
import com.example.socialmedia.Utils.TransferUtils;
import com.example.socialmedia.controller.rq.UserRegisterRq;
import com.example.socialmedia.dao.UserDao;
import com.example.socialmedia.dto.UserRegisterDto;
import com.example.socialmedia.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final TransferUtils transferUtils;

    public String register(UserRegisterRq userRegisterRq){
        UserRegisterDto userRegisterDto = transferUtils.registerRqToDto(userRegisterRq);

        UserEntity createdUser = userDao.register(userRegisterDto);
        return jwtUtils.generateToken(createdUser.getId());
    }
}
