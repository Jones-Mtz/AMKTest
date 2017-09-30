package com.amk.amktest.music.collections;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amk.amktest.R;
import com.amk.amktest.api.models.Collection;
import com.amk.amktest.utils.FragmentCommunication;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jones on 29/09/17.
 */

public class RVCollectionsAdapter extends RecyclerView.Adapter<RVCollectionsAdapter.ViewHolder> {

    private Context context;
    private List<Collection> collections = new ArrayList<>();
    private FragmentCommunication fragmentCommunication;

    public RVCollectionsAdapter(Context context, List<Collection> collections, FragmentCommunication fragmentCommunication) {
        this.context = context;
        this.collections = collections;
        this.fragmentCommunication = fragmentCommunication;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);

        return new RVCollectionsAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(collections.get(position));
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivCollectionCover;
        private TextView tvCollectionName;
        private TextView tvCollectionAlbumName;
        private TextView tvCollectionDate;
        private TextView tvCollectionCount;
        private TextView tvCollectionCountry;
        private TextView tvCollectionCurrency;
        private TextView tvCollectionTrackPrice;
        private TextView tvCollectionAlbumPrice;
        private TextView tvCollectionAvailable;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            ivCollectionCover = (ImageView) itemView.findViewById(R.id.iv_collection_cover);
            tvCollectionName = (TextView) itemView.findViewById(R.id.tv_collection_name);
            tvCollectionAlbumName = (TextView) itemView.findViewById(R.id.tv_collection_album);
            tvCollectionDate = (TextView) itemView.findViewById(R.id.tv_collection_date);
            tvCollectionCount = (TextView) itemView.findViewById(R.id.tv_collection_trackCount);
            tvCollectionCountry = (TextView) itemView.findViewById(R.id.tv_collection_country);
            tvCollectionCurrency = (TextView) itemView.findViewById(R.id.tv_collection_type);
            tvCollectionTrackPrice = (TextView) itemView.findViewById(R.id.tv_collection_song_price);
            tvCollectionAlbumPrice = (TextView) itemView.findViewById(R.id.tv_collection_album_price);
            tvCollectionAvailable = (TextView) itemView.findViewById(R.id.tv_collection_available);

        }

        public void setData(Collection collection) {
            Picasso.with(context).load(collection.getCover()).into(ivCollectionCover);
            tvCollectionName.setText(collection.getName());
            tvCollectionAlbumName.setText(collection.getAlbumName());
            // TODO: 29/09/17 format to date
            tvCollectionDate.setText(collection.getReleaseDate());
            tvCollectionCount.setText(collection.getTracksCount());
            tvCollectionCountry.setText(collection.getCountry());
            tvCollectionCurrency.setText(collection.getCurrency());
            tvCollectionTrackPrice.setText(collection.getTrackPrice());
            tvCollectionAlbumPrice.setText(collection.getCollectionPrice());

            if (collection.isStreamabe()) {
                tvCollectionAvailable.setText("Sí");
            } else {
                tvCollectionAvailable.setText("No");
            }


        }

        @Override
        public void onClick(View view) {

        }
    }
}
