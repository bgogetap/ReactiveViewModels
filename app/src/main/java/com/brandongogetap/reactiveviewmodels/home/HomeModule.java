package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.dagger.ScreenScope;
import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

@Module
public final class HomeModule {

    @Provides @Reusable
    static ListRepository provideListRepository(ItemDatabase database) {
        return new ListRepository(database);
    }

    @Provides @ScreenScope
    static HomeViewModel provideViewModel(ListRepository repository) {
        return new HomeViewModel(repository);
    }

    @Provides @ScreenScope
    static HomeViewProvider provideViewProvider(HomeViewModel viewModel) {
        return viewModel;
    }

    @Provides @ScreenScope
    static ItemClickedListener provideItemClickedListener(HomePresenter presenter) {
        return presenter;
    }
}
