package com.brandongogetap.reactiveviewmodels.database;

import android.graphics.Color;

import com.jakewharton.rxrelay2.BehaviorRelay;

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
        for (int i = 0; i < 10; i++) {
            items.add(generateItem(i));
        }
        return items;
    }

    private static ListItem generateItem(int seed) {
        String title = String.format("Item #%d", seed);
        String message = String.format("This is the %d item", seed);
        int color = COLORS[seed % 5];
        return ListItem.create(seed, title, message, color);
    }

    private static int getHighestId() {
        int id = 0;
        for (ListItem item : items) {
            if (id < item.id()) {
                id = item.id();
            }
        }
        return id;
    }

    /**
     * Using this allows us to emulate a reactive database.
     */
    private final BehaviorRelay<List<ListItem>> mockHotDatabaseObservable;

    public ItemDatabase() {
        mockHotDatabaseObservable = BehaviorRelay.createDefault(items);
    }

    public Observable<List<ListItem>> items() {
        return mockHotDatabaseObservable;
    }

    public Observable<ListItem> itemForId(int id) {
        for (ListItem item : items) {
            if (item.id() == id) {
                return Observable.just(item);
            }
        }
        throw new IllegalArgumentException("No item for id: " + id);
    }

    public void addItem() {
        items.add(generateItem(getHighestId() + 1));
        mockHotDatabaseObservable.accept(items);
    }

    public void removeItem(ListItem listItem) {
        items.remove(listItem);
        mockHotDatabaseObservable.accept(items);
    }
}
