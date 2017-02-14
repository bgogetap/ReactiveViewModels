package com.brandongogetap.reactiveviewmodels.base;

import com.brandongogetap.reactiveviewmodels.dagger.ActivityScope;
import com.brandongogetap.reactiveviewmodels.detail.DetailComponent;
import com.brandongogetap.reactiveviewmodels.detail.DetailModule;
import com.brandongogetap.reactiveviewmodels.home.HomeComponent;
import com.brandongogetap.reactiveviewmodels.home.HomeModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {

    HomeComponent plus(HomeModule homeModule);

    DetailComponent plus(DetailModule detailModule);

    void inject(MainActivity activity);
}
