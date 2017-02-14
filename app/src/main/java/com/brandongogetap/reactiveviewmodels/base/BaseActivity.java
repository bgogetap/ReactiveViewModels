package com.brandongogetap.reactiveviewmodels.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.brandongogetap.scoper.Scoped;
import com.brandongogetap.scoper.Scoper;
import com.brandongogetap.scoper.ScoperContext;

public abstract class BaseActivity<T> extends AppCompatActivity implements Scoped {

    protected T component;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = initComponent();
    }

    @NonNull @Override public String getScopeName() {
        return getClass().getName();
    }

    protected abstract T initComponent();

    @Override protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(new ScoperContext(newBase, this));
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            Scoper.destroyScope(this);
        }
    }
}
