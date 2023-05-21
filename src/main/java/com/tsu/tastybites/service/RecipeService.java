package com.tsu.tastybites.service;

import com.tsu.tastybites.entity.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();
}
