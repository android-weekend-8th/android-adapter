package com.rathana.android_adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.rathana.android_adapter.adapter.NewsAdapter;
import com.rathana.android_adapter.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView=findViewById(R.id.recyclerView);

        adapter=new NewsAdapter(newsList);
        recyclerView.setAdapter(adapter);

        getNews();
    }

    private void getNews(){
        for (int i=0; i<100 ; i++){

            News news=new News();
            news.setThumbnail(R.drawable.image);
            news.setTitle("Title "+i);
            news.setDate("04/01/2020");
            news.setAuthor("Dong Dara");

            newsList.add(news);
        }

        adapter.notifyDataSetChanged();
    }
}
