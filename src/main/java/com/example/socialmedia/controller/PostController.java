package com.example.socialmedia.controller;

import com.example.socialmedia.controller.rq.post.PostCreateRq;
import com.example.socialmedia.controller.rq.post.PostDeleteRq;
import com.example.socialmedia.controller.rq.post.PostUpdateRq;
import com.example.socialmedia.dto.post.PostDto;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.response.ResponseMessage;
import com.example.socialmedia.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/post", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    private final PostService postService;

    // 發布文章
    @PostMapping(value = "/create-post")
    public ResponseEntity<ResponseMessage<PostDto>> create(@Valid @RequestBody PostCreateRq postCreateRq){
        PostDto newData = postService.create(postCreateRq);

        return ResponseEntity.ok(ResponseMessage.<PostDto>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(newData)
                .build()
        );
    }

    // 查詢所有文章
    @GetMapping(value = "/query-posts")
    public ResponseEntity<ResponseMessage<List<PostDto>>> query(){
        List<PostDto> allDatas = postService.query();

        return ResponseEntity.ok(ResponseMessage.<List<PostDto>>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(allDatas)
                .build()
        );
    }

    // 編輯文章
    @PutMapping(value = "/update-post")
    public ResponseEntity<ResponseMessage<PostDto>> update(@Valid @RequestBody PostUpdateRq postUpdateRq){
        PostDto updateData = postService.update(postUpdateRq);

        return ResponseEntity.ok(ResponseMessage.<PostDto>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(updateData)
                .build()
        );
    }

    // 刪除文章
    @DeleteMapping(value = "/delete-post")
    public ResponseEntity<ResponseMessage<String>> delete(@Valid @RequestBody PostDeleteRq postDeleteRq){
        postService.delete(postDeleteRq);

        return ResponseEntity.ok(ResponseMessage.<String>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(null)
                .build()
        );
    }
}
