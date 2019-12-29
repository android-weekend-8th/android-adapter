package com.rathana.android_adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SongListAdapter extends BaseAdapter {

    private List<Song> songs;

    public SongListAdapter(List<Song> songs){
        this.songs=songs;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View v = null;
        if(convertView==null)
            v= LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.layout_song_item,
                    viewGroup,
                    false
            );

        else
            v=convertView;

        ImageView thumbnail= v.findViewById(R.id.thumbnail);
        TextView textTitle= v.findViewById(R.id.title);
        Song song= songs.get(i);
        thumbnail.setImageResource(song.getThumbnail());
        textTitle.setText(song.getTitle());

        return v;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int i) {
        return songs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
