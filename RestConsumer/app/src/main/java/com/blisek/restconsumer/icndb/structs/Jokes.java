package com.blisek.restconsumer.icndb.structs;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public class Jokes implements Serializable {
    private String type;
    private List<Joke> value;

    public Jokes() {}

    public Jokes(String type, List<Joke> value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Joke> getValue() {
        return value;
    }

    public void setValue(List<Joke> value) {
        this.value = value;
    }
}
