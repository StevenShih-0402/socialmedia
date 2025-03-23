package com.example.socialmedia.dao;

import com.example.socialmedia.Utils.TransferUtils;
import com.example.socialmedia.dto.UserRegisterDto;
import com.example.socialmedia.entity.UserEntity;
import com.example.socialmedia.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;
    private final TransferUtils transferUtils;

    public UserEntity register(UserRegisterDto userRegisterDto){
        UserEntity userData = transferUtils.registerDtoToEntity(userRegisterDto);
        return userRepository.save(userData);
    }

}
