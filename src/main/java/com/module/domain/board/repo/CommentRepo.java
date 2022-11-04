package com.module.domain.board.repo;

import com.module.domain.board.entityrepo.EPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepo {

    @Autowired
    EPostRepo ePostRepo;

}
