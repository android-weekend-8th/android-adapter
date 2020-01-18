package com.rathana.android_adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.android_adapter.entity.News;

public class DetailNewsActivity extends AppCompatActivity {

    ImageView thumbnail;
    TextView textHeadline;
    TextView textAuthor;
    TextView textDate;
    TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        thumbnail = findViewById(R.id.thumbnail);
        textHeadline = findViewById(R.id.textHeadline);
        textAuthor = findViewById(R.id.textAuthor);
        textDate = findViewById(R.id.textDate);
        textContent = findViewById(R.id.content);

        if (getIntent() != null) {

            News news = getIntent().getParcelableExtra("news");
            textHeadline.setText(news.getTitle());
            textAuthor.setText(news.getAuthor());
            textDate.setText(news.getDate());
            if (news.getImagePath() != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(news.getImagePath());
                thumbnail.setImageBitmap(bitmap);
            } else {
                thumbnail.setImageResource(news.getThumbnail());
            }

        }

    }
}
