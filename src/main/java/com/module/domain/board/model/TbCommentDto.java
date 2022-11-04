package com.module.domain.board.model;

import com.module.db.enums.common.Del;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TbCommentDto {

    private Long commentId;
    private Long userId;
    private Del del;

    private String content;

    private Long parentId;

    private LocalDateTime creDt;
    private LocalDateTime updDt;

    private List<TbCommentDto> children = new ArrayList<>();

}
