package com.example.user_service.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestUser {
    @NotNull(message = "이메일은 낫널입니다.")
    @Size(min = 2, message = "이메일은 최소 2글자입니다.")
    private String email;

    @NotNull(message = "이름은 낫널입니다.")
    @Size(min = 2, message = "이름은 최소 2글자입니다.")
    private String name;

    @NotNull(message = "비밀번호는 낫널입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8글자입니다.")
    private String pwd;
}
