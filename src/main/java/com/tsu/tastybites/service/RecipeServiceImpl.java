package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.entity.Review;
import com.tsu.tastybites.model.Difficulty;
import com.tsu.tastybites.repository.RecipeRepository;
import com.tsu.tastybites.repository.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final ReviewRepository reviewRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, ReviewRepository reviewRepository) {
        this.recipeRepository = recipeRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Recipe saveRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public Page<Recipe> findRecipes(Integer authorId, Integer categoryId, String title,
                                    Date publishDateStart, Date publishDateEnd, BigDecimal priceStart,
                                    BigDecimal priceEnd, Difficulty difficulty, Double scoreStart, Double scoreEnd,
                                    Double hoursToCookStart, Double hoursToCookEnd, Integer caloriesStart, Integer caloriesEnd,
                                    int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNumber - 1, pageSize, sort);  // Note: page starts from 0

        Specification<Recipe> spec = Specification.where(null);
        if (authorId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author").get("id"), authorId));
        }
        if (categoryId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId));
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
        if (priceStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceStart));
        }
        if (priceEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceEnd));
        }
        if (difficulty != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("difficulty"), difficulty));
        }
        if (scoreStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("score"), scoreStart));
        }
        if (scoreEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("score"), scoreEnd));
        }
        if (hoursToCookStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("hoursToCook"), hoursToCookStart));
        }
        if (hoursToCookEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("hoursToCook"), hoursToCookEnd));
        }
        if (caloriesStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("calories"), caloriesStart));
        }
        if (caloriesEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("calories"), caloriesEnd));
        }

        return recipeRepository.findAll(spec, pageable);
    }

    @Override
    public void deleteRecipe(int id) {
        recipeRepository.deleteById(id);
    }

    @Override
    public Review saveReview(Review review) {
        Recipe targetRecipe = recipeRepository.findById(review.getRecipe().getId()).orElseThrow();
        int reviewsCount = targetRecipe.getReviews() != null ? targetRecipe.getReviews().size() : 0;

        double resultScore = targetRecipe.getScore() != null ? targetRecipe.getScore() : 0;
        resultScore *= reviewsCount;
        resultScore += review.getScore();
        resultScore /= reviewsCount + 1;

        // update score of target recipe
        targetRecipe.setScore(resultScore);
        recipeRepository.save(targetRecipe);

        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getRecipeReviews(int recipeId, Integer authorId, Date publishDateStart, Date publishDateEnd,
                                         Double scoreStart, Double scoreEnd, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Specification<Review> spec = Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("recipe").get("id"), recipeId));
        if (authorId != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("author").get("id"), authorId));
        }
        if (publishDateStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("publishDate"), publishDateStart));
        }
        if (publishDateEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("publishDate"), publishDateEnd));
        }
        if (scoreStart != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("score"), scoreStart));
        }
        if (scoreEnd != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("score"), scoreEnd));
        }

        return reviewRepository.findAll(spec, sort);
    }

    @Override
    public void deleteReview(int id) {
        Review review = reviewRepository.findById(id).orElseThrow();
        Recipe targetRecipe = recipeRepository.findById(review.getRecipe().getId()).orElseThrow();
        int reviewsCount = targetRecipe.getReviews() != null ? targetRecipe.getReviews().size() : 0;

        double resultScore = targetRecipe.getScore() != null ? targetRecipe.getScore() : 0;
        resultScore *= reviewsCount;
        resultScore -= review.getScore();
        resultScore /= reviewsCount - 1;

        // update score of target recipe
        targetRecipe.setScore(resultScore);
        recipeRepository.save(targetRecipe);

        reviewRepository.deleteById(id);
    }
}
