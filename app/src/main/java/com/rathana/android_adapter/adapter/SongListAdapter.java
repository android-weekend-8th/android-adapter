package com.rathana.android_adapter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rathana.android_adapter.R;
import com.rathana.android_adapter.entity.Song;

import java.util.List;

public class SongListAdapter extends BaseAdapter {

    private List<Song> songs;
    private boolean isClicked=false;

    public SongListAdapter(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public View getView(int i, View convertView, final ViewGroup viewGroup) {
        View v = null;
        if (convertView == null)
            v = LayoutInflater.from(viewGroup.getContext()).inflate(
                    R.layout.layout_song_item,
                    viewGroup,
                    false
            );

        else
            v = convertView;

        ImageView thumbnail = v.findViewById(R.id.thumbnail);
        TextView textTitle = v.findViewById(R.id.title);
        final ImageButton buttonFavorite = v.findViewById(R.id.buttonFavorite);
        Song song = songs.get(i);
        thumbnail.setImageResource(song.getThumbnail());
        textTitle.setText(song.getTitle());

        buttonFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(viewGroup.getContext(),
                        "Favorite clicked",
                        Toast.LENGTH_SHORT).show();
                if(isClicked){
                    isClicked= false;
                    buttonFavorite.setImageResource(R.drawable.ic_favorite_border_red_24dp);
                } else {
                    isClicked=true;
                    buttonFavorite.setImageResource(R.drawable.ic_favorite_border_gray_24dp);
                }
            }
        });

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
