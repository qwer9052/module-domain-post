package com.module.domain.post.repo;

import com.module.core.exception.CommonException;
import com.module.db.post.entity.TbComment;
import com.module.db.post.entity.TbPost;
import com.module.db.post.model.TbCommentChildrenDto;
import com.module.db.post.model.TbCommentDto;
import com.module.db.user.model.TbUserDto;
import com.module.domain.post.entityrepo.ECommentRepo;
import com.module.domain.post.entityrepo.EPostRepo;
import com.module.domain.post.querydsl.QCommentRepo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.module.db.post.entity.QTbComment.tbComment;
import static com.module.db.post.entity.QTbPost.tbPost;
import static com.module.db.user.entity.QTbUser.tbUser;

@Repository
public class CommentRepo implements QCommentRepo {

    @Autowired
    ECommentRepo eCommentRepo;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    public TbComment findById(Long commentId) {
        return eCommentRepo.findById(commentId).orElseThrow(() -> new CommonException("존재 하지 않는 댓글 입니다."));
    }

    public TbComment insertPostComment(TbComment tbComment) {
        return eCommentRepo.save(tbComment);
    }

    public Integer updatePostComment(TbComment tbComment) {
        return eCommentRepo.updatePostCommentParent(tbComment.getCommentId(), tbComment);
    }

    public List<TbComment> findAllByTbPost(TbPost post) {
        return eCommentRepo.findAllByTbPost(post);
    }


    @Override
    public List<TbCommentDto> findCommentsByPostId(Long postId) {
        List<TbCommentDto> commentDtos = jpaQueryFactory.select(Projections.bean(TbCommentDto.class,
                        tbComment.commentId,
                        tbComment.creDt,
                        tbComment.del,
                        Projections.fields(TbUserDto.class,
                                tbComment.tbUser.name,
                                tbComment.tbUser.userId).as("tbUser"),
                        tbComment.content,
                        tbComment.parent.commentId.as("parentId")
                ))
                .from(tbComment)
                .innerJoin(tbComment.tbPost, tbPost)
                .innerJoin(tbComment.tbUser, tbUser)
                .where(tbPost.postId.eq(postId).and(tbComment.parent.commentId.isNull()))
                .orderBy(tbComment.commentId.asc())
                .fetch();
        return commentDtos;
    }

    @Override
    public List<TbCommentChildrenDto> findCommentChildrenByPostId(Long postId) {
        List<TbCommentChildrenDto> commentChildrenDtos = jpaQueryFactory.select(Projections.bean(TbCommentChildrenDto.class,
                        tbComment.commentId,
                        tbComment.creDt,
                        tbComment.del,
                        Projections.fields(TbUserDto.class,
                                tbComment.tbUser.name,
                                tbComment.tbUser.userId).as("tbUser"),
                        tbComment.content,
                        tbComment.parent.commentId.as("parentId")
                ))
                .from(tbComment)
                .innerJoin(tbComment.tbPost, tbPost)
                .innerJoin(tbComment.tbUser, tbUser)
                .where(tbPost.postId.eq(postId).and(tbComment.parent.isNotNull()))
                .orderBy(tbComment.commentId.asc())
                .fetch();
        return commentChildrenDtos;
    }

    @Override
    public Optional<TbCommentDto> findOneByCommentId(Long commentId) {
        Optional<TbCommentDto> tbCommentDto = Optional.ofNullable(jpaQueryFactory
                .select(Projections.bean(TbCommentDto.class,
                        tbComment.content,
                        tbComment.creDt,
                        tbComment.del,
                        Projections.fields(TbUserDto.class,
                                tbComment.tbUser.name,
                                tbComment.tbUser.userId).as("tbUser"),
                        tbComment.commentId,
                        tbComment.parent.commentId.as("parentId")
                ))
                .from(tbComment)
                .innerJoin(tbComment.tbUser, tbUser)
                .where(tbComment.commentId.eq(commentId).and(tbComment.parent.isNull()))
                .orderBy(tbComment.commentId.asc())
                .fetchOne());
        return tbCommentDto;
    }

    public List<TbCommentChildrenDto> findCommentChildrenByCommentId(Long commentId) {
        List<TbCommentChildrenDto> tbCommentChildrenDtos = jpaQueryFactory
                .select(Projections.bean(TbCommentChildrenDto.class,
                        tbComment.content,
                        tbComment.creDt,
                        tbComment.del,
                        Projections.fields(TbUserDto.class,
                                tbComment.tbUser.name,
                                tbComment.tbUser.userId).as("tbUser"),
                        tbComment.commentId,
                        tbComment.parent.commentId.as("parentId")
                ))
                .from(tbComment)
                .innerJoin(tbComment.tbUser, tbUser)
                .where(tbComment.parent.commentId.eq(commentId).and(tbComment.parent.isNotNull()))
                .orderBy(tbComment.commentId.asc())
                .fetch();
        return tbCommentChildrenDtos;
    }
}
