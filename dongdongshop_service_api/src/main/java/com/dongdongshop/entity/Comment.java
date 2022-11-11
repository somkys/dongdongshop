package com.dongdongshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    //主键id
    private String id;

    //评价人id
    private Long userId;

    //商品id
    private Long goodsId;

    //商品图片地址
    private String img;

    //商品评论内容
    private String context;

    //评论时间
    private LocalDateTime CommentDate;

    //评论等级1-5
    private Integer level;

    //商品名称
    private String goodsName;


}
