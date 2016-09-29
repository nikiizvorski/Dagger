package com.androidchill.niki.dagger.dagger.modules;

import com.androidchill.niki.dagger.service.ServiceFactory;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/* Created by NIKI on 9/28/2016. */

@Module
public class ApiModule {

    @Provides
    @CustomScope
    ServiceFactory provideMountApiService(Retrofit retrofit){
        return retrofit.create(ServiceFactory.class);
    }
}
