package com.example.socialmedia.dao;

import com.example.socialmedia.dto.post.PostCreateDto;
import com.example.socialmedia.dto.post.PostDeleteDto;
import com.example.socialmedia.dto.post.PostQueryDto;
import com.example.socialmedia.dto.post.PostUpdateDto;
import com.example.socialmedia.entity.CommentEntity;
import com.example.socialmedia.entity.PostEntity;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.exeption.CustomException;
import com.example.socialmedia.repository.CommentRepository;
import com.example.socialmedia.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String sysMessage = "<系統訊息> 用戶 " + userDao.findUserById(newData.getUserId()).getUserName() + " 於 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 建立此貼文。";

        CommentEntity commentData = new CommentEntity();
        commentData.setUserId(newData.getUserId());
        commentData.setPostId(newData.getId());
        commentData.setContent(sysMessage);

        commentRepository.save(commentData);

        return newData;
    }

    public List<PostEntity> queryAllPosts(PostQueryDto postQueryDto){
        Pageable pageable = PageRequest.of(postQueryDto.getPageNo(), postQueryDto.getPageSize());
        return postRepository.findPagePosts(pageable).getContent();
    }

    public PostEntity updatePost(PostUpdateDto postUpdateDto){
        PostEntity postData = new PostEntity();

        postData.setId(postUpdateDto.getId());
        postData.setUserId(postUpdateDto.getUserId());
        postData.setContent(postUpdateDto.getContent());

        PostEntity updateData = postRepository.save(postData);

        // 更新貼文時，自動加上一則包含貼文更新時間的留言
        String sysMessage = "<系統訊息> 該貼文於 " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " 由發布者更新此貼文。";

        CommentEntity commentData = new CommentEntity();
        commentData.setUserId(updateData.getUserId());
        commentData.setPostId(updateData.getId());
        commentData.setContent(sysMessage);

        commentRepository.save(commentData);

        return updateData;
    }

    public void deletePost(PostDeleteDto postDeleteDto){
        postRepository.deleteById(postDeleteDto.getId());
    }
}
