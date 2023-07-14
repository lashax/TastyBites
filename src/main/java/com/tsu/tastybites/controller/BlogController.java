package com.tsu.tastybites.controller;

import com.tsu.tastybites.entity.Blog;
import com.tsu.tastybites.service.BlogService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
     *
     * @return დამატებული ბლოგის ობიექტი
     */
    @PostMapping("/add-blog")
    public Blog addBlog(@RequestBody Blog blog) {
        return blogService.saveBlog(blog);
    }

    /**
     * ბლოგების წამოღება ფილტრებით, ფაგინაციის და სორტირებით
     *
     * @param authorId         გამომქვეყნებელი მომხმარებლის id
     * @param title            სათაური
     * @param publishDateStart გამოქვეყნების თარიღი >=
     * @param publishDateEnd   გამოქვეყნების თარიღი <=
     * @param pageNumber       გვერდის ნომერი (ათვლა იწყება 1-დან) (default: 1)
     * @param pageSize         რა რადოენობით სტატიები უნდა იქნეს დაბრუნებული (default: 20)
     * @param sortField        ბლოგის ველი, თუ რითაც უნდა მოხდეს სორტირება  (default: publishDate)
     * @param sortDirection    სორტირების მიმართულება: DESC ან ASC (default: DESC)
     * @return ობიექტს, რომელსაც content-ში აქვს ბლოგების სია
     */
    @GetMapping("/get-blogs")
    public Page<Blog> getBlogs(@RequestParam(required = false) Integer authorId,
                               @RequestParam(required = false) String title,
                               @RequestParam(required = false)
                               @DateTimeFormat(pattern = "dd-MM-yyyy") Date publishDateStart,
                               @RequestParam(required = false)
                               @DateTimeFormat(pattern = "dd-MM-yyyy") Date publishDateEnd,
                               @RequestParam(defaultValue = "1") int pageNumber,
                               @RequestParam(defaultValue = "20") int pageSize,
                               @RequestParam(defaultValue = "publishDate") String sortField,
                               @RequestParam(defaultValue = "DESC") String sortDirection) {
        return blogService.findBlogs(authorId, title, publishDateStart, publishDateEnd, pageNumber, pageSize, sortField, sortDirection);
    }

    /**
     * ბლოგის წაშლა აიდის მიხედვით
     *
     * @param id ბლოგის აიდი, რომელიც უნდა წაიშალოს
     */
    @DeleteMapping("/delete-blog/{id}")
    public void deleteBlog(@PathVariable int id) {
        blogService.deleteBlog(id);
    }
}
