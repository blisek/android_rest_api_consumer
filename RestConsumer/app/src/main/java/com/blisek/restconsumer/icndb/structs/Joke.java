package com.blisek.restconsumer.icndb.structs;

import java.io.Serializable;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public class Joke implements Serializable {
    private long id;
    private String joke;

    public Joke() {}

    public Joke(long id, String joke) {
        this.id = id;
        this.joke = joke;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
