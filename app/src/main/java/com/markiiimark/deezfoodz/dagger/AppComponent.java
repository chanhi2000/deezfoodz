package com.markiiimark.deezfoodz.dagger;

import com.markiiimark.deezfoodz.ui.food.FoodActivity;
import com.markiiimark.deezfoodz.ui.foodz.FoodzActivity;
import com.markiiimark.deezfoodz.ui.food.FoodPresenterImpl;
import com.markiiimark.deezfoodz.ui.foodz.FoodzPresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

@Singleton @Component(modules = {AppModule.class, PresenterModule.class, NetworkModule.class})
public interface AppComponent {
    void inject(FoodzActivity target);
    void inject(FoodActivity target);

    void inject(FoodzPresenterImpl target);
    void inject(FoodPresenterImpl target);
}
