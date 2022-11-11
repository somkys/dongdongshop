package com.dongdongshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("comment_user")
public class CommentVO implements Serializable {
    //主键id
    @Id
    private String id;

    //评价人id
    @Field("user_id")
    private Long userId;

    //商品id
    @Field("goods_id")
    private Long goodsId;

    //商品图片地址
    @Field("img")
    private String img;

    //商品评论内容
    @Field("context")
    private String context;

    //评论时间
    @Field("commentDate")
    private LocalDateTime commentDate;

    //评论等级1-5
    @Field("level")
    private Integer level;

    //商品名称
    @Field("goods_name")
    private String goodsName;
}
