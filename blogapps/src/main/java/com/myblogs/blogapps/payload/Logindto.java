package com.myblogs.blogapps.payload;

import lombok.Data;

@Data
public class Logindto {
    private String usernameOrEmail;
    private String password;
}
