package com.markiiimark.deezfoodz.ui.food;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;

import com.markiiimark.deezfoodz.model.Food;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public interface FoodPresenter {
    void setView(FoodView view);
    void getFood(String foodId);

    @ColorRes int getFoodColor(Food food);
    @DrawableRes int getFoodImage(Food food);
}
