package com.juvcarl.batch.mcs.lastfm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juvcarl.batch.mcs.lastfm.R;
import com.juvcarl.batch.mcs.lastfm.model.Artist;
import com.juvcarl.batch.mcs.lastfm.viewmodel.LastFMViewModel;
import com.squareup.picasso.Picasso;

public class ArtistFragment extends Fragment {

    private LastFMViewModel lastFMViewModel;
    TextView textView;
    ImageView imageView;

    public ArtistFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artist, container, false);

        lastFMViewModel = ViewModelProviders.of(getActivity()).get(LastFMViewModel.class);

        lastFMViewModel.getMutableArtist().observe(this, new Observer<Artist>() {
            @Override
            public void onChanged(@Nullable Artist artist) {
                showArtist(artist);
            }
        });

        textView = view.findViewById(R.id.tv_artist);
        imageView = view.findViewById(R.id.iv_artist);


        return view;
    }

    private void showArtist(Artist artist) {
        textView.setText(artist.getName());
        Picasso.get()
                .load(artist.getImage().get(3).getText())
                .into(imageView);
    }


}
