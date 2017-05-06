package com.markiiimark.deezfoodz.network;

import android.provider.SyncStateContract;

import com.markiiimark.deezfoodz.app.Constants;
import com.markiiimark.deezfoodz.model.FoodResponse;
import com.markiiimark.deezfoodz.model.FoodzListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public interface UsdaApi {
    @GET("ndb/list?api_key=" + Constants.API_KEY)
    Call<FoodzListResponse> getFoodzList();

    @GET("ndb/nutrients?api_key=" + Constants.API_KEY + "&nutrients=" + Constants.SUGAR_NUTRIENT)
    Call<FoodResponse> getFoodItem(@Query("ndbno") String foodId);

}
