package com.sureshtech.springbootrestdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sureshtech.springbootrestdemo.entity.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

}
