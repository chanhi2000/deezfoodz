package com.markiiimark.deezfoodz.ui.food;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.util.Log;

import com.markiiimark.deezfoodz.R;
import com.markiiimark.deezfoodz.app.Constants;
import com.markiiimark.deezfoodz.app.DeezFoodzApplication;
import com.markiiimark.deezfoodz.model.Food;
import com.markiiimark.deezfoodz.model.FoodNutrient;
import com.markiiimark.deezfoodz.model.FoodResponse;
import com.markiiimark.deezfoodz.network.UsdaApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class FoodPresenterImpl implements FoodPresenter {

    @Inject UsdaApi usdaApi;

    public FoodPresenterImpl(Context context) {  ((DeezFoodzApplication)context).getAppComponent().inject(this);  }

    private FoodView view;

    @Override public void setView(FoodView view) {  this.view = view;  }

    @Override
    public void getFood(String foodId) {
        view.showLoading();

//        Converter.Factory converter = GsonConverterFactory.create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(converter)
//                .build();
//
//        UsdaApi usdaApi = retrofit.create(UsdaApi.class);

        usdaApi.getFoodItem(foodId).enqueue(new Callback<FoodResponse>() {
            @Override
            public void onResponse(Call<FoodResponse> call, Response<FoodResponse> response) {
                List<Food> foodList = response.body().getReport().getFoods();
                if (foodList != null && foodList.size() > 0) {
                    view.showFood(response.body().getReport().getFoods().get(0));
                } else {
                    showError();
                }
                view.hideLoading();
            }

            @Override
            public void onFailure(Call<FoodResponse> call, Throwable t) {
                showError();
                view.hideLoading();
            }
        });
    }

    @Override @ColorRes
    public int getFoodColor(Food food) {
        int colorRes = R.color.foodUnknown;

        List<FoodNutrient> nutrients = food.getNutrients();
        if (nutrients != null && nutrients.size() > 0) {
            FoodNutrient nutrient = nutrients.get(0);
            try {
                double nutrientValue = Double.parseDouble(nutrient.getValue());
                if (nutrientValue < 0) {
                    colorRes = R.color.foodUnknown;
                } else if (nutrientValue < Constants.YELLOW_LEVEL) {
                    colorRes = R.color.foodGreen;
                } else if (nutrientValue < Constants.RED_LEVEL) {
                    colorRes = R.color.foodYellow;
                } else {
                    colorRes = R.color.foodRed;
                }
            } catch (NumberFormatException e) {
                Log.e(FoodPresenterImpl.class.getSimpleName(),
                        "Error: parsing nutrient value");
            }
        }
        return colorRes;
    }

    @Override @DrawableRes
    public int getFoodImage(Food food) {
        int drawableRes = R.drawable.yellow;

        List<FoodNutrient> nutrients = food.getNutrients();
        if (nutrients != null && nutrients.size() > 0) {
            FoodNutrient nutrient = nutrients.get(0);

            try {
                double nutrientValue = Double.parseDouble(nutrient.getValue());
                if (nutrientValue < 0) {
                    drawableRes = R.drawable.yellow;
                } else if (nutrientValue < Constants.YELLOW_LEVEL) {
                    drawableRes= R.drawable.green;
                } else if (nutrientValue < Constants.RED_LEVEL) {
                    drawableRes = R.drawable.yellow;
                } else {
                    drawableRes= R.drawable.red;
                }
            } catch (NumberFormatException e) {
                Log.e(FoodPresenterImpl.class.getSimpleName(),
                        "Error: parsing nutrient value");
            }
        }
        return drawableRes;
    }

    private void showError() {  view.showErrorMessage();  }
}
