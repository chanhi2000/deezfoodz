package com.markiiimark.deezfoodz.app;

import android.app.Application;

import com.markiiimark.deezfoodz.dagger.AppComponent;
import com.markiiimark.deezfoodz.dagger.AppModule;
import com.markiiimark.deezfoodz.dagger.DaggerAppComponent;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class DeezFoodzApplication extends Application {
    private AppComponent appComponent;
    public AppComponent getAppComponent() {  return appComponent;  }

    protected AppComponent initDagger(DeezFoodzApplication application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();
    }

    @Override public void onCreate() {
        super.onCreate();
        appComponent = initDagger(this);
    }
}
