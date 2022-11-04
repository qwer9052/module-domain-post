package com.module.domain.board.entityrepo;

import com.module.db.entity.post.TbPost;
import com.module.db.entity.post.TbPostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EPostLikeRepo extends JpaRepository<TbPostLike, Long> {
}
