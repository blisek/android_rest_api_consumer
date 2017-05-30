package com.blisek.restconsumer.icndb.services.generators;

import com.blisek.restconsumer.icndb.services.ICNDbService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public final class ICNDbServiceGenerator {
    private static final String BASE_URL = "http://api.icndb.com";
    private static final Object monitor = new Object();
    private volatile static ICNDbService service;

    public static ICNDbService getInstance() {
        if(service == null) {
            synchronized (monitor) {
                if(service == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service = retrofit.create(ICNDbService.class);
                }
            }
        }

        return service;
    }

}
