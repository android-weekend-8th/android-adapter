package com.rathana.android_adapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rathana.android_adapter.R;
import com.rathana.android_adapter.entity.Product;

import java.util.List;

public class StaggeredGridAdapter extends RecyclerView.Adapter<StaggeredGridAdapter.ViewHolder> {

    List<Product> products;
    Context context;

    public StaggeredGridAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_store_staggered, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.thumb.setImageResource(product.getThumbnail());
        holder.textPrice.setText("" + product.getPrice());
        holder.buttonCart.setOnClickListener(v -> {
            Toast.makeText(context, "Add to cart", Toast.LENGTH_SHORT).show();
        });
    }

    public void addItems(List<Product> products) {
        int previousSize = this.products.size();
        this.products.addAll(products);
        notifyItemRangeInserted(previousSize, products.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton buttonCart;
        ImageView thumb;
        TextView textPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textPrice = itemView.findViewById(R.id.textPrice);
            thumb = itemView.findViewById(R.id.thumbnail);
            buttonCart = itemView.findViewById(R.id.buttonCart);
        }
    }

}
