package com.module.domain.post.entityrepo;

import com.module.db.post.entity.TbPost;

import java.util.List;

public interface ETestRepo {

    List<TbPost> findByYoutuberSeq();
}
