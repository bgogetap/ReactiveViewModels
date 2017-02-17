package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

final class HomeViewModel implements HomeViewProvider {

    private final BehaviorRelay<List<ListItem>> listItemRelay;
    private final Disposable repoSubscription;

    HomeViewModel(ListRepository repository) {
        listItemRelay = BehaviorRelay.create();
        repoSubscription = repository.getItems().subscribe(listItemRelay);
    }

    @Override public Observable<List<ListItem>> listItems() {
        return listItemRelay;
    }

    @Override public void release() {
        if (!repoSubscription.isDisposed()) {
            repoSubscription.dispose();
        }
    }
}
