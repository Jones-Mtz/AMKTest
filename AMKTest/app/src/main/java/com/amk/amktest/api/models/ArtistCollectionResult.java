package com.amk.amktest.api.models;

import com.amk.amktest.api.models.Collection;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jones on 29/09/17.
 */

public class ArtistCollectionResult {

    @SerializedName("resultCount")
    @Expose
    private int resultCount;

    @SerializedName("results")
    @Expose
    private Collection[] artistsCollections;

    public ArtistCollectionResult() {
    }

    public int getResultCount() {
        return resultCount;
    }

    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

    public Collection[] getArtistsCollections() {
        return artistsCollections;
    }

    public void setArtistsCollections(Collection[] artistsCollections) {
        this.artistsCollections = artistsCollections;
    }
}
