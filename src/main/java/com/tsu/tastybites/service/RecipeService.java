package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.entity.Review;
import com.tsu.tastybites.model.Difficulty;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    Page<Recipe> findRecipes(Integer authorId, Integer categoryId, String title,
                             Date publishDateStart, Date publishDateEnd, BigDecimal priceStart,
                             BigDecimal priceEnd, Difficulty difficulty, Double scoreStart, Double scoreEnd,
                             Double hoursToCookStart, Double hoursToCookEnd, Integer caloriesStart, Integer caloriesEnd,
                             int pageNumber, int pageSize, String sortField, String sortDirection);

    void deleteRecipe(int id);

    Review saveReview(Review review);

    List<Review> getRecipeReviews(int recipeId, Integer authorId, Date publishDateStart, Date publishDateEnd,
                                  Double scoreStart, Double scoreEnd, String sortField, String sortDirection);

    void deleteReview(int id);
}
