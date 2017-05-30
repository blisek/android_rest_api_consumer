package com.blisek.restconsumer.cats.structs;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * Created by bartek-pc on 28.05.2017.
 */

public final class CatsPictures implements Serializable {
    private List<URL> picturesUrls;

    public CatsPictures() {}

    public CatsPictures(List<URL> picturesUrls) {
        this.picturesUrls = picturesUrls;
    }

    public List<URL> getPicturesUrls() {
        return picturesUrls;
    }

    public void setPicturesUrls(List<URL> picturesUrls) {
        this.picturesUrls = picturesUrls;
    }
}
