package umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.music;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedList;

import umn.ac.id.erikaatmaja_00000027299_if633_bl_uts.R;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ItemMusicViewHolder> {
    private ArrayList<SongFiles> mDaftarMusic;
    private LayoutInflater mInflater;
    private Context mContext;

    public SongAdapter(Context context, ArrayList<SongFiles> mDaftarMusic) {
        this.mContext = context;
        this.mDaftarMusic = mDaftarMusic;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemMusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.music_item_layout, parent, false);
        return new ItemMusicViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMusicViewHolder holder, int position) {
        SongFiles mSongfiles = mDaftarMusic.get(position);
        holder.tvJudul.setText(mSongfiles.getTitle());
        byte[] image = getSongThumbnail(mSongfiles.getPath());

        if(image != null)
        {
            Glide.with(mContext).asBitmap().load(image).into(holder.thumbnail);
        }
        else
        {
            Glide.with(mContext).load(R.drawable.thumbnail).into(holder.thumbnail);
        }
    }

    @Override
    public int getItemCount() {
        return mDaftarMusic.size();
    }

    class ItemMusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView thumbnail;
        private TextView tvJudul, tvURI;
        private SongAdapter mAdapter;
        private int mPosisi;
        private SongFiles mSongfiles;

        public ItemMusicViewHolder(@NonNull View itemView, SongAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            tvJudul = (TextView) itemView.findViewById(R.id.tvJudul);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSongfiles = mDaftarMusic.get(mPosisi);
            Intent detailIntent = new Intent(mContext, SongDetailActivity.class);
            detailIntent.putExtra("position", mPosisi);
            mContext.startActivity(detailIntent);
        }
    }
    private byte[] getSongThumbnail(String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
