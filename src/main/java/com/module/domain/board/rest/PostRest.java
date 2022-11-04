package com.module.domain.board.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/post")
public interface PostRest {

    @GetMapping
    public void findPost();

    @PostMapping
    public void insertPost();

}
