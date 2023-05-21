package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Blog;

import java.util.List;

public interface BlogService {

    void saveBlog(Blog blog);

    List<Blog> getAllBlogs();
}
