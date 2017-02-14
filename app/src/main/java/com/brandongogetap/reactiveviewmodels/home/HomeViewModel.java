package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.database.ListItem;
import com.jakewharton.rxrelay2.BehaviorRelay;

import java.util.List;

import io.reactivex.Observable;

final class HomeViewModel implements HomeViewProvider {

    private final BehaviorRelay<List<ListItem>> listItemRelay;

    HomeViewModel(ListRepository repository) {
        listItemRelay = BehaviorRelay.create();
        repository.getItems().subscribe(listItemRelay);
    }

    @Override public Observable<List<ListItem>> listItems() {
        return listItemRelay;
    }
}
