package com.androidchill.niki.dagger.dagger.components;

import com.androidchill.niki.dagger.dagger.modules.NetworkModule;

import javax.inject.Singleton;
import dagger.Component;
import retrofit2.Retrofit;

/* Created by NIKI on 9/28/2016. */

@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Retrofit retrofit();
}
