package com.example.socialmedia.repository;

import com.example.socialmedia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByPhone(String phone);
    Boolean existsByPhone(String phone);
}
