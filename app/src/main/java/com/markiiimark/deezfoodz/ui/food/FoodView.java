package com.markiiimark.deezfoodz.ui.food;


import com.markiiimark.deezfoodz.model.Food;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public interface FoodView {
    void showLoading();
    void hideLoading();
    void showFood(Food food);
    void showErrorMessage();
}
