package com.amk.amktest.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jones on 29/09/17.
 */

public class Artist {
    @SerializedName("artistName")
    @Expose
    private String name;

    @SerializedName("collectionName")
    @Expose
    private String albumName;

    @SerializedName("collectionPrice")
    @Expose
    private String collectionPrice;

    @SerializedName("trackPrice")
    @Expose
    private String trackPrice;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("country")
    @Expose
    private String Country;

    @SerializedName("artworkUrl100")
    @Expose
    private String cover;

    public Artist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
