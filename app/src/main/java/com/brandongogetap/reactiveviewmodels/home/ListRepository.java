package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;
import com.brandongogetap.reactiveviewmodels.database.ListItem;

import java.util.List;

import io.reactivex.Observable;

final class ListRepository {

    private final ItemDatabase database;

    ListRepository(ItemDatabase database) {
        this.database = database;
    }

    Observable<List<ListItem>> getItems() {
        return database.items();
    }
}
