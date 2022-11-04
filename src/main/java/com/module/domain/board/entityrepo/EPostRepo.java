package com.module.domain.board.entityrepo;

import com.module.db.entity.post.TbPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface EPostRepo extends JpaRepository<TbPost, Long> {
}
