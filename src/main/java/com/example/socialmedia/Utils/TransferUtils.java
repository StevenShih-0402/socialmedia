package com.example.socialmedia.Utils;

import com.example.socialmedia.controller.rq.UserRegisterRq;
import com.example.socialmedia.dto.UserRegisterDto;
import com.example.socialmedia.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransferUtils {

    public UserRegisterDto registerRqToDto(UserRegisterRq userRegisterRq){

        return UserRegisterDto.builder()
                .userName(userRegisterRq.getUserName())
                .email(userRegisterRq.getEmail())
                .password(PasswordUtils.hashPassword(userRegisterRq.getPassword()))
                .biography(userRegisterRq.getBiography())
                .phone(userRegisterRq.getPhone())
                .build();
    }

    public UserEntity registerDtoToEntity(UserRegisterDto userRegisterDto){
        return UserEntity.builder()
                .userName(userRegisterDto.getUserName())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .biography(userRegisterDto.getBiography())
                .phone(userRegisterDto.getPhone())
                .build();
    }

    public UserRegisterDto registerEntityToDto(UserEntity userEntity){
        return UserRegisterDto.builder()
                        .id(userEntity.getId())
                        .userName(userEntity.getUserName())
                        .email(userEntity.getEmail())
                        .password(userEntity.getPassword())
                        .biography(userEntity.getBiography())
                        .build();
    }

}
