package com.brandongogetap.reactiveviewmodels.detail;

import com.brandongogetap.reactiveviewmodels.dagger.ScreenScope;

import dagger.Subcomponent;

@ScreenScope
@Subcomponent(modules = DetailModule.class)
public interface DetailComponent {

    void inject(DetailController detailController);
}
