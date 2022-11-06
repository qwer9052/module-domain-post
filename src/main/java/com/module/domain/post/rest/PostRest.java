package com.module.domain.post.rest;

import com.module.core.annotation.JwtAuth;
import com.module.db.post.model.TbPostDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1/post")
public interface PostRest {

    @GetMapping("/all")
    @JwtAuth
    List<TbPostDto> findAllPost(Long userId);

    @GetMapping("/{postId}")
    @JwtAuth
    TbPostDto findPost(Long userId, @PathVariable Long postId);

    @PostMapping
    @JwtAuth
    Long insertPost(@RequestBody TbPostDto tbPostDto, Long userId);

    @PostMapping("/comment/{postId}/{commentId}")
    @JwtAuth
    Long insertPostComment(Long userId, @PathVariable Long postId, @PathVariable Long commentId, @RequestBody TbPostDto tbPostDto);

}
