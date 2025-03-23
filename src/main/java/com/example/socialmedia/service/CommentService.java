package com.example.socialmedia.service;

import com.example.socialmedia.Utils.JwtUtils;
import com.example.socialmedia.controller.rq.comment.CommentCreateRq;
import com.example.socialmedia.dao.CommentDao;
import com.example.socialmedia.dao.UserDao;
import com.example.socialmedia.dto.comment.CommentCreateDto;
import com.example.socialmedia.dto.comment.CommentDto;
import com.example.socialmedia.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentDao commentDao;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;

    public CommentDto create(CommentCreateRq commentCreateRq){
        // 驗證使用者是否有帶合法的 Token
        jwtUtils.validateToken();

        CommentCreateDto commentCreateDto = CommentCreateDto.builder()
                .postId(commentCreateRq.getPostId())
                .userId(jwtUtils.getLoginUserId())
                .content(commentCreateRq.getContent())
                .build();

        CommentEntity newData = commentDao.createComment(commentCreateDto);

        return CommentDto.builder()
                .userName(userDao.findUserById(newData.getUserId()).getUserName())
                .postId(newData.getPostId())
                .content(newData.getContent())
                .build();

    }
}
