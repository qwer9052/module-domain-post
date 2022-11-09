package com.module.domain.post.repo;

import com.module.core.exception.CommonException;
import com.module.db.post.entity.TbComment;
import com.module.db.post.entity.TbPost;
import com.module.db.post.model.TbCommentChildrenDto;
import com.module.db.post.model.TbCommentDto;
import com.module.db.post.model.TbPostDto;
import com.module.db.user.model.QTbUserDto;
import com.module.db.user.model.TbUserDto;
import com.module.domain.post.entityrepo.EPostRepo;
import com.module.domain.post.querydsl.QPostRepo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.module.db.post.entity.QTbComment.tbComment;
import static com.module.db.post.entity.QTbPost.tbPost;
import static com.module.db.user.entity.QTbUser.tbUser;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepo implements QPostRepo {

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    EPostRepo ePostRepo;

    public Long insertPost(TbPost tbPost) {
        TbPost newTbPost = ePostRepo.save(tbPost);
        return newTbPost.getPostId();
    }

    public List<TbPost> findAllPost() {
        List<TbPost> posts = ePostRepo.findPost();
        return posts;
    }

    public TbPost findPost(Long postId) {
        TbPost post = ePostRepo.findById(postId).orElseThrow(() -> new CommonException("존재 하지 않는 게시물 입니다."));
        return post;
    }

    public TbPost findFetchPostWithComment(Long postId) {
        TbPost post = ePostRepo.findFetchPostWithComment(postId).orElseThrow(() -> new CommonException("존재 하지 않는 게시물 입니다."));
        return post;
    }

    @Override
    public Optional<TbPostDto> findOnePostById(Long userId, Long postId) {
        Optional<TbPostDto> postRes = Optional.ofNullable(jpaQueryFactory
                .select(Projections.bean(TbPostDto.class,
                        tbPost.postId,
                        tbPost.postType,
                        Projections.fields(TbUserDto.class,
                                tbPost.tbUser.name,
                                tbPost.tbUser.userId).as("tbUser"),
                        tbPost.creDt,
                        tbPost.del,
                        tbPost.title,
                        tbPost.content))
                .from(tbPost)
                .innerJoin(tbPost.tbUser, tbUser)
                .where(tbPost.postId.eq(userId))
                .fetchOne());
        return postRes;
    }


//    @Override
//    public TbPostDto findOnePostById(Long userId, Long postId) {
//
//        Optional<TbPostDto> postRes = Optional.ofNullable(jpaQueryFactory
//                .select(Projections.bean(TbPostDto.class,
//                        tbPost.postId,
//                        tbPost.postType,
//                        Projections.fields(TbUserDto.class,
//                                tbPost.tbUser.name,
//                                tbPost.tbUser.userId).as("tbUser"),
//                        tbPost.creDt,
//                        tbPost.del,
//                        tbPost.title,
//                        tbPost.content))
//                .from(tbPost)
//                .innerJoin(tbPost.tbUser, tbUser)
//                .where(tbPost.postId.eq(userId))
//                .fetchOne());
//
//        if (postRes.isEmpty()) {
//            return null;
//        }
//
//        List<TbCommentDto> commentDtos = jpaQueryFactory.select(Projections.bean(TbCommentDto.class,
//                        tbComment.commentId,
//                        tbComment.creDt,
//                        tbComment.del,
//                        Projections.fields(TbUserDto.class,
//                                tbComment.tbUser.name,
//                                tbComment.tbUser.userId).as("tbUser"),
//                        tbComment.content,
//                        tbComment.parent.commentId.as("parentId")
//                ))
//                .from(tbComment)
//                .innerJoin(tbComment.tbPost, tbPost)
//                .innerJoin(tbComment.tbUser, tbUser)
//                .where(tbPost.postId.eq(postId).and(tbComment.parent.commentId.isNull()))
//                .orderBy(tbComment.commentId.asc())
//                .fetch();
//
//        System.out.println(commentDtos.size() + "-------");
//
//
//        List<TbCommentChildrenDto> childrenCommentDtos = jpaQueryFactory.select(Projections.bean(TbCommentChildrenDto.class,
//                        tbComment.commentId,
//                        tbComment.creDt,
//                        tbComment.del,
//                        Projections.fields(TbUserDto.class,
//                                tbComment.tbUser.name,
//                                tbComment.tbUser.userId).as("tbUser"),
//                        tbComment.content,
//                        tbComment.parent.commentId.as("parentId")
//                ))
//                .from(tbComment)
//                .innerJoin(tbComment.tbPost, tbPost)
//                .innerJoin(tbComment.tbUser, tbUser)
//                .where(tbPost.postId.eq(postId).and(tbComment.parent.isNotNull()))
//                .orderBy(tbComment.commentId.asc())
//                .fetch();
//
//
//        commentDtos.forEach(parent -> {
//            parent.setChildren(childrenCommentDtos.stream()
//                    .filter(children -> children.getParentId().equals(parent.getCommentId()))
//                    .collect(Collectors.toList()));
//        });
//
//
//        postRes.get().setComments(commentDtos);
//
//        System.out.println(postRes.get().getTbUser());
//
//
//        return postRes.get();
//    }


}

