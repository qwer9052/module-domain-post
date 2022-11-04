package com.module.domain.board.entityrepo;

import com.module.db.entity.post.TbComment;
import com.module.db.entity.post.TbPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ECommentRepo extends JpaRepository<TbComment, Long> {
}
