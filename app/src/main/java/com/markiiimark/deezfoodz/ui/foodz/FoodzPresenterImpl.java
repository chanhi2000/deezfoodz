package com.markiiimark.deezfoodz.ui.foodz;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import com.markiiimark.deezfoodz.app.Constants;
import com.markiiimark.deezfoodz.model.FoodzItem;
import com.markiiimark.deezfoodz.model.FoodzListResponse;
import com.markiiimark.deezfoodz.network.UsdaApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class FoodzPresenterImpl implements FoodzPresenter {

    private FoodzView view;

    @Override public void setView(FoodzView view) {  this.view = view;  }

    @Override public void getFoodz() {
        view.showLoading();

        Converter.Factory converter = GsonConverterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(converter)
                .build();

        UsdaApi usdaApi = retrofit.create(UsdaApi.class);

        usdaApi.getFoodzList().enqueue(new Callback<FoodzListResponse>() {

            @Override
            public void onResponse(Call<FoodzListResponse> call, Response<FoodzListResponse> response) {
                if (response.code() != 200) {

                    view.showErrorMessage();

                } else {
                    List<FoodzItem> foodzItemList = Stream.of(response.body().getList().getItem())
                            .filter(foodzItem -> !foodzItem.getName().contains("ERROR"))
                            .collect(Collectors.toList());

                    view.showFoodz(foodzItemList);
                }

                view.hideLoading();
            }

            @Override
            public void onFailure(Call<FoodzListResponse> call, Throwable t) {
                view.showErrorMessage();
                view.hideLoading();
            }
        });
    }
}
