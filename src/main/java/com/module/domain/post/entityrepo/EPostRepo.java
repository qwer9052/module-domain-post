package com.module.domain.post.entityrepo;

import com.module.db.post.entity.TbPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface EPostRepo extends JpaRepository<TbPost, Long> {


    @Query("select p from TbPost p")
    List<TbPost> findPost();

    @Query("select p from TbPost p left join fetch p.comments where p.postId = :postId")
    Optional<TbPost> findFetchPostWithComment(@Param("postId") Long postId);
}
