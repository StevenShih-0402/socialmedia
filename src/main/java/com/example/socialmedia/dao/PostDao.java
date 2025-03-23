package com.example.socialmedia.dao;

import com.example.socialmedia.dto.post.PostCreateDto;
import com.example.socialmedia.dto.post.PostDeleteDto;
import com.example.socialmedia.dto.post.PostUpdateDto;
import com.example.socialmedia.entity.PostEntity;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.exeption.CustomException;
import com.example.socialmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostDao {
    private final PostRepository postRepository;

    public PostEntity findPostById(Integer id){
        return postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseMessageEnum.DATABASE_ERROR.getResponse(), "找不到 id 對應的資料。"));
    }

    public PostEntity createPost(PostCreateDto postCreateDto){
        PostEntity postData = new PostEntity();

        postData.setUserId(postCreateDto.getUserId());
        postData.setContent(postCreateDto.getContent());

        return postRepository.save(postData);
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
