package com.module.domain.post.entityrepo;

import com.module.db.post.entity.TbPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EPostRepo extends JpaRepository<TbPost, Long> {

    @Query("select p from TbPost p")
    List<TbPost> findPost();
}
