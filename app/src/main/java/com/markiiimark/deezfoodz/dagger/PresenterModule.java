package com.markiiimark.deezfoodz.dagger;

import android.content.Context;

import com.markiiimark.deezfoodz.ui.food.FoodPresenter;
import com.markiiimark.deezfoodz.ui.food.FoodPresenterImpl;
import com.markiiimark.deezfoodz.ui.foodz.FoodzPresenter;
import com.markiiimark.deezfoodz.ui.foodz.FoodzPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;

/**
 * Created by MarkiiimarK on 5/7/17.
 */
@Module
public class PresenterModule {
    @Provides @Singleton
    FoodzPresenter provideFoodzPresenter(Context context) {  return new FoodzPresenterImpl(context);  }

    @Provides @Singleton
    FoodPresenter provideFoodPresenter(Context context) {  return new FoodPresenterImpl(context);  }
}
