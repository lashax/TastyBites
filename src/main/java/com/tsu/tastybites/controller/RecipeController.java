package com.tsu.tastybites.controller;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.entity.Review;
import com.tsu.tastybites.service.RecipeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * ახალი რეცეპტის დამატება.
     */
    @PostMapping("/add-recipe")
    public String addRecipe(@RequestBody Recipe recipe) {
        recipeService.saveRecipe(recipe);
        return "New recipe has been added";
    }

    /**
     * ყველა რეცეპტის წამოღება რაც კი არსებობს ბაზაში.
     */
    @GetMapping("/all-recipes")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    /**
     * შეფასების (კომენტარის) დამატება
     */
    @PostMapping("/add-review")
    public String addReview(@RequestBody Review review) {
        recipeService.saveReview(review);
        return "New review has been added";
    }

    /**
     * კონკრეტული რეცეპტისთვის ყველა შეფასებების (კომენტარების) წამოღება.
     * @param id  რეცეპტის id
     */
    @GetMapping("/review/{id}")
    public List<Review> getRecipeReviews(@PathVariable int id) {
        return recipeService.getRecipeReviews(id);
    }
}
