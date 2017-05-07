package com.markiiimark.deezfoodz.ui.foodz;

import android.content.Context;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import com.markiiimark.deezfoodz.app.DeezFoodzApplication;
import com.markiiimark.deezfoodz.model.FoodzItem;
import com.markiiimark.deezfoodz.model.FoodzListResponse;
import com.markiiimark.deezfoodz.network.UsdaApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class FoodzPresenterImpl implements FoodzPresenter {

    @Inject UsdaApi usdaApi;

    public FoodzPresenterImpl(Context context) {  ((DeezFoodzApplication)context).getAppComponent().inject(this);  }

    private FoodzView view;

    @Override public void setView(FoodzView view) {  this.view = view;  }

    @Override public void getFoodz() {
        view.showLoading();

//        Converter.Factory converter = GsonConverterFactory.create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(converter)
//                .build();
//
//        UsdaApi usdaApi = retrofit.create(UsdaApi.class);

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
