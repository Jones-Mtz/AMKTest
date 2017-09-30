package com.amk.amktest.music.artists;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amk.amktest.R;
import com.amk.amktest.api.models.Artist;
import com.amk.amktest.utils.Constants;
import com.amk.amktest.utils.FragmentCommunication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jones on 29/09/17.
 */

public class RVArtistsAdapter extends RecyclerView.Adapter<RVArtistsAdapter.ViewHolder> {

    private Context context;
    private List<Artist> artists = new ArrayList<>();
    private FragmentCommunication fragmentCommunication;

    public RVArtistsAdapter(Context context, List<Artist> artists, FragmentCommunication fragmentCommunication) {
        this.context = context;
        this.artists = artists;
        this.fragmentCommunication = fragmentCommunication;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);

        return new RVArtistsAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(artists.get(position));

    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivArtistCover;
        private TextView tvArtistName;
        private TextView tvArtistAlbumName;
        private TextView tvArtistAlbumPrice;
        private TextView tvArtistTrackPrice;
        private TextView tvArtistCurrency;
        private TextView tvArtistCountry;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            ivArtistCover = (ImageView) itemView.findViewById(R.id.iv_artist_cover);
            tvArtistName = (TextView) itemView.findViewById(R.id.tv_artist_name);
            tvArtistAlbumName = (TextView) itemView.findViewById(R.id.tv_artist_album);
            tvArtistAlbumPrice = (TextView) itemView.findViewById(R.id.tv_artist_album_price);
            tvArtistTrackPrice = (TextView) itemView.findViewById(R.id.tv_artist_song_price);
            tvArtistTrackPrice = (TextView) itemView.findViewById(R.id.tv_artist_song_price);
            tvArtistCurrency = (TextView) itemView.findViewById(R.id.tv_artist_type);
            tvArtistCountry = (TextView) itemView.findViewById(R.id.tv_artist_country);
        }

        public void setData(Artist artist) {
            Picasso.with(context).load(artist.getCover()).into(ivArtistCover);
            tvArtistName.setText(artist.getName());
            tvArtistAlbumName.setText(artist.getAlbumName());
            tvArtistAlbumPrice.setText(artist.getCollectionPrice());
            tvArtistTrackPrice.setText(artist.getTrackPrice());
            tvArtistCurrency.setText(artist.getCurrency());
            tvArtistCountry.setText(artist.getCurrency());
        }

        @Override
        public void onClick(View view) {
            fragmentCommunication.fragmentNotification(Constants.ARTISTSFRAGMENT,
                    artists.get(getAdapterPosition()));
        }
    }
}
