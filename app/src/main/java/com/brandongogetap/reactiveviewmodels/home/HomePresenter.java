package com.brandongogetap.reactiveviewmodels.home;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.brandongogetap.reactiveviewmodels.base.RouterService;
import com.brandongogetap.reactiveviewmodels.dagger.ScreenScope;
import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.brandongogetap.reactiveviewmodels.detail.DetailController;

import javax.inject.Inject;

@ScreenScope
final class HomePresenter implements ItemClickedListener {

    private final RouterService routerService;

    @Inject HomePresenter(RouterService routerService) {
        this.routerService = routerService;
    }

    @Override public void itemClicked(ListItem listItem) {
        Router router = routerService.getRouter();
        if (router != null) {
            router.pushController(RouterTransaction.with(DetailController.newInstance(listItem.id))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }
}
