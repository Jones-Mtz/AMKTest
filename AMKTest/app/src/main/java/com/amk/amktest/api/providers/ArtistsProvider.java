package com.amk.amktest.api.providers;

import com.amk.amktest.api.ServiceFactory;
import com.amk.amktest.api.models.Artist;
import com.amk.amktest.api.models.ArtistsResult;
import com.amk.amktest.api.models.deserializers.ArtistsDeserializer;
import com.amk.amktest.api.services.ArtistsService;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jones on 29/09/17.
 */

public class ArtistsProvider {

    private ArtistsService service;

    public ArtistsProvider() {
        service = ServiceFactory.getServiceInstance(ArtistsService.class, GsonConverterFactory.create(
                new GsonBuilder()
                        .registerTypeAdapter(ArtistsResult.class, new ArtistsDeserializer())
                        .create()
        ));
    }

    public Observable<ArtistsResult> getArtists(String term) {
        return service.getArtistsByGenre(term)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
