package com.tsu.tastybites.controller;

import com.tsu.tastybites.entity.Recipe;
import com.tsu.tastybites.entity.Review;
import com.tsu.tastybites.model.Difficulty;
import com.tsu.tastybites.service.RecipeService;
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

import java.math.BigDecimal;
import java.util.Date;
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
     *
     * @return დამატებული რეცეპტის ობიექტი
     */
    @PostMapping("/add-recipe")
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return recipeService.saveRecipe(recipe);
    }

    /**
     * რეცეპტების წამოღება ფილტრით, პაგინაციით და სორტირებით.
     * @param authorId გამომქვეყნებლის აიდი
     * @param categoryId კატეგორიის აიდი
     * @param title დასახელება
     * @param publishDateStart გამოქვეყნების თარიღი >=
     * @param publishDateEnd გამოქვეყნების თარიღი <=
     * @param priceStart თანხა >=
     * @param priceEnd თანხა <=
     * @param difficulty სირთულე: EASY, MEDIUM, HARD
     * @param scoreStart შეფასება >=
     * @param scoreEnd  შეფასება <=
     * @param hoursToCookStart მომზადების დრო (სთ) >=
     * @param hoursToCookEnd  მომზადების დრო (სთ) <=
     * @param caloriesStart კალორიები >=
     * @param caloriesEnd კალორიები <=
     * @param pageNumber       გვერდის ნომერი (ათვლა იწყება 1-დან) (default: 1)
     * @param pageSize         რა რადოენობით სტატიები უნდა იქნეს დაბრუნებული (default: 20)
     * @param sortField        ბლოგის ველი, თუ რითაც უნდა მოხდეს სორტირება  (default: publishDate)
     * @param sortDirection    სორტირების მიმართულება: DESC ან ASC (default: DESC)
     * @return ობიექტს, რომელსაც content-ში აქვს რეცეპტების სია
     */
    @GetMapping("/get-recipes")
    public Page<Recipe> getRecipes(@RequestParam(required = false) Integer authorId,
                                   @RequestParam(required = false) Integer categoryId,
                                   @RequestParam(required = false) String title,
                                   @RequestParam(required = false)
                                   @DateTimeFormat(pattern = "dd-MM-yyyy") Date publishDateStart,
                                   @RequestParam(required = false)
                                   @DateTimeFormat(pattern = "dd-MM-yyyy") Date publishDateEnd,
                                   @RequestParam(required = false) BigDecimal priceStart,
                                   @RequestParam(required = false) BigDecimal priceEnd,
                                   @RequestParam(required = false) Difficulty difficulty,
                                   @RequestParam(required = false) Double scoreStart,
                                   @RequestParam(required = false) Double scoreEnd,
                                   @RequestParam(required = false) Double hoursToCookStart,
                                   @RequestParam(required = false) Double hoursToCookEnd,
                                   @RequestParam(required = false) Integer caloriesStart,
                                   @RequestParam(required = false) Integer caloriesEnd,
                                   @RequestParam(defaultValue = "1") int pageNumber,
                                   @RequestParam(defaultValue = "20") int pageSize,
                                   @RequestParam(defaultValue = "publishDate") String sortField,
                                   @RequestParam(defaultValue = "DESC") String sortDirection) {
        return recipeService.findRecipes(authorId, categoryId, title, publishDateStart, publishDateEnd, priceStart,
                priceEnd, difficulty, scoreStart, scoreEnd, hoursToCookStart, hoursToCookEnd, caloriesStart,
                caloriesEnd, pageNumber, pageSize, sortField, sortDirection);
    }

    /**
     * რეცეპტის წაშლა
     *
     * @param id რეცეპტის აიდი, რომელიც უნდა წაიშალოს
     */
    @DeleteMapping("/delete-blog/{id}")
    public void deleteRecipe(@PathVariable int id) {
        recipeService.deleteRecipe(id);
    }

    /**
     * შეფასების (კომენტარის) დამატება
     */
    @PostMapping("/add-review")
    public Review addReview(@RequestBody Review review) {
        return recipeService.saveReview(review);
    }

    /**
     * კონკრეტული რეცეპტის შეფასებების წამოღება ფილტრაციით და სორტირებით.
     *
     * @param recipeId         რეცეპტის აიდი, რომლის შეფასებები გვაინტერესებს
     * @param authorId         შემფასებელი მომხმარებლის აიდი
     * @param publishDateStart გამოქვეყნების დრო >=
     * @param publishDateEnd   გამოქვეყნების დრო <=
     * @param scoreStart       შეფასება >=
     * @param scoreEnd         შეფასება <=
     * @param sortField        ბლოგის ველი, თუ რითაც უნდა მოხდეს სორტირება  (default: publishDate)
     * @param sortDirection    სორტირების მიმართულება: DESC ან ASC (default: DESC)
     * @return შეფასების ობიექტების სია
     */
    @GetMapping("/recipe-reviews")
    public List<Review> getRecipeReviews(@RequestParam int recipeId,
                                         @RequestParam(required = false) Integer authorId,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "dd-MM-yyyy") Date publishDateStart,
                                         @RequestParam(required = false)
                                         @DateTimeFormat(pattern = "dd-MM-yyyy") Date publishDateEnd,
                                         @RequestParam(required = false) Double scoreStart,
                                         @RequestParam(required = false) Double scoreEnd,
                                         @RequestParam(defaultValue = "publishDate") String sortField,
                                         @RequestParam(defaultValue = "DESC") String sortDirection) {
        return recipeService.getRecipeReviews(recipeId, authorId, publishDateStart, publishDateEnd, scoreStart, scoreEnd, sortField, sortDirection);
    }

    /**
     * შეფასების წაშლა
     *
     * @param id შეფასების აიდი, რომელიც უნდა წაიშალოს
     */
    @DeleteMapping("/delete-review/{id}")
    public void deleteReview(@PathVariable int id) {
        recipeService.deleteReview(id);
    }
}
