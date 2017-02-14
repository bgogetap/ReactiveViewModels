package com.brandongogetap.reactiveviewmodels.base;

import android.support.annotation.Nullable;

import com.brandongogetap.reactiveviewmodels.dagger.ActivityScope;

import javax.inject.Inject;

@ActivityScope
public final class ActivityService {

    @Nullable private MainActivity activity;

    @Inject ActivityService() {

    }

    void attach(MainActivity activity) {
        this.activity = activity;
    }

    void detach() {
        this.activity = null;
    }

    @Nullable public MainActivity getActivity() {
        return activity;
    }
}
