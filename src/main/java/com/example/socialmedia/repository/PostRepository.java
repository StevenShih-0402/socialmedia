package com.example.socialmedia.repository;

import com.example.socialmedia.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    @Query(
            value = "SELECT * FROM POSTS ORDER BY UPDATED_AT DESC ",
            nativeQuery = true
    )
    Page<PostEntity> findPagePosts(Pageable pageable);
}
