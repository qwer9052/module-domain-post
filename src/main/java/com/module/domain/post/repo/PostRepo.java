package com.module.domain.post.repo;

import com.module.core.exception.CommonException;
import com.module.db.post.entity.TbPost;
import com.module.domain.post.entityrepo.EPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepo {

    @Autowired
    EPostRepo ePostRepo;

    public Long insertPost(TbPost tbPost) {
        TbPost newTbPost = ePostRepo.save(tbPost);
        return newTbPost.getPostId();
    }

    public List<TbPost> findAllPost() {
        List<TbPost> posts = ePostRepo.findPost();
        return posts;
    }

    public TbPost findPost(Long postId) {
        TbPost post = ePostRepo.findById(postId).orElseThrow(() -> new CommonException("존재 하지 않는 게시물 입니다."));
        return post;
    }
}
