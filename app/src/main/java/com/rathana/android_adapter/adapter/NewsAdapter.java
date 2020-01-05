package com.rathana.android_adapter.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rathana.android_adapter.DetailActivity;
import com.rathana.android_adapter.R;
import com.rathana.android_adapter.entity.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    List<News> newsList;
    Context context;
    ItemClickedListener listener;

    public NewsAdapter(Context context,List<News> newsList) {
        this.context=context;
        this.newsList = newsList;
        this.listener=(ItemClickedListener) context;
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        News news= newsList.get(position);
        holder.thumbnail.setImageResource(news.getThumbnail());
        holder.textAuthor.setText(news.getAuthor());
        holder.textDate.setText(news.getDate());
        holder.textTitle.setText(news.getTitle());

        holder.itemView.setOnClickListener(view->{
            Intent intent=new Intent(context, DetailActivity.class);
            context.startActivity(intent);
        });

        holder.buttonDelete.setOnClickListener(v->{
            if(listener!=null)
                listener.onDelete(holder.getAdapterPosition());
        });

    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail;
        TextView textTitle;
        TextView textDate;
        TextView textAuthor;
        ImageButton buttonDelete,buttonEdit;
        public ViewHolder(View itemView){
            super(itemView);
            thumbnail= itemView.findViewById(R.id.imageView);
            textTitle=itemView.findViewById(R.id.textTitle);
            textDate=itemView.findViewById(R.id.textDate);
            textAuthor=itemView.findViewById(R.id.textAuthor);
            buttonDelete =itemView.findViewById(R.id.buttonDelete);
            buttonEdit=itemView.findViewById(R.id.buttonEdit);
        }
    }

    public interface ItemClickedListener{
        void onDelete(int position);
    }

    public void remove(int position){
        this.newsList.remove(position);
        notifyItemRemoved(position);
    }

}


/***
 *
 *  - create class  adapter extends RecyclerView.Adapter
 *  - create class ViewHolder extends RecyclerView.ViewHolder
 *  -  implementation
 *     override methods
 *      - getItemCount()
 *      - onCreateViewHolder()
 *      - onBindViewHolder()
 */

