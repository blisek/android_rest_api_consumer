package com.blisek.restconsumer.cats.services;

import com.blisek.restconsumer.cats.structs.CatsPictures;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public interface CatsService {

    String PICTURES_COUNT = "10";

    @GET("images/get?format=xml&results_per_page=" + PICTURES_COUNT)
    Call<CatsPictures> getRandomCatsPictures();

}
