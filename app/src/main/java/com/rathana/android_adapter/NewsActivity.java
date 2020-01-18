package com.rathana.android_adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rathana.android_adapter.adapter.NewsAdapter;
import com.rathana.android_adapter.entity.News;
import com.rathana.android_adapter.util.LoadMoreLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
        implements NewsAdapter.ItemClickedListener {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<News> newsList = new ArrayList<>();
    FloatingActionButton buttonCreateNews;
    static final int CREATE_NEWS_CODE = 1;
    static final int EDIT_NEWS_CODE = 2;
    private int itemPosition;

    private int currentPage = 0;
    private boolean isLoadMore = true;
    LoadMoreLinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        recyclerView = findViewById(R.id.recyclerView);
        buttonCreateNews = findViewById(R.id.buttonCreate);

        getNews();
        setupRecyclerView();
        getMoreItems();

        buttonCreateNews.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateNewsActivity.class);
            startActivityForResult(intent, CREATE_NEWS_CODE);
        });
    }

    private void getNews() {
        for (int i = 0; i < 25; i++) {
            News news = new News();
            news.setThumbnail(R.drawable.image);
            news.setTitle("Title " + i);
            news.setDate("04/01/2020");
            news.setAuthor("Dong Dara");
            newsList.add(news);
        }
    }

    private void getMoreItems() {
        List<News> newsSubList = new ArrayList<>();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 25; i++) {
                    News news = new News();
                    news.setThumbnail(R.drawable.image);
                    news.setTitle("Title " + i);
                    news.setDate("04/01/2020");
                    news.setAuthor("Dong Dara");
                    newsSubList.add(news);
                }
                adapter.addMOreItems(newsSubList);
                adapter.setCanLoadMore(true);
                layoutManager.loadingFinished();
            }

        }, 1000);
    }

    private void setupRecyclerView() {

        adapter = new NewsAdapter(this, newsList);
        adapter.setCanLoadMore(false);
        layoutManager = new LoadMoreLinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        layoutManager.setLoadMOreListener(new LoadMoreLinearLayoutManager.OnLoadMOreListener() {
            @Override
            public void onLoadMore() {
                //add load more items
                getMoreItems();
            }
        });
        layoutManager.loadingFinished();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_NEWS_CODE && resultCode == RESULT_OK) {
            News news = data.getParcelableExtra("news");
            newsList.add(0, news);
            adapter.notifyItemInserted(0);
        } else if (requestCode == EDIT_NEWS_CODE && resultCode == RESULT_OK) {
            News news = data.getParcelableExtra("news");
            if (news != null) {
                adapter.update(news, itemPosition);
            }
        }
    }

    @Override
    public void onDelete(int position) {
        //remove data from list
        adapter.remove(position);

    }

    @Override
    public void onUpdate(News news, int position) {
        itemPosition = position;
        Intent intent = new Intent(this, EditNewsActivity.class);
        intent.putExtra("news", news);
        startActivityForResult(intent, EDIT_NEWS_CODE);
    }
}
