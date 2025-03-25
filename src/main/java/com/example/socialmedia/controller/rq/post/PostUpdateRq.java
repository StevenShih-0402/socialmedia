package com.example.socialmedia.controller.rq.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRq {

    @NotNull
    private Integer id;

    @NotBlank
    @Size(max = 2000)
    private String content;
}
