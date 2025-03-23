package com.example.socialmedia.dao;

import com.example.socialmedia.dto.user.UserRegisterDto;
import com.example.socialmedia.entity.UserEntity;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.exeption.CustomException;
import com.example.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;

    public UserEntity findUserById(Integer id){
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseMessageEnum.DATABASE_ERROR.getResponse(), "找不到 id 對應的資料。"));
    }

    public UserEntity findUserByPhone(String phone){
        return userRepository.findByPhone(phone);
    }

    public Boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }

    public UserEntity register(UserRegisterDto userRegisterDto){
        UserEntity userData = registerDtoToEntity(userRegisterDto);
        return userRepository.save(userData);
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
}
