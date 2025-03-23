package com.example.socialmedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "POSTS")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @Column(name = "POST_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_gen")
    @SequenceGenerator(name = "post_seq_gen", sequenceName = "SEQ_POSTS", allocationSize = 1)
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED_AT", insertable = false, updatable = false)
    private LocalDateTime createdAt;
}
