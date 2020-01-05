package com.rathana.android_adapter.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.rathana.android_adapter.DetailActivity;
import com.rathana.android_adapter.entity.Product;
import com.rathana.android_adapter.R;

import java.util.List;

public class StoreAdapter extends BaseAdapter {

    List<Product> products;

    public StoreAdapter(List<Product> products) {
        this.products = products;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        View view = null;
        if (convertView == null)
            view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_store, viewGroup, false);
        else
            view = convertView;

        binding(viewGroup.getContext(), view, products.get(i));

        return view;
    }

    private void binding(final Context context, @NonNull View view, @NonNull Product product) {
        ImageView thumbnail = view.findViewById(R.id.thumbnail);
        TextView textPrice = view.findViewById(R.id.textPrice);
        ImageView buttonCart= view.findViewById(R.id.buttonCart);
        thumbnail.setImageResource(product.getThumbnail());
        textPrice.setText("" + product.getPrice());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, DetailActivity.class));
            }
        });

        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
