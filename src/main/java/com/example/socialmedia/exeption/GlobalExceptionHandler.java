package com.example.socialmedia.exeption;

import com.example.socialmedia.enums.ResponseMessageEnum;
import com.example.socialmedia.response.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Jwt 身分驗證錯誤
    @ExceptionHandler(JwtTokenException.class)
    public ResponseEntity<ResponseMessage<String>> handleJwtTokenException(JwtTokenException ex) {
        return ResponseEntity.ok(ResponseMessage.<String>builder()
                        .code(ResponseMessageEnum.AUTH_ERROR.getResponse())
                        .message(ex.getMessage())
                        .data(null)
                        .build()
                );
    }

    // Valid 驗證格式錯誤
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessage<String>> methodArgNotValidException(MethodArgumentNotValidException ex) {
        return ResponseEntity.ok(ResponseMessage.<String>builder()
                        .code(ResponseMessageEnum.INPUT_ERROR.getResponse())
                        .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())  // BindingResult 包含所有驗證失敗的錯誤訊息，AllErrors 是一個 List，包含所有錯誤訊息物件，這邊取第一個錯誤(通常只有一個)，DefaultMessage 是預設的錯誤訊息。
                        .data(null)
                        .build()
                );
    }
}
