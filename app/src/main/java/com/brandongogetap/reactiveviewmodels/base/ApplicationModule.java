package com.brandongogetap.reactiveviewmodels.base;

import com.brandongogetap.reactiveviewmodels.database.ItemDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
final class ApplicationModule {

    @Provides @Singleton static ItemDatabase provideDatabase() {
        return new ItemDatabase();
    }
}
