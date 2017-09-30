package com.amk.amktest.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Jones on 29/09/17.
 */

public class ArtistsResult {
    @SerializedName("resultCount")
    @Expose
    private int resultCount;

    @SerializedName("results")
    @Expose
    private Artist[] artists;

    public ArtistsResult() {
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public void setArtists(Artist[] artists) {
        this.artists = artists;
    }
}
