package com.module.domain.post.entityrepo;

import com.module.db.post.entity.TbLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EPostLikeRepo extends JpaRepository<TbLike, Long> {
}
