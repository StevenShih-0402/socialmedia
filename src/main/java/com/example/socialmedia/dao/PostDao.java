package com.example.socialmedia.dao;

import com.example.socialmedia.dto.post.PostCreateDto;
import com.example.socialmedia.dto.post.PostDeleteDto;
import com.example.socialmedia.dto.post.PostUpdateDto;
import com.example.socialmedia.entity.CommentEntity;
import com.example.socialmedia.entity.PostEntity;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.exeption.CustomException;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PostDao {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserDao userDao;

    public PostEntity findPostById(Integer id){
        return postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseMessageEnum.DATABASE_ERROR.getResponse(), "找不到 id 對應的資料。"));
    }

    public PostEntity createPost(PostCreateDto postCreateDto){
        PostEntity postData = new PostEntity();

        postData.setUserId(postCreateDto.getUserId());
        postData.setContent(postCreateDto.getContent());


        PostEntity newData = postRepository.save(postData);
        // 建立新貼文時，自動加上一則包含貼文建立時間的留言
        String sysMessage = "<系統訊息> 用戶 " + userDao.findUserById(newData.getUserId()).getUserName() + " 於 " + LocalDateTime.now() + " 建立此貼文。";

        CommentEntity commentData = new CommentEntity();
        commentData.setUserId(newData.getUserId());
        commentData.setPostId(newData.getId());
        commentData.setContent(sysMessage);

        commentRepository.save(commentData);

        return newData;
    }

    public List<PostEntity> queryAllPosts(){
        return postRepository.findAll();
    }

    public PostEntity updatePost(PostUpdateDto postUpdateDto){
        PostEntity postData = new PostEntity();

        postData.setId(postUpdateDto.getId());
        postData.setUserId(postUpdateDto.getUserId());
        postData.setContent(postUpdateDto.getContent());

        return postRepository.save(postData);
    }

    public void deletePost(PostDeleteDto postDeleteDto){
        postRepository.deleteById(postDeleteDto.getId());
    }
}
