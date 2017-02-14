package com.brandongogetap.reactiveviewmodels.base;

import android.app.Application;

import com.brandongogetap.scoper.Scoper;

public final class MyApplication extends Application {

    public static final String SCOPE = "application_scope";

    @Override public void onCreate() {
        super.onCreate();
        Scoper.createComponent(() -> SCOPE, DaggerApplicationComponent.create());
    }
}
