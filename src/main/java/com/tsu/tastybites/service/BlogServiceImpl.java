package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Blog;
import com.tsu.tastybites.repository.BlogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public Page<Blog> findBlogs(Integer authorId, String title, Date publishDateStart, Date publishDateEnd,
                                int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize, sort);  // Note: page starts from 0

        Specification<Blog> spec = Specification.where(null);
        if (authorId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author").get("id"), authorId));
        }
        if (title != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
        if (publishDateStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("publishDate"), publishDateStart));
        }
        if (publishDateEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("publishDate"), publishDateEnd));
        }

        return blogRepository.findAll(spec, pageable);
    }

    @Override
    public void deleteBlog(int id) {
        blogRepository.deleteById(id);
    }
}
