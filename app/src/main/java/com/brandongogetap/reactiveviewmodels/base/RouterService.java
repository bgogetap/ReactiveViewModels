package com.brandongogetap.reactiveviewmodels.base;

import android.support.annotation.Nullable;

import com.bluelinelabs.conductor.Router;

import javax.inject.Inject;

import dagger.Reusable;

@Reusable
public final class RouterService {

    private final ActivityService activityService;

    @Inject RouterService(ActivityService activityService) {
        this.activityService = activityService;
    }

    @Nullable public Router getRouter() {
        MainActivity activity = activityService.getActivity();
        return activity != null ? activity.getRouter() : null;
    }
}
