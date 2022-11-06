package com.module.domain.post.repo;

import com.module.core.exception.CommonException;
import com.module.db.post.entity.QTbPost;
import com.module.db.post.entity.TbPost;
import com.module.domain.post.entityrepo.EPostRepo;
import com.module.domain.post.entityrepo.ETestRepo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
//import static com.module.db.post.entity.QTbPost.tbPost;

import java.util.List;

@Repository
public class PostRepo implements ETestRepo {


    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    EPostRepo ePostRepo;

    public Long insertPost(TbPost tbPost) {
        TbPost newTbPost = ePostRepo.save(tbPost);
        return newTbPost.getPostId();
    }

    public List<TbPost> findAllPost() {
        this.findByYoutuberSeq();
        List<TbPost> posts = ePostRepo.findPost();
        return posts;
    }

    public TbPost findPost(Long postId) {
        TbPost post = ePostRepo.findById(postId).orElseThrow(() -> new CommonException("존재 하지 않는 게시물 입니다."));
        return post;
    }

    @Override
    public List<TbPost> findByYoutuberSeq() {
        System.out.println("findByYoutuberSeq");
        return jpaQueryFactory.select(QTbPost.tbPost).from(QTbPost.tbPost).fetch();
    }
}
