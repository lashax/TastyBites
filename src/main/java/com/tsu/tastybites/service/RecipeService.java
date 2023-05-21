package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.entity.Review;

import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();

    Review saveReview(Review review);

    List<Review> getRecipeReviews(int id);
}
