package com.brandongogetap.reactiveviewmodels.home;

import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.brandongogetap.reactiveviewmodels.base.RouterService;
import com.brandongogetap.reactiveviewmodels.dagger.ScreenScope;
import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;
import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.brandongogetap.reactiveviewmodels.detail.DetailController;

import javax.inject.Inject;

import static com.bluelinelabs.conductor.RouterTransaction.with;

@ScreenScope
final class HomePresenter implements ItemClickedListener {

    private final RouterService routerService;
    private final ItemDatabase database;

    @Inject HomePresenter(
            RouterService routerService,
            ItemDatabase database
    ) {
        this.routerService = routerService;
        this.database = database;
    }

    @Override public void itemClicked(ListItem listItem) {
        Router router = routerService.getRouter();
        if (router != null) {
            router.pushController(with(DetailController.newInstance(listItem.id()))
                    .pushChangeHandler(new FadeChangeHandler())
                    .popChangeHandler(new FadeChangeHandler()));
        }
    }

    @Override public void itemLongClicked(ListItem listItem) {
        database.removeItem(listItem);
    }

    void addItemClicked() {
        database.addItem();
    }
}
