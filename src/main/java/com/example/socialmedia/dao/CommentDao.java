package com.example.socialmedia.dao;

import com.example.socialmedia.dto.comment.CommentCreateDto;
import com.example.socialmedia.dto.post.PostCreateDto;
import com.example.socialmedia.entity.CommentEntity;
import com.example.socialmedia.entity.PostEntity;
import com.example.socialmedia.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDao {
    private final CommentRepository commentRepository;

    public CommentEntity createComment(CommentCreateDto commentCreateDto){
        CommentEntity commentData = new CommentEntity();

        commentData.setUserId(commentCreateDto.getUserId());
        commentData.setPostId(commentCreateDto.getPostId());
        commentData.setContent(commentCreateDto.getContent());

        return commentRepository.save(commentData);
    }
}
