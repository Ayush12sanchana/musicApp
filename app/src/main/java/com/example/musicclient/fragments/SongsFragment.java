package com.example.musicclient.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicclient.Adapter.MusickAdapters;
import com.example.musicclient.R;


import static com.example.musicclient.SongsActivity.musickFiles;



public class SongsFragment extends Fragment {


    RecyclerView recyclerView;
    MusickAdapters musickAdapters;

    public SongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_songs, container, false);
        recyclerView = view.findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);

        if(!(musickFiles.size()<1))
        {

            musickAdapters = new MusickAdapters(getContext(), musickFiles);
            recyclerView.setAdapter(musickAdapters);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,
                    false));

        }

        return view;
    }


}
