package com.brandongogetap.reactiveviewmodels.detail;

import com.brandongogetap.reactiveviewmodels.dagger.ScreenScope;
import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public final class DetailModule {

    private final int itemId;

    DetailModule(int itemId) {
        this.itemId = itemId;
    }

    @Provides @ScreenScope DetailRepository provideRepository(ItemDatabase database) {
        return new DetailRepository(database, itemId);
    }

    @Provides @ScreenScope static DetailViewModel provideViewModel(DetailRepository repository) {
        return new DetailViewModel(repository);
    }

    @Provides @ScreenScope DetailViewProvider provideViewProvider(DetailViewModel viewModel) {
        return viewModel;
    }
}
