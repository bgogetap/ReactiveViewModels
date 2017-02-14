package com.brandongogetap.reactiveviewmodels.home;

import com.brandongogetap.reactiveviewmodels.dagger.ScreenScope;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = HomeModule.class)
public interface HomeComponent {

    void inject(ItemViewHolder viewHolder);

    void inject(HomeController homeController);
}
