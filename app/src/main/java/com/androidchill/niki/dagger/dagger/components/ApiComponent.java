package com.androidchill.niki.dagger.dagger.components;

import com.androidchill.niki.dagger.activity.MainActivity;
import com.androidchill.niki.dagger.dagger.modules.ApiModule;
import com.androidchill.niki.dagger.dagger.modules.CustomScope;

import dagger.Component;

/* Created by NIKI on 9/28/2016. */

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {
    void inject(MainActivity mainActivity);
}
