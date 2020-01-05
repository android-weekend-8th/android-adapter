package com.rathana.android_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.rathana.android_adapter.entity.News;

public class CreateNewsActivity extends AppCompatActivity {

    EditText textHeadLine, textDate,textAuthor;
    Button buttonSave;
    ImageView thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_news);

        textHeadLine=findViewById(R.id.textHeadline);
        textDate=findViewById(R.id.textDate);
        textAuthor=findViewById(R.id.textAuthor);
        buttonSave=findViewById(R.id.buttonSave);
        thumbnail=findViewById(R.id.thumbnail);

        buttonSave.setOnClickListener(v-> {
            News news = new News();
            news.setTitle(textHeadLine.getText().toString());
            news.setDate(textDate.getText().toString());
            news.setAuthor(textAuthor.getText().toString());
            news.setThumbnail(R.drawable.the_rock);

            Intent intent=new Intent();
            intent.putExtra("news",news);
            setResult(RESULT_OK,intent);
            finish();
        });

    }
}
