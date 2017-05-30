package com.blisek.restconsumer.cats.services.generators;

import com.blisek.restconsumer.cats.services.CatsService;
import com.blisek.restconsumer.cats.structs.CatsPictures;
import com.blisek.restconsumer.icndb.services.ICNDbService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.xml.CatsApiXmlConverterFactory;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public final class CatsServiceGenerator {
    private static final String BASE_URL = "http://thecatapi.com/api/";
    private static final Object monitor = new Object();
    private volatile static CatsService service;

    public static CatsService getInstance() {
        if(service == null) {
            synchronized (monitor) {
                if(service == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(BASE_URL)
                            .addConverterFactory(CatsApiXmlConverterFactory.create())
                            .build();
                    service = retrofit.create(CatsService.class);
                }
            }
        }

        return service;
    }

}
