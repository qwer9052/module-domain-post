package com.module.domain.post.entityrepo;

import com.module.db.post.entity.TbComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ECommentRepo extends JpaRepository<TbComment, Long> {
}
