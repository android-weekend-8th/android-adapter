package com.rathana.android_adapter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.rathana.android_adapter.entity.News;

public class EditNewsActivity extends AppCompatActivity {

    EditText textHeadLine, textDate, textAuthor;
    Button buttonSaveChange;
    ImageView thumbnail, buttonPickImage;
    News news;
    private String imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_news);

        textHeadLine = findViewById(R.id.textHeadline);
        textDate = findViewById(R.id.textDate);
        textAuthor = findViewById(R.id.textAuthor);
        buttonSaveChange = findViewById(R.id.buttonSaveChange);
        thumbnail = findViewById(R.id.thumbnail);
        buttonPickImage = findViewById(R.id.buttonPickImage);

        Intent intent = getIntent();
        if (intent != null) {
            news = intent.getParcelableExtra("news");
            textHeadLine.setText(news.getTitle());
            textAuthor.setText(news.getAuthor());
            textDate.setText(news.getDate());
            thumbnail.setImageResource(news.getThumbnail());

        }

        buttonSaveChange.setOnClickListener(v -> {
            news.setTitle(textHeadLine.getText().toString());
            news.setAuthor(textAuthor.getText().toString());
            news.setDate(textDate.getText().toString());
            if (imagePath != null) {
                news.setImagePath(imagePath);
            }
            Intent i = new Intent();
            i.putExtra("news", news);
            setResult(RESULT_OK, i);
            finish();
        });

        buttonPickImage.setOnClickListener(v -> {
            requestPermission();
            imagePicker();
        });
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    101);
        }
    }

    static final int CODE_PICK_IMAGE = 200;

    private void imagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        startActivityForResult(intent, CODE_PICK_IMAGE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_PICK_IMAGE && resultCode == RESULT_OK) {

            try {
                Uri uri = data.getData();
                String[] columnInfo = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(
                        uri,
                        columnInfo,
                        null,
                        null,
                        null
                );
                cursor.moveToNext();
                int columnIndex = cursor.getColumnIndex(columnInfo[0]);
                imagePath = cursor.getString(columnIndex);

                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                thumbnail.setImageBitmap(bitmap);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
