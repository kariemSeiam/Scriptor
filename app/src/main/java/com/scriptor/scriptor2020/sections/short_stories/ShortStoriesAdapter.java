package com.scriptor.scriptor2020.sections.short_stories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptor.scriptor2020.R;

import java.util.List;

public class ShortStoriesAdapter extends RecyclerView.Adapter<ShortStoriesAdapter.ShortStoriesViewHolder> {

    Context context;
    List<ShortStoriesModel> models;
    Boolean aBoolean = true;

    public ShortStoriesAdapter(Context context, List<ShortStoriesModel> models) {
        this.context = context;
        this.models = models;
    }

    public ShortStoriesAdapter() {

    }

    @NonNull
    @Override
    public ShortStoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.short_stories_item, parent, false);
        return new ShortStoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShortStoriesViewHolder holder, int position) {
        ShortStoriesModel model = models.get(position);
        holder.tvUsername.setText(model.getUsername());
        if (model.getIs_admin())
            holder.star.setVisibility(View.VISIBLE);
        holder.tvBody.setText(model.getStory_body());
        holder.tvTitle.setText(model.getStory_title());
        holder.itemView.setOnClickListener(v -> {
            if (aBoolean) {
                holder.tvBody.setMaxLines(500);
                aBoolean = false;
            } else {
                holder.tvBody.setMaxLines(5);
                aBoolean = true;
            }
        });
        holder.tvBody.setOnClickListener(v -> {
            if (aBoolean) {
                holder.tvBody.setMaxLines(500);
                aBoolean = false;
            } else {
                holder.tvBody.setMaxLines(5);
                aBoolean = true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ShortStoriesViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody, tvUsername;
        ImageView star;

        public ShortStoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_shortS);
            tvBody = itemView.findViewById(R.id.shortS_body);
            tvUsername = itemView.findViewById(R.id.shortS_View_Username);
            star = itemView.findViewById(R.id.shortS_star);
        }
    }
}
