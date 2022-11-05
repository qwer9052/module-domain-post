package com.module.domain.post.repo;

import com.module.domain.post.entityrepo.EPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepo {

    @Autowired
    EPostRepo ePostRepo;

}
