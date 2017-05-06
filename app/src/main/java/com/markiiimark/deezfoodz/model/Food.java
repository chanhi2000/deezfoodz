package com.markiiimark.deezfoodz.model;

import java.util.List;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class Food {
    private String nbdno;
    private String name;
    private String measure;
    private List<FoodNutrient> nutrients;

    public String getNbdno() {
        return nbdno;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }

    public List<FoodNutrient> getNutrients() {
        return nutrients;
    }
}
