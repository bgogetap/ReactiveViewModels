package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.database.ListItem;

import java.util.List;

import io.reactivex.Observable;

interface HomeViewProvider {

    Observable<List<ListItem>> listItems();
}
