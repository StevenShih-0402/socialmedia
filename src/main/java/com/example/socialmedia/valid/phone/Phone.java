package com.example.socialmedia.valid.phone;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PhoneValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Phone {
    String message() default "行動電話必須為台灣電話號碼格式 (09開頭，共10個數字)。";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
