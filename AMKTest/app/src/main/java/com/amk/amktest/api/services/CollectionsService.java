package com.amk.amktest.api.services;

import com.amk.amktest.api.models.ArtistCollectionResult;
import com.amk.amktest.api.models.ArtistsResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jones on 29/09/17.
 */

public interface CollectionsService {

    @GET("search")
    Observable<ArtistCollectionResult> getCollections(@Query("term") String term, @Query("entity") String entity);

}
