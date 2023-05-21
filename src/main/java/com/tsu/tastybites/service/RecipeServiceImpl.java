package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.entity.Review;
import com.tsu.tastybites.repository.RecipeRepository;
import com.tsu.tastybites.repository.ReviewRepository;
import org.springframework.stereotype.Service;

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
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getRecipeReviews(int id) {
        return reviewRepository.findAllByRecipe_Id(id);
    }
}
