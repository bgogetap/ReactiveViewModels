package com.brandongogetap.reactiveviewmodels.detail;

import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;
import com.brandongogetap.reactiveviewmodels.database.ListItem;

import io.reactivex.Observable;

final class DetailRepository {

    private final ItemDatabase database;
    private final int itemId;

    DetailRepository(ItemDatabase database, int itemId) {
        this.database = database;
        this.itemId = itemId;
    }

    Observable<ListItem> getItem() {
        return database.itemForId(itemId);
    }
}
