package com.brandongogetap.reactiveviewmodels.detail;

import com.jakewharton.rxrelay2.BehaviorRelay;

import io.reactivex.Observable;

final class DetailViewModel implements DetailViewProvider {

    private final BehaviorRelay<String> titleRelay;
    private final BehaviorRelay<String> messageRelay;
    private final BehaviorRelay<Integer> colorRelay;
    private final BehaviorRelay<Integer> idRelay;

    DetailViewModel(DetailRepository repository) {
        titleRelay = BehaviorRelay.create();
        messageRelay = BehaviorRelay.create();
        colorRelay = BehaviorRelay.create();
        idRelay = BehaviorRelay.create();
        repository.getItem().subscribe(listItem -> {
            titleRelay.accept(listItem.title());
            messageRelay.accept(listItem.message());
            colorRelay.accept(listItem.color());
            idRelay.accept(listItem.id());
        });
    }

    @Override public Observable<String> title() {
        return titleRelay;
    }

    @Override public Observable<String> message() {
        return messageRelay;
    }

    @Override public Observable<Integer> id() {
        return idRelay;
    }

    @Override public Observable<Integer> color() {
        return colorRelay;
    }
}
