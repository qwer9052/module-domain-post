package com.module.domain.board.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.module.db.enums.common.Del;
import com.module.db.enums.post.PostType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TbPostDto {

    private Long postId;
    private Long userId;
    private String title;
    private String content;
    private PostType postType;
    private Del del;
    private LocalDateTime creDt;
    private LocalDateTime updDt;
}
