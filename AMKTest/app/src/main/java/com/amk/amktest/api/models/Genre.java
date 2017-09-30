package com.amk.amktest.api.models;

import android.graphics.drawable.Drawable;

/**
 * Created by Jones on 29/09/17.
 */

public class Genre {
    private String name;
    private Drawable cover;

    public Genre(String name, Drawable cover) {
        this.name = name;
        this.cover = cover;
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getCover() {
        return cover;
    }

    public void setCover(Drawable cover) {
        this.cover = cover;
    }
}
