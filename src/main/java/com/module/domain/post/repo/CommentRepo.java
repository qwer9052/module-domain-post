package com.module.domain.post.repo;

import com.module.core.exception.CommonException;
import com.module.db.post.entity.TbComment;
import com.module.domain.post.entityrepo.ECommentRepo;
import com.module.domain.post.entityrepo.EPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepo {

    @Autowired
    ECommentRepo eCommentRepo;

    public TbComment findById(Long commentId) {
        return eCommentRepo.findById(commentId).orElseThrow(() -> new CommonException("존재 하지 않는 댓글 입니다."));
    }

    public TbComment insertPostComment(TbComment tbComment) {
        return eCommentRepo.save(tbComment);
    }

    public Integer updatePostComment(TbComment tbComment) {
        return eCommentRepo.updatePostCommentParent(tbComment.getCommentId(), tbComment);
    }
}
