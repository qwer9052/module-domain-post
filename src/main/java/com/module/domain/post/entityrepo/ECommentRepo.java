package com.module.domain.post.entityrepo;

import com.module.db.post.entity.TbComment;
import com.module.db.post.entity.TbPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ECommentRepo extends JpaRepository<TbComment, Long> {

    @Query("update TbComment c set c.parent = :TbComment where c.commentId = :commentId")
    @Modifying
    Integer updatePostCommentParent(@Param("commentId") Long commentId, @Param("TbComment") TbComment tbComment);

    List<TbComment> findAllByTbPost(TbPost post);
}
