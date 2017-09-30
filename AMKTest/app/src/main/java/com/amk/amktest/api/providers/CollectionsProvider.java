package com.amk.amktest.api.providers;

import com.amk.amktest.api.ServiceFactory;
import com.amk.amktest.api.models.ArtistCollectionResult;
import com.amk.amktest.api.models.ArtistsResult;
import com.amk.amktest.api.models.deserializers.ArtistsDeserializer;
import com.amk.amktest.api.models.deserializers.CollectionsDeserializer;
import com.amk.amktest.api.services.ArtistsService;
import com.amk.amktest.api.services.CollectionsService;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jones on 29/09/17.
 */

public class CollectionsProvider {

    private CollectionsService service;

    public CollectionsProvider() {
        service = ServiceFactory.getServiceInstance(CollectionsService.class, GsonConverterFactory.create(
                new GsonBuilder()
                        .registerTypeAdapter(ArtistCollectionResult.class, new CollectionsDeserializer())
                        .create()
        ));
    }

    public Observable<ArtistCollectionResult> getCollections(String term) {
        return service.getCollections(term, "album")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
