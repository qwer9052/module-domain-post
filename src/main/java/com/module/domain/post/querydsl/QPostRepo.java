package com.module.domain.post.querydsl;

import com.module.db.post.entity.TbPost;
import com.module.db.post.model.TbCommentChildrenDto;
import com.module.db.post.model.TbCommentDto;
import com.module.db.post.model.TbPostDto;

import java.util.List;
import java.util.Optional;

public interface QPostRepo {

    Optional<TbPostDto> findOnePostById(Long userId, Long postId);

}
