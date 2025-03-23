package com.example.socialmedia.Utils;

import com.example.socialmedia.dao.UserDao;
import com.example.socialmedia.dto.UserLoginDto;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.exeption.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidUtils {

    private final UserDao userDao;

    public void phoneIsExist(String phone){
        if(userDao.existsByPhone(phone)){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "電話號碼已被註冊過。");
        }
    }

    public void phoneIsNotExist(String phone){
        if(!userDao.existsByPhone(phone)){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "電話號碼尚未被註冊過。");
        }
    }

    public void passwordIsCorrect(UserLoginDto userLoginDto){
        String encodedPassword = userDao.findUserByPhone(userLoginDto.getPhone()).getPassword();

        if(!PasswordUtils.verifyPassword(userLoginDto.getPassword(), encodedPassword)){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "密碼不正確。");
        }
    }
}
