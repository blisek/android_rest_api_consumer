package com.blisek.restconsumer.icndb.services;

import com.blisek.restconsumer.icndb.structs.Joke;
import com.blisek.restconsumer.icndb.structs.Jokes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public interface ICNDbService {

    @GET("jokes/random/{count}")
    Call<Jokes> getRandomJokes(@Path("count") int count);

}
