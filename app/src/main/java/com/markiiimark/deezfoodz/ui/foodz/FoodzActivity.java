package com.markiiimark.deezfoodz.ui.foodz;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.markiiimark.deezfoodz.R;
import com.markiiimark.deezfoodz.app.DeezFoodzApplication;
import com.markiiimark.deezfoodz.app.StringUtils;
import com.markiiimark.deezfoodz.model.FoodzItem;
import com.markiiimark.deezfoodz.ui.LifecycleLoggingActivity;
import com.markiiimark.deezfoodz.ui.food.FoodActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MarkiiimarK on 5/7/17.
 */

public class FoodzActivity extends LifecycleLoggingActivity implements FoodzView {

    @Inject FoodzPresenter presenter;

    @BindView(R.id.activity_foodz_recyclerView) RecyclerView foodzRecyclerView;
    @BindView(R.id.activity_foodz_progressBar) ProgressBar progressBar;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodz);

        ((DeezFoodzApplication)getApplication()).getAppComponent().inject(this);

        ButterKnife.bind(this);

        foodzRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        presenter = new FoodzPresenterImpl();
        presenter.setView(this);
        presenter.getFoodz();
    }

    // FoodzView
    @Override public void showLoading() {  progressBar.setVisibility(View.VISIBLE);  }
    @Override public void hideLoading() {  progressBar.setVisibility(View.GONE);  }

    @Override public void showFoodz(List<FoodzItem> foodzItemList) {
        foodzRecyclerView.setAdapter(new FoodzAdapter(foodzItemList));
        foodzRecyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override public void showErrorMessage() {  Toast.makeText(this, R.string.FoodzListError, Toast.LENGTH_SHORT).show();  }
    @Override public void launchFoodDetail(FoodzItem foodzItem) {  FoodActivity.launch(this, foodzItem);  }

    // Inner Classes

    class FoodzAdapter extends RecyclerView.Adapter<FoodzViewHolder> {
        private List<FoodzItem> foodzItemList;

        FoodzAdapter(List<FoodzItem> foodzItemList) {  this.foodzItemList = foodzItemList;  }

        @Override public FoodzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(FoodzActivity.this);
            return new FoodzViewHolder(inflater.inflate(R.layout.list_item_food, parent, false));
        }

        @Override public void onBindViewHolder(FoodzViewHolder holder, int position) {
            FoodzItem foodzItem = foodzItemList.get(position);
            holder.getFoodName().setText(StringUtils.stripPrefix(foodzItem.getName()));
            holder.getContainer().setOnClickListener(v -> launchFoodDetail(foodzItem));
        }

        @Override public int getItemCount() {  return foodzItemList.size();  }
    }
}
