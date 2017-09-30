package com.amk.amktest.music.genres;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amk.amktest.utils.FragmentCommunication;
import com.amk.amktest.R;
import com.amk.amktest.api.models.Genre;
import com.amk.amktest.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jones on 29/09/17.
 */

public class RVGenresAdapter extends RecyclerView.Adapter<RVGenresAdapter.ViewHolder> {

    List<Genre> genres = new ArrayList<>();
    FragmentCommunication fragmentCommunication;

    public RVGenresAdapter(List<Genre> genres, FragmentCommunication fragmentCommunication) {
        this.genres = genres;
        this.fragmentCommunication = fragmentCommunication;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_genre, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(genres.get(position));
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivGenreCover;
        private TextView tvGenreCover;

        public ViewHolder(View itemView) {
            super(itemView);
            ivGenreCover = (ImageView) itemView.findViewById(R.id.iv_genre_cover);
            tvGenreCover = (TextView) itemView.findViewById(R.id.tv_genre_title);
            itemView.setOnClickListener(this);
        }

        public void setData(Genre genre) {
            ivGenreCover.setImageDrawable(genre.getCover());
            tvGenreCover.setText(genre.getName());
        }

        @Override
        public void onClick(View view) {
            fragmentCommunication.fragmentNotification(Constants.GENRESFRAGMENT, genres.get(getAdapterPosition()));
        }
    }
}
