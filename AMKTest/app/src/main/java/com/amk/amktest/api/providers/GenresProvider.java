package com.amk.amktest.api.providers;

import com.amk.amktest.api.models.Genre;

import java.util.ArrayList;

/**
 * Created by Jones on 29/09/17.
 */

public class GenresProvider {
    public GenresProvider() {
    }

    public ArrayList<Genre> getGenders() {
        ArrayList<Genre> genres = new ArrayList<>();

        genres.add(new Genre("Pop"));
        genres.add(new Genre("Rock"));
        genres.add(new Genre("Metal"));
        genres.add(new Genre("Soul"));
        genres.add(new Genre("Jazz"));

        return genres;
    }
}
