package com.amk.amktest.music.artists;

import android.app.Activity;
import android.content.Context;
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

import com.amk.amktest.api.models.Artist;
import com.amk.amktest.api.models.ArtistsResult;
import com.amk.amktest.utils.DialogUtils;
import com.amk.amktest.utils.FragmentCommunication;
import com.amk.amktest.R;
import com.amk.amktest.api.providers.ArtistsProvider;
import com.amk.amktest.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observer;


public class ArtistsFragment extends Fragment {
    private static final String TAG = "ArtistsFragment";

    private static final String GENRENAME = "genre";

    private Activity activity;
    private View view;
    private String genre;
    private DialogUtils dialogUtils;

    private List<Artist> artists = new ArrayList<>();

    private FragmentCommunication fragmentCommunication;

    public ArtistsFragment() {
        // Required empty public constructor
    }

    public static ArtistsFragment newInstance(String genreName) {
        ArtistsFragment fragment = new ArtistsFragment();
        Bundle args = new Bundle();
        args.putString(GENRENAME, genreName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
        dialogUtils = new DialogUtils(activity);
        if (getArguments() != null) {
            genre = getArguments().getString(GENRENAME);
        } else genre = "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_artists, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        makeRequest();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunication) {
            fragmentCommunication = (FragmentCommunication) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement FragmentCommunication");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentCommunication = null;
    }


    private void makeRequest(){
        if (Utils.isOnline(activity)) {
            getArtists(genre);
        } else {
            Snackbar.make(activity.findViewById(android.R.id.content), R.string.no_internet,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.retry, view1 -> makeRequest()).show();
        }
    }

    public void getArtists(String genre) {
        dialogUtils.showDialog();
        new ArtistsProvider().getArtists(genre).subscribe(new Observer<ArtistsResult>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted() called");
                setArtists(artists);
                dialogUtils.dismissDialog();
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() called with: e = [" + e + "]");
                dialogUtils.dismissDialog();
            }

            @Override
            public void onNext(ArtistsResult artist) {
                Log.d(TAG, "onNext() called with: artist = [" + artist + "]");
                Collections.addAll(artists, artist.getArtists());
            }
        });
    }

    private void setArtists(List<Artist> artists) {
        RVArtistsAdapter artistsAdapter = new RVArtistsAdapter(activity, artists,
                fragmentCommunication);
        ((RecyclerView) view.findViewById(R.id.rv_genre_artists)).setLayoutManager(
                new LinearLayoutManager(activity));
        ((RecyclerView) view.findViewById(R.id.rv_genre_artists)).setAdapter(artistsAdapter);
    }
}
