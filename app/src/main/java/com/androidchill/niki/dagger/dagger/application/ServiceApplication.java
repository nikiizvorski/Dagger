package com.androidchill.niki.dagger.dagger.application;

import android.app.Application;

import com.androidchill.niki.dagger.dagger.components.ApiComponent;
import com.androidchill.niki.dagger.dagger.components.DaggerApiComponent;
import com.androidchill.niki.dagger.dagger.components.DaggerNetworkComponent;
import com.androidchill.niki.dagger.dagger.components.NetworkComponent;
import com.androidchill.niki.dagger.dagger.modules.NetworkModule;
import com.androidchill.niki.dagger.service.ServiceFactory;

/* Created by NIKI on 9/28/2016. */

public class ServiceApplication extends Application {
    private ApiComponent apiComponent;

    @Override
    public void onCreate() {
        injectDependencies();
        super.onCreate();
        
        Picasso.Builder builder = new Picasso.Builder(this);
        builder.downloader(new OkHttp3Downloader(this, Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);
    }

    private void injectDependencies() {
        apiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    public NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(ServiceFactory.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
