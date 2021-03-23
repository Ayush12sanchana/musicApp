package com.example.musicclient.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.musicclient.Model.MusickFiles;
import com.example.musicclient.PlayerActivity;
import com.example.musicclient.R;


import java.util.ArrayList;

public class MusickAdapters extends RecyclerView.Adapter<MusickAdapters.MyViewHolder> {


    private Context mContext;
    private ArrayList<MusickFiles> mFiles;

    public MusickAdapters(Context mContext, ArrayList<MusickFiles> mFiles){
        this.mContext=mContext;
        this.mFiles=mFiles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.musick_items,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.file_name.setText(mFiles.get(position).getTitle());
        byte[] image =  getAlbumArt(mFiles.get(position).getPath());
        if(image != null) {
            Glide.with(mContext).asBitmap()
                    .load(image)
                    .into(holder.album_art);

        }
        else
        {
            Glide.with(mContext).asBitmap()
                    .load(R.drawable.si)
                    .into(holder.album_art);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(mContext, PlayerActivity.class);
                intent.putExtra("position", position);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFiles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView file_name;
        ImageView album_art;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            file_name= itemView.findViewById(R.id.musick_file_name);
            album_art= itemView.findViewById(R.id.musick_img);

        }
    }
    private byte[] getAlbumArt(String uri)
    {
        MediaMetadataRetriever retriever= new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return  art;
    }

}
