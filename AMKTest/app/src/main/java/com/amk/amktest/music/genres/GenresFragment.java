package com.amk.amktest.music.genres;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amk.amktest.utils.FragmentCommunication;
import com.amk.amktest.R;
import com.amk.amktest.api.providers.GenresProvider;

public class GenresFragment extends Fragment {

    private View mainView;
    private Activity activity;

    private FragmentCommunication fragmentCommunication;

    public GenresFragment() {
    }

    public static GenresFragment newInstance() {
        GenresFragment fragment = new GenresFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        activity = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genres, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RVGenresAdapter genresAdapter = new RVGenresAdapter(
                new GenresProvider().getGenders(), fragmentCommunication);

        ((RecyclerView) view.findViewById(R.id.rv_main_genres)).setLayoutManager(
                new LinearLayoutManager(activity));

        ((RecyclerView) view.findViewById(R.id.rv_main_genres)).setAdapter(genresAdapter);

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


}
