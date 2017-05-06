package com.markiiimark.deezfoodz.ui.foodz;

import com.markiiimark.deezfoodz.model.FoodzItem;

import java.util.List;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public interface FoodzView {
    void showLoading();
    void hideLoading();
    void showFoodz(List<FoodzItem> foodzItemList);
    void showErrorMessage();
    void launchFoodDetail(FoodzItem foodzItem);
}
