package com.example.socialmedia.controller.rq.comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentQueryRq {

    @NotNull
    private Integer postId;
}
