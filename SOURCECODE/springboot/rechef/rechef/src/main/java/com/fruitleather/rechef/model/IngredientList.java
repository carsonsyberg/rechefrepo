package com.fruitleather.rechef.model;

import javax.persistence.*;
import java.io.Serializable;

public class IngredientList implements Serializable {
    private String[] ingredients;
    public IngredientList() {}
    public IngredientList(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        String arr = "[";
        for(int i = 0; i < ingredients.length; i++) {
            arr = arr + ingredients[i];
            if (i != 0) arr += ", ";
        }
        arr += "]";
        return "IngredientList{" +
                ", name: " + arr +
                '}';
    }
}