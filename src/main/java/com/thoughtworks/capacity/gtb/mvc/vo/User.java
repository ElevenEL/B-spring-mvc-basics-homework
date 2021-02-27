package com.thoughtworks.capacity.gtb.mvc.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@AllArgsConstructor
public class User {

    private final String id = UUID.randomUUID().toString();

    @NotBlank(message = "用户名不为空")
    @Size(min = 3, max = 10, message = "用户名不合法")
    @Pattern(regexp = "\\w+$", message = "用户名不合法")
    private String username;

    @NotBlank(message = "密码是不为空")
    @Size(min = 5, max = 12, message = "密码不合法")
    private String password;

    @Email(message = "邮箱地址不合法")
    private String email;
}
