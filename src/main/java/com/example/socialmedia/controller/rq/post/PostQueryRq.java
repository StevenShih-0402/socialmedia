package com.example.socialmedia.controller.rq.post;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostQueryRq {
    @NotNull
    private Integer pageNo;

    @NotNull
    private Integer pageSize;
}
