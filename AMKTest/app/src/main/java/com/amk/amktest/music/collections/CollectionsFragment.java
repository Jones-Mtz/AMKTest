package com.amk.amktest.music.collections;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amk.amktest.R;
import com.amk.amktest.api.models.ArtistCollectionResult;
import com.amk.amktest.api.models.Collection;
import com.amk.amktest.api.providers.CollectionsProvider;
import com.amk.amktest.music.artists.RVArtistsAdapter;
import com.amk.amktest.utils.DialogUtils;
import com.amk.amktest.utils.FragmentCommunication;
import com.amk.amktest.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observer;


public class CollectionsFragment extends Fragment {
    private static final String TAG = "CollectionsFragment";

    private static final String ARTISTSNAME = "artists_name";

    private Activity activity;
    private View view;
    private String artist;

    private DialogUtils dialogUtils;

    private List<Collection> collections = new ArrayList<>();

    private FragmentCommunication fragmentCommunication;

    public CollectionsFragment() {
        // Required empty public constructor
    }

    public static CollectionsFragment newInstance(String artist) {
        CollectionsFragment fragment = new CollectionsFragment();
        Bundle args = new Bundle();
        args.putString(ARTISTSNAME, artist);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        dialogUtils = new DialogUtils(activity);
        if (getArguments() != null) {
            artist = getArguments().getString(ARTISTSNAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_collections, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunication) {
            fragmentCommunication = (FragmentCommunication) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentCommunication = null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        makeRequest();

    }

    private void makeRequest() {
        if (Utils.isOnline(activity)) {
            getCollections(artist);
        } else {
            Snackbar.make(activity.findViewById(android.R.id.content), R.string.no_internet,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, view1 -> makeRequest()).show();
        }
    }

    private void getCollections(String artist) {
        dialogUtils.showDialog();
        new CollectionsProvider().getCollections(artist).subscribe(new Observer<ArtistCollectionResult>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted() called");
                setCollections(collections);
                dialogUtils.dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
                dialogUtils.dismissDialog();

            }

            @Override
            public void onNext(ArtistCollectionResult artistCollectionResult) {
                Log.d(TAG, "onNext() called with: artistCollectionResult = [" + artistCollectionResult + "]");
                Collections.addAll(collections, artistCollectionResult.getArtistsCollections());
            }
        });
    }

    private void setCollections(List<Collection> collections) {
        RVCollectionsAdapter collectionsAdapter = new RVCollectionsAdapter(activity, collections,
                fragmentCommunication);
        ((RecyclerView) view.findViewById(R.id.rv_artists_collection)).setLayoutManager(
                new LinearLayoutManager(activity));
        ((RecyclerView) view.findViewById(R.id.rv_artists_collection)).setAdapter(collectionsAdapter);
    }
}
