package com.fruitleather.rechef.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Recipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long recipeID;
    private String name;
    private String url;
    private String servings;
    private String rating;
    private String imageurl;
    private String type;
    private String instructions;
    private String ingredients;
    public Recipe() {}
    public Recipe(String name, String url, String servings, String rating, String imageurl, String type, String instructions, String ingredients) {
        this.name = name;
        this.url = url;
        this.servings = servings;
        this.rating = rating;
        this.imageurl = imageurl;
        this.type = type;
        this.instructions = instructions;
        this.ingredients = ingredients;
    }

    public Long getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(Long recipeID) {
        this.recipeID = recipeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageurl() {
        return this.imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "User{" +
                "recipeId=" + recipeID +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", servings='" + servings + '\'' +
                ", rating='" + rating + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", type='" + type + '\'' +
                ", instructions='" + instructions + '\'' +
                ", ingredients='" + ingredients + '\'' +
                '}';
    }
}