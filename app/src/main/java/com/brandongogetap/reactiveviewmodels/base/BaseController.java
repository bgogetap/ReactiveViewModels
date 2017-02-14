package com.brandongogetap.reactiveviewmodels.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.brandongogetap.scoper.Scoped;
import com.brandongogetap.scoper.Scoper;
import com.brandongogetap.scoper.ScoperContext;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseController<T> extends Controller implements Scoped {

    private Unbinder unbinder;

    protected T component;

    public BaseController() {
        this(null);
    }

    public BaseController(Bundle bundle) {
        super(bundle);
    }

    @NonNull @Override protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        View view = inflateView(LayoutInflater.from(scopedContext()), container);
        unbinder = ButterKnife.bind(this, view);
        if (component == null) {
            component = initComponent(getActivity());
        }
        onViewBound(view);
        return view;
    }

    protected abstract void onViewBound(View view);

    protected abstract View inflateView(LayoutInflater inflater, ViewGroup container);

    protected abstract T initComponent(Activity activity);

    protected Context scopedContext() {
        return new ScoperContext(getActivity(), this);
    }

    @Override public boolean handleBack() {
        boolean superHandled = super.handleBack();
        if (!superHandled) {
            Scoper.destroyScope(this);
        }
        return superHandled;
    }

    @NonNull @Override public String getScopeName() {
        return getClass().getName();
    }

    @Override protected void onDestroyView(@NonNull View view) {
        super.onDestroyView(view);
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
