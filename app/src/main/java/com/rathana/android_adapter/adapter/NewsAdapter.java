package com.rathana.android_adapter.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rathana.android_adapter.DetailNewsActivity;
import com.rathana.android_adapter.R;
import com.rathana.android_adapter.entity.News;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<News> newsList;
    Context context;
    ItemClickedListener listener;
    boolean canLoadMore = false;


    private static final int PROGRESS_ITEM_TYPE = 0;
    private static final int NEWS_ITEM_TYPE = 1;


    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
        this.listener = (ItemClickedListener) context;
    }

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    @Override
    public int getItemCount() {
        if (canLoadMore)
            return newsList.size() + 1;
        else
            return newsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position < newsList.size())
            return NEWS_ITEM_TYPE;
        else
            return PROGRESS_ITEM_TYPE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == NEWS_ITEM_TYPE) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_news, parent, false);
            return new ViewHolder(view);
        } else if (viewType == PROGRESS_ITEM_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_load_more_item, parent, false);
            return new LoadMoreViewHolder(view);
        }

        return super.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder) {

            ViewHolder viewHolder = (ViewHolder) holder;

            News news = newsList.get(position);

            if (news.getImagePath() != null) {
                Bitmap bitmap = BitmapFactory.decodeFile(news.getImagePath());
                viewHolder.thumbnail.setImageBitmap(bitmap);
            } else {
                viewHolder.thumbnail.setImageResource(news.getThumbnail());
            }

            viewHolder.textAuthor.setText(news.getAuthor());
            viewHolder.textDate.setText(news.getDate());
            viewHolder.textTitle.setText(news.getTitle());

            holder.itemView.setOnClickListener(view -> {
                Intent intent = new Intent(context, DetailNewsActivity.class);
                intent.putExtra("news", news);
                context.startActivity(intent);
            });

            viewHolder.buttonDelete.setOnClickListener(v -> {
                if (listener != null)
                    listener.onDelete(holder.getAdapterPosition());
            });

            viewHolder.buttonEdit.setOnClickListener(v -> {
                if (listener != null)
                    listener.onUpdate(news, holder.getAdapterPosition());
            });
        } else {
            //load more viewHolder
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView thumbnail;
        TextView textTitle;
        TextView textDate;
        TextView textAuthor;
        ImageButton buttonDelete, buttonEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.imageView);
            textTitle = itemView.findViewById(R.id.textHeadline);
            textDate = itemView.findViewById(R.id.textDate);
            textAuthor = itemView.findViewById(R.id.textAuthor);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }

    class LoadMoreViewHolder extends ViewHolder {
        public LoadMoreViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface ItemClickedListener {
        void onDelete(int position);

        void onUpdate(News news, int position);
    }

    public void remove(int position) {
        this.newsList.remove(position);
        notifyItemRemoved(position);
    }

    public void update(News news, int position) {
        newsList.set(position, news);
        notifyItemChanged(position);
    }

    public void addMOreItems(List<News> news) {
        int previousItems = newsList.size();
        newsList.addAll(news);
        notifyItemRangeInserted(previousItems, newsList.size());
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

