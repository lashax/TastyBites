package com.tsu.tastybites.controller;

import com.tsu.tastybites.entity.Blog;
import com.tsu.tastybites.service.BlogService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
@CrossOrigin
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * ახალი სტატიის დამატება
     */
    @PostMapping("/add-blog")
    public String addBlog(@RequestBody Blog blog) {
        blogService.saveBlog(blog);
        return "New blog has been added";
    }

    /**
     * ყველა სტატიის წამოღება რაც კი არსებობს ბაზაში.
     */
    @GetMapping("/all-blogs")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }
}
