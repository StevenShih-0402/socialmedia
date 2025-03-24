package com.example.socialmedia.service;

import com.example.socialmedia.Utils.JwtUtils;
import com.example.socialmedia.Utils.ValidUtils;
import com.example.socialmedia.controller.rq.post.PostCreateRq;
import com.example.socialmedia.controller.rq.post.PostDeleteRq;
import com.example.socialmedia.controller.rq.post.PostUpdateRq;
import com.example.socialmedia.dao.PostDao;
import com.example.socialmedia.dao.UserDao;
import com.example.socialmedia.dto.post.PostCreateDto;
import com.example.socialmedia.dto.post.PostDeleteDto;
import com.example.socialmedia.dto.post.PostDto;
import com.example.socialmedia.dto.post.PostUpdateDto;
import com.example.socialmedia.entity.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDao postDao;
    private final UserDao userDao;
    private final JwtUtils jwtUtils;
    private final ValidUtils validUtils;

    @Transactional
    public PostDto create(PostCreateRq postCreateRq){
        // 驗證使用者是否有帶合法的 Token
        jwtUtils.validateToken();

        PostCreateDto postCreateDto = new PostCreateDto();
        postCreateDto.setUserId(jwtUtils.getLoginUserId());
        postCreateDto.setContent(postCreateRq.getContent());

        PostEntity newData = postDao.createPost(postCreateDto);
        return postEntityToDto(newData);
    }

    public List<PostDto> query(){
        // 驗證使用者是否有帶合法的 Token
        jwtUtils.validateToken();

        List<PostEntity> allPostData = postDao.queryAllPosts();

        return allPostData.stream()
                .sorted(Comparator.comparing(PostEntity::getCreatedAt).reversed()) // 由最近到最遠
                .map(this::postEntityToDto)  // .map(entity -> this.postEntityToDto(entity))
                .toList();
    }

    public PostDto update(PostUpdateRq postUpdateRq){
        // 驗證使用者是否有帶合法的 Token
        jwtUtils.validateToken();

        PostUpdateDto postUpdateDto = new PostUpdateDto();
        postUpdateDto.setId(postUpdateRq.getId());
        postUpdateDto.setUserId(jwtUtils.getLoginUserId());
        postUpdateDto.setContent(postUpdateRq.getContent());

        // 使用者只能修改自己的貼文
        validUtils.isOwnPost(postUpdateDto.getId(), jwtUtils.getLoginUserId());

        PostEntity updateData = postDao.updatePost(postUpdateDto);
        return postEntityToDto(updateData);
    }

    public void delete(PostDeleteRq postDeleteRq){
        // 驗證使用者是否有帶合法的 Token
        jwtUtils.validateToken();

        PostDeleteDto postDeleteDto = new PostDeleteDto();
        postDeleteDto.setId(postDeleteRq.getId());

        // 使用者只能刪除自己的貼文
        validUtils.isOwnPost(postDeleteDto.getId(), jwtUtils.getLoginUserId());

        postDao.deletePost(postDeleteDto);
    }

    public PostDto postEntityToDto(PostEntity postEntity){
        return PostDto.builder()
                .id(postEntity.getId())
                .userId(postEntity.getUserId())
                .userName(userDao.findUserById(postEntity.getUserId()).getUserName())
                .content(postEntity.getContent())
                .build();
    }
}
