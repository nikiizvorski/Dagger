package com.androidchill.niki.dagger.service;

import com.androidchill.niki.dagger.model.Response;
import com.androidchill.niki.dagger.model.ResponsePets;
import retrofit2.http.GET;
import rx.Observable;

/* Created by NIKI on 9/28/2016. */

public interface ServiceFactory {
    String BASE_URL = "https://eu.api.battle.net/wow/";

    @GET("mount/?locale=en_GB&apikey=amd7ujwtgawpy97kswhhk38xsxtbdn2m")
    Observable<Response> getMounts();

    //https://us.api.battle.net/wow/pet/?locale=en_US&apikey=amd7ujwtgawpy97kswhhk38xsxtbdn2m
    @GET("pet/?locale=en_GB&apikey=amd7ujwtgawpy97kswhhk38xsxtbdn2m")
    Observable<ResponsePets> getPets();
}
