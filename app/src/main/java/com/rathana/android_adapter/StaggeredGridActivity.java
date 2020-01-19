package com.rathana.android_adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.rathana.android_adapter.adapter.StaggeredGridAdapter;
import com.rathana.android_adapter.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class StaggeredGridActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StaggeredGridAdapter adapter;
    List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid);

        recyclerView = findViewById(R.id.staggeredRecyclerView);
        adapter = new StaggeredGridAdapter(this, products);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        2,
                        StaggeredGridLayoutManager.VERTICAL));

        recyclerView.setAdapter(adapter);

        getProducts();
    }

    private void getProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setPrice(5d);
            product.setThumbnail(R.drawable.image);

            Product product1 = new Product();
            product1.setPrice(5d);
            product1.setThumbnail(R.drawable.the_rock);

            Product product2 = new Product();
            product2.setPrice(5d);
            product2.setThumbnail(R.drawable.ic_music_note_black_24dp);


            products.add(product);
            products.add(product1);
            products.add(product2);
        }

        adapter.addItems(products);
    }
}
