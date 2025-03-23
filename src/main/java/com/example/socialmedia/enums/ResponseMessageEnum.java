package com.example.socialmedia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessageEnum {
    SUCCESS("0"),
    AUTH_ERROR("1"),
    INPUT_ERROR("2"),
    DATABASE_ERROR("3");

    private final String response;
}
