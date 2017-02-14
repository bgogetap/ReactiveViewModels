package com.brandongogetap.reactiveviewmodels.database;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Mock database
 */
public final class ItemDatabase {

    private static final int[] COLORS = new int[]{
            Color.BLUE,
            Color.GRAY,
            Color.GREEN,
            Color.CYAN,
            Color.DKGRAY
    };
    private static final List<ListItem> items = generateItems();

    private static List<ListItem> generateItems() {
        List<ListItem> items = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            String title = String.format("Item #%d", i);
            String message = String.format("This is the %d item", i);
            int color = COLORS[i % 5];
            items.add(new ListItem(i, title, message, color));
        }
        return items;
    }

    public Observable<List<ListItem>> items() {
        return Observable.just(items);
    }

    public Observable<ListItem> itemForId(int id) {
        for (ListItem item : items) {
            if (item.id == id) {
                return Observable.just(item);
            }
        }
        throw new IllegalArgumentException("No item for id: " + id);
    }
}
