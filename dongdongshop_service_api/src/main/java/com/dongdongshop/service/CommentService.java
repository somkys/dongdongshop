package com.dongdongshop.service;

import com.dongdongshop.entity.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentService {
    boolean insertComment(Comment comment);

    List<Comment> queryComment();

    Comment queryCommentById(String id);

    void updateCommentById(String comment,String id);

    void deleteCommentById(String id);

    List<Comment> queryCommentByCondition(Integer level);

    Comment queryCommentByGoodsId(Long goodsId);

}
