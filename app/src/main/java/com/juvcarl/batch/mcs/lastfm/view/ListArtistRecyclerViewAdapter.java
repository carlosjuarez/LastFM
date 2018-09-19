package com.juvcarl.batch.mcs.lastfm.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juvcarl.batch.mcs.lastfm.R;
import com.juvcarl.batch.mcs.lastfm.model.Artist;

import java.util.List;

public class ListArtistRecyclerViewAdapter extends RecyclerView.Adapter<ListArtistRecyclerViewAdapter.ViewHolder> {

    private List<Artist> mValues;
    private final SelectArtistInterface mListener;

    public ListArtistRecyclerViewAdapter(List<Artist> items, SelectArtistInterface listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_artist_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentView.setText(mValues.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnArtistSelected(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mValues!=null){
            return mValues.size();
        }else{
            return 0;
        }

    }

    public void updateList(List<Artist> artists) {
        this.mValues = artists;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Artist mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
