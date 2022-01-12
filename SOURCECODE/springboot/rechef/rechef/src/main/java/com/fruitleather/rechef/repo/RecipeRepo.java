package com.fruitleather.rechef.repo;

import java.awt.*;
import java.util.List;
import com.fruitleather.rechef.model.User;
import com.fruitleather.rechef.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RecipeRepo extends JpaRepository<Recipe, Long> {
    @Query(value = "SELECT * FROM recipe",
            nativeQuery=true
    )
    public List<Recipe> getAllRecipes();

    @Query(value = "SELECT * FROM recipe WHERE :recipeID = recipeID;",
            nativeQuery=true
    )
    public Recipe getRecipe(@Param("recipeID") Integer recipeID);
}