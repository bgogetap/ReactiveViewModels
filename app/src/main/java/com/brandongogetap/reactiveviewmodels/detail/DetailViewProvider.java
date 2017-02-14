package com.brandongogetap.reactiveviewmodels.detail;

import io.reactivex.Observable;

interface DetailViewProvider {

    Observable<String> title();

    Observable<String> message();

    Observable<Integer> id();

    Observable<Integer> color();
}
