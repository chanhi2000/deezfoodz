package com.markiiimark.deezfoodz.dagger;

import com.markiiimark.deezfoodz.ui.food.FoodPresenter;
import com.markiiimark.deezfoodz.ui.food.FoodPresenterImpl;
import com.markiiimark.deezfoodz.ui.foodz.FoodzPresenter;
import com.markiiimark.deezfoodz.ui.foodz.FoodzPresenterImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MarkiiimarK on 5/7/17.
 */
@Module
public class PresenterModule {
    @Provides @Singleton
    FoodzPresenter provideFoodzPresenter() {  return new FoodzPresenterImpl();  }

    @Provides @Singleton
    FoodPresenter provideFoodPresenter() {  return new FoodPresenterImpl();  }
}
