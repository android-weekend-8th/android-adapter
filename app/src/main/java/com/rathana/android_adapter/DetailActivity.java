package com.rathana.android_adapter;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView textViewDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        textViewDetail = findViewById(R.id.textDetail);
        Intent intent = getIntent();

        if (intent != null) {
            String country =
                    intent.getStringExtra("data");
            textViewDetail.setText(country);
        }

    }
}
