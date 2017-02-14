package com.brandongogetap.reactiveviewmodels.database;

public final class ListItem {

    public final int id;
    public final String title;
    public final String message;
    public final int color;

    ListItem(int id, String title, String message, int color) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.color = color;
    }
}
