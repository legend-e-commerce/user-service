package com.example.user_service.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestLogin {
    @NotNull(message = "email cannot be null")
    @Size(min = 2, message = "email not be less than two char")
    private String email;
    @NotNull(message = "password cannot be null")
    @Size(min = 8, message = "password not be less than eight char")
    private String password;
}
