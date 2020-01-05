package com.rathana.android_adapter;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.rathana.android_adapter.adapter.StoreAdapter;
import com.rathana.android_adapter.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private GridView gridView;
    private StoreAdapter storeAdapter;
    List<Product> products = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        gridView = findViewById(R.id.gridView);

        storeAdapter = new StoreAdapter(products);
        gridView.setAdapter(storeAdapter);

        getProducts();

    }

    private void getProducts() {
        for (int i = 0; i < 100; i++) {
            Product product = new Product();
            product.setPrice(10D);
            product.setThumbnail(R.drawable.image);

            Product product2 = new Product();
            product2.setPrice(10D);
            product2.setThumbnail(R.drawable.the_rock);

            products.add(product);
            products.add(product2);
        }

        storeAdapter.notifyDataSetChanged();
    }
}
