package com.brandongogetap.reactiveviewmodels.base;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.brandongogetap.reactiveviewmodels.R;
import com.brandongogetap.reactiveviewmodels.home.HomeController;
import com.brandongogetap.scoper.Scoper;

import javax.inject.Inject;

public final class MainActivity extends BaseActivity<MainComponent> {

    @Inject ActivityService activityService;

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component.inject(this);
        activityService.attach(this);
        FrameLayout container = (FrameLayout) findViewById(R.id.fl_container);
        router = Conductor.attachRouter(this, container, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new HomeController()));
        }
    }

    Router getRouter() {
        return router;
    }

    protected MainComponent initComponent() {
        return Scoper.<ApplicationComponent>withParent(MyApplication.SCOPE)
                .createChild(this, parent -> parent.plus(new MainModule()));
    }

    @Override public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        activityService.detach();
    }
}
