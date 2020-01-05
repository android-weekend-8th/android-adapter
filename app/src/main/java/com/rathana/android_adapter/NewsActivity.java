package com.rathana.android_adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rathana.android_adapter.adapter.NewsAdapter;
import com.rathana.android_adapter.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
        implements NewsAdapter.ItemClickedListener {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList = new ArrayList<>();
    FloatingActionButton buttonCreateNews;
    static final int CREATE_NEWS_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView=findViewById(R.id.recyclerView);
        buttonCreateNews=findViewById(R.id.buttonCreate);
        adapter=new NewsAdapter(this,newsList);
        recyclerView.setAdapter(adapter);

        getNews();

        buttonCreateNews.setOnClickListener(v->{
            Intent intent = new Intent(this,CreateNewsActivity.class);
            startActivityForResult(intent,CREATE_NEWS_CODE);
        });
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode==CREATE_NEWS_CODE && resultCode==RESULT_OK){
            News news = data.getParcelableExtra("news");
            newsList.add(0,news);
            adapter.notifyItemInserted(0);
        }
    }

    @Override
    public void onDelete(int position) {
        //remove data from list
        adapter.remove(position);

    }
}
