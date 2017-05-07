package com.markiiimark.deezfoodz.dagger;

import com.markiiimark.deezfoodz.app.Constants;
import com.markiiimark.deezfoodz.network.UsdaApi;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

@Module
public class NetworkModule {

    private static final String NAME_BASE_URL = "NAME_BASE_URL";

    @Provides @Named(NAME_BASE_URL)
    String provideBaseUrlString() {  return Constants.BASE_URL;  }

    @Provides @Singleton
    Converter.Factory provideGsonConverter() {  return GsonConverterFactory.create();  }

    @Provides @Singleton
    Retrofit provideRetroFit(Converter.Factory converter, @Named(NAME_BASE_URL) String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(converter)
                .build();
    }

    @Provides @Singleton
    UsdaApi provideUsdaApi(Retrofit retrofit) {  return retrofit.create(UsdaApi.class);  }
}
