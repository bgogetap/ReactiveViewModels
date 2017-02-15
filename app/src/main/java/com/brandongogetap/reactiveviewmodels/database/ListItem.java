package com.brandongogetap.reactiveviewmodels.database;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ListItem {

    public static ListItem create(int id, String title, String message, int color) {
        return new AutoValue_ListItem(id, title, message, color);
    }

    public abstract int id();

    public abstract String title();

    public abstract String message();

    public abstract int color();
}
