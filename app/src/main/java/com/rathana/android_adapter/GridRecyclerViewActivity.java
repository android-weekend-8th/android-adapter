package com.rathana.android_adapter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rathana.android_adapter.adapter.GridAdapter;
import com.rathana.android_adapter.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GridRecyclerViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    GridAdapter adapter;
    List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_recycler_view);

        setupRecyclerView();
        getProducts();
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.gridRecylerView);
        adapter = new GridAdapter(this, products);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    private void getProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Product product = new Product();
            product.setPrice(5d);
            product.setThumbnail(R.drawable.image);
            products.add(product);
        }

        adapter.addItems(products);
    }
}
