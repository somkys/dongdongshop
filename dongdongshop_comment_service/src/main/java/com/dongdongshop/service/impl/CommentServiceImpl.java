package com.dongdongshop.service.impl;

import com.dongdongshop.entity.Comment;
import com.dongdongshop.entity.CommentVO;
import com.dongdongshop.service.CommentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@DubboService
public class CommentServiceImpl implements CommentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean insertComment(Comment comment) {
        CommentVO commentVO = new CommentVO();
        BeanUtils.copyProperties(comment,commentVO);
        commentVO.setCommentDate(LocalDateTime.now());
        CommentVO insert = mongoTemplate.insert(commentVO);
        if (insert != null){
        return true;
        }
        return false;
    }

    @Override
    public List<Comment> queryComment() {
        Query queryLimit = new Query().skip(0).limit(2);
//        queryLimit;
        List<CommentVO> commentVOS = mongoTemplate.find(queryLimit, CommentVO.class);
        List<Comment> collect = commentVOS.stream().map(commentVO -> {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentVO, comment);
            return comment;
        }).collect(Collectors.toList());
        System.out.println("collect = " + collect);
        return collect;
    }

    @Override
    public Comment queryCommentById(String id) {
        CommentVO commentVO = mongoTemplate.findById(id, CommentVO.class);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO,comment);
        return comment;
    }

    @Override
    public void updateCommentById(String context,String id) {
        Update update = new Update();
        update.set("context",context);
        mongoTemplate.updateFirst(Query.query(Criteria.where("id").is(id)),update,CommentVO.class);
    }

    @Override
    public void deleteCommentById(String id) {
        mongoTemplate.remove(Query.query(Criteria.where("id").is(id)),CommentVO.class);
    }

    @Override
    public List<Comment> queryCommentByCondition(Integer level) {

        if (level == 1){
            //差评
            return queryCommentByConditionHelp(1,2);
        }

        if (level == 2){
            return queryCommentByConditionHelp(3,4);
        }

        if (level==3) {
            return queryCommentByConditionHelp(5, 5);
        }
        return queryCommentByConditionHelp(1, 5);
    }

    public  List<Comment> queryCommentByConditionHelp(Integer i1 , Integer i2){
        Criteria criteria = new Criteria();
        criteria.andOperator(
                Criteria.where("level").gte(i1),
                Criteria.where("level").lte(i2)
        );
        Query query = new Query(criteria).skip(0).limit(2);
//        query.skip(0).limit(2);
        List<CommentVO> commentVOS = mongoTemplate.find(query, CommentVO.class);
        List<Comment> collect = commentVOS.stream().map(commentVO -> {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentVO, comment);
            return comment;
        }).collect(Collectors.toList());
        return collect;
    }

    @Override
    public Comment queryCommentByGoodsId(Long goodsId) {
        List<CommentVO> commentVOS = mongoTemplate.find(Query.query(Criteria.where("goods_id").is(goodsId)), CommentVO.class);
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVOS.get(0),comment);
        return comment;
    }



}
