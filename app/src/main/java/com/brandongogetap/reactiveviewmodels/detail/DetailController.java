package com.brandongogetap.reactiveviewmodels.detail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandongogetap.reactiveviewmodels.R;
import com.brandongogetap.reactiveviewmodels.base.BaseController;
import com.brandongogetap.reactiveviewmodels.base.MainComponent;
import com.brandongogetap.scoper.Scoper;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.CompositeDisposable;

public final class DetailController extends BaseController<DetailComponent> {

    public static DetailController newInstance(int itemId) {
        Bundle bundle = new Bundle();
        bundle.putInt("item_id", itemId);
        return new DetailController(bundle);
    }

    public DetailController(Bundle bundle) {
        super(bundle);
    }

    @Inject DetailViewProvider viewModel;

    @BindView(R.id.tv_title) TextView titleTextView;
    @BindView(R.id.tv_message) TextView messageTextView;
    @BindView(R.id.tv_id) TextView idTextView;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Override protected void onViewBound(View view) {
        component.inject(this);
        disposables.add(viewModel.title().subscribe(titleTextView::setText));
        disposables.add(viewModel.message().subscribe(messageTextView::setText));
        disposables.add(viewModel.id().subscribe(id -> idTextView.setText("ID: " + id)));
        disposables.add(viewModel.color().subscribe(view::setBackgroundColor));
    }

    @Override protected View inflateView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.controller_detail, container, false);
    }

    @Override protected DetailComponent initComponent(Activity activity) {
        int itemId = getArgs().getInt("item_id");
        return Scoper.<MainComponent>withParent(activity)
                .createChild(this, parent -> parent.plus(new DetailModule(itemId)));
    }

    @Override protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        disposables.clear();
    }
}
