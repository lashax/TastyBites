package com.tsu.tastybites.controller;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.service.RecipeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@CrossOrigin
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/add")
    public String add(@RequestBody Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "New recipe is added";
    }

    @GetMapping("/get-all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }
}
