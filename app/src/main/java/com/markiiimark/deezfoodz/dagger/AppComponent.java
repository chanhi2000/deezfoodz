package com.markiiimark.deezfoodz.dagger;

import com.markiiimark.deezfoodz.app.DeezFoodzApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

@Singleton @Component(modules = {AppModule.class})
public interface AppComponent {
}
