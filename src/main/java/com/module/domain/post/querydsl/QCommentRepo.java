package com.module.domain.post.querydsl;

import com.module.db.post.model.TbCommentChildrenDto;
import com.module.db.post.model.TbCommentDto;
import com.module.db.post.model.TbPostDto;

import java.util.List;
import java.util.Optional;

public interface QCommentRepo {

    List<TbCommentDto> findCommentsByPostId(Long postId);

    List<TbCommentChildrenDto> findCommentChildrenByPostId(Long postId);

    Optional<TbCommentDto> findOneByCommentId(Long commentId);

    List<TbCommentChildrenDto> findCommentChildrenByCommentId(Long commentId);

}
