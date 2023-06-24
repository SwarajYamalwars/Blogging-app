package com.myblogs.blogapps.payload;

import lombok.Data;

import java.util.Date;

@Data
public class Likedto {


        private Long id;
        private Long userId;
        private Long postId;
        private Date date;


}
