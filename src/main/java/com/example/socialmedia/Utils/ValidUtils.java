package com.example.socialmedia.Utils;

import com.example.socialmedia.dao.PostDao;
import com.example.socialmedia.dao.UserDao;
import com.example.socialmedia.dto.user.UserLoginDto;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.exeption.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidUtils {

    private final UserDao userDao;
    private final PostDao postDao;

    // 電話號碼已被註冊過
    public void phoneIsExist(String phone){
        if(userDao.existsByPhone(phone)){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "電話號碼已被註冊過。");
        }
    }

    // 電話號碼還沒被註冊過
    public void phoneIsNotExist(String phone){
        if(!userDao.existsByPhone(phone)){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "電話號碼尚未被註冊過。");
        }
    }

    // 密碼正確
    public void passwordIsCorrect(UserLoginDto userLoginDto){
        String encodedPassword = userDao.findUserByPhone(userLoginDto.getPhone()).getPassword();

        if(!PasswordUtils.verifyPassword(userLoginDto.getPassword(), encodedPassword)){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "密碼不正確。");
        }
    }

    // 是用戶自己發布的貼文
    public void isOwnPost(Integer postId, Integer userId){
        if(!(postDao.findPostById(postId).getUserId().equals(userId))){
            throw new CustomException(ResponseMessageEnum.AUTH_ERROR.getResponse(), "這篇貼文的發布者不是目前登入的用戶。");
        }
    }
}
