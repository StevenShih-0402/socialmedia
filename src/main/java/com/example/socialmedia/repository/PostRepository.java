package com.example.socialmedia.repository;

import com.example.socialmedia.entity.PostEntity;
import com.example.socialmedia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    @Query(value = "SELECT SEQ_POSTS.NEXTVAL FROM DUAL", nativeQuery = true)
    Integer getNextPostId();

    @Procedure(value = "create_post_and_time_comment")
    void createPostAndTimeComment(@Param("user_id") Integer userId,
                                        @Param("content") String content,
                                        @Param("comment") String comment);
}
