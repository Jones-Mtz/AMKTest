package com.amk.amktest.api.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Jones on 29/09/17.
 */

public class Collection {

    @SerializedName("artistName")
    @Expose
    private String name;

    @SerializedName("collectionName")
    @Expose
    private String albumName;

    @SerializedName("releaseDate")
    @Expose
    private String releaseDate;

    @SerializedName("trackCount")
    @Expose
    private String tracksCount;

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("currency")
    @Expose
    private String currency;

    @SerializedName("trackPrice")
    @Expose
    private String trackPrice;

    @SerializedName("collectionPrice")
    @Expose
    private String collectionPrice;

    @SerializedName("isStreamable")
    @Expose
    private boolean streamabe;

    @SerializedName("artworkUrl100")
    @Expose
    private String cover;

    public Collection() {
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTracksCount() {
        return tracksCount;
    }

    public void setTracksCount(String tracksCount) {
        this.tracksCount = tracksCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTrackPrice() {
        return trackPrice;
    }

    public void setTrackPrice(String trackPrice) {
        this.trackPrice = trackPrice;
    }

    public String getCollectionPrice() {
        return collectionPrice;
    }

    public void setCollectionPrice(String collectionPrice) {
        this.collectionPrice = collectionPrice;
    }

    public boolean isStreamabe() {
        return streamabe;
    }

    public void setStreamabe(boolean streamabe) {
        this.streamabe = streamabe;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
