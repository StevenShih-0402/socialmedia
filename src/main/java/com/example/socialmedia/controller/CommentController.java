package com.example.socialmedia.controller;

import com.example.socialmedia.controller.rq.comment.CommentCreateRq;
import com.example.socialmedia.controller.rq.comment.CommentQueryRq;
import com.example.socialmedia.dto.comment.CommentDto;
import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.response.ResponseMessage;
import com.example.socialmedia.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/comment", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {

    private final CommentService commentService;

    // 發布留言
    @PostMapping(value = "/create-comment")
    public ResponseEntity<ResponseMessage<CommentDto>> create(@Valid @RequestBody CommentCreateRq commentCreateRq){
        CommentDto newData = commentService.create(commentCreateRq);

        return ResponseEntity.ok(ResponseMessage.<CommentDto>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(newData)
                .build()
        );
    }

    // 查詢貼文所有留言
    @PostMapping(value = "/query-comments")
    public ResponseEntity<ResponseMessage<List<CommentDto>>> queryByPostId(@Valid @RequestBody CommentQueryRq commentQueryRq){
        List<CommentDto> allDatas = commentService.query(commentQueryRq);

        return ResponseEntity.ok(ResponseMessage.<List<CommentDto>>builder()
                .code(ResponseMessageEnum.SUCCESS.getResponse())
                .message(ResponseMessageEnum.SUCCESS.toString())
                .data(allDatas)
                .build()
        );
    }
}
