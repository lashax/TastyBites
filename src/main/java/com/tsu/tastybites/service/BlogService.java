package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Blog;
import org.springframework.data.domain.Page;

import java.util.Date;

public interface BlogService {

    Blog saveBlog(Blog blog);

    Page<Blog> findBlogs(Integer authorId, String title, Date publishDateStart, Date publishDateEnd,
                         int pageNumber, int pageSize, String sortField, String sortDirection);

    void deleteBlog(int id);
}
