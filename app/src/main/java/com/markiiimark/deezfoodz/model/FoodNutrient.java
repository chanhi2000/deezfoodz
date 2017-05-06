package com.markiiimark.deezfoodz.model;

import com.markiiimark.deezfoodz.app.StringUtils;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class FoodNutrient {
    private String nutrient;
    private String unit;
    private String value;

    @Override public String toString() {
        return nutrient + ": " + value + " " + unit;
    }
    public String getValue() {  return value;  }
}
