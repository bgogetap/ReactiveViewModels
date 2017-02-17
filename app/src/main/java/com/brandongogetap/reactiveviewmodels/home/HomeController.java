package com.brandongogetap.reactiveviewmodels.home;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brandongogetap.reactiveviewmodels.R;
import com.brandongogetap.reactiveviewmodels.base.BaseController;
import com.brandongogetap.reactiveviewmodels.base.MainComponent;
import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.brandongogetap.scoper.Scoper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.CompositeDisposable;

public final class HomeController extends BaseController<HomeComponent> {

    @Inject HomeViewProvider viewModel;
    @Inject HomePresenter presenter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private final CompositeDisposable disposables = new CompositeDisposable();

    private HomeAdapter adapter;

    @Override protected void onViewBound(View view) {
        component.inject(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        disposables.add(viewModel.listItems().subscribe(this::setData));
        setRetainViewMode(RetainViewMode.RETAIN_DETACH);
    }

    private void setData(List<ListItem> items) {
        if (adapter == null) {
            adapter = new HomeAdapter(items);
            recyclerView.setAdapter(adapter);
        } else {
            // DiffUtil example coming soon -- don't really do this
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.fab) void addClicked() {
        presenter.addItemClicked();
    }

    @Override protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_home, container, false);
    }

    @Override protected HomeComponent initComponent(Activity activity) {
        return Scoper.<MainComponent>withParent(activity)
                .createChild(this, parent -> parent.plus(new HomeModule()));
    }

    @Override protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        disposables.clear();
        adapter = null;
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        viewModel.release();
    }
}
