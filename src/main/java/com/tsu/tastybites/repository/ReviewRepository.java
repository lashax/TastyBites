package com.tsu.tastybites.repository;

import com.tsu.tastybites.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findAllByRecipe_Id(int recipeId);
}
