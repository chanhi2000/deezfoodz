package com.markiiimark.deezfoodz.dagger;

import com.markiiimark.deezfoodz.app.DeezFoodzApplication;
import com.markiiimark.deezfoodz.ui.food.FoodActivity;
import com.markiiimark.deezfoodz.ui.foodz.FoodzActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

@Singleton @Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {
    void inject(FoodzActivity target);
    void inject(FoodActivity target);
}
