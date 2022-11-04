package com.module.domain.board.model;

import com.module.db.enums.common.Del;
import com.module.db.enums.post.PostType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TbPostLikeDto {

    private Long postId;
    private Long postLikeId;
    private Long userId;
    private Del del;
    private LocalDateTime creDt;
    private LocalDateTime updDt;

}
