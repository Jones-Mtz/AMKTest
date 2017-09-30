package com.amk.amktest.api.services;

import com.amk.amktest.api.models.Artist;
import com.amk.amktest.api.models.ArtistsResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jones on 29/09/17.
 */

public interface ArtistsService {

    @GET("search")
    Observable<ArtistsResult> getArtistsByGenre(@Query("term") String term);

}