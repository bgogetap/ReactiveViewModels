package com.brandongogetap.reactiveviewmodels.base;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    MainComponent plus(MainModule mainModule);
}
