package com.rathana.android_adapter;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.android_adapter.adapter.SongListAdapter;
import com.rathana.android_adapter.entity.Song;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends AppCompatActivity {

    ListView listView;
    SongListAdapter adapter;
    List<Song> songs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        listView = findViewById(R.id.listView);

        for (int i = 0; i < 50; i++) {

            Song song = new Song();
            song.setThumbnail(R.drawable.ic_music_note_black_24dp);
            song.setTitle("See you again " + i);
            songs.add(song);

            Song song2 = new Song();
            song2.setThumbnail(R.drawable.the_rock);
            song2.setTitle("Jumanji II " + i);
            songs.add(song2);

        }

        adapter = new SongListAdapter(songs);
        listView.setAdapter(adapter);


    }


}
