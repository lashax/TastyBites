package com.tsu.tastybites.service;

import com.tsu.tastybites.model.Recipe;

import java.util.List;

public interface RecipeService {

    Recipe saveRecipe(Recipe recipe);

    List<Recipe> getAllRecipes();
}
