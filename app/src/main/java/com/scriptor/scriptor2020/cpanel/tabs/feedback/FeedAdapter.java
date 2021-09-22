package com.scriptor.scriptor2020.cpanel.tabs.feedback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.scriptor.scriptor2020.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    Context context;
    List<FeedBackModel> models;

    public FeedAdapter() {

    }

    public FeedAdapter(Context context, List<FeedBackModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedback_item, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        FeedBackModel model = models.get(position);
        holder.tvUsername.setText(model.getUsername());
        holder.tvDate.setText(model.getDate_created());
        holder.tvBody.setText(model.getFeedback());
        holder.tvUsername.setText(model.getUsername());
        Glide.with(holder.itemView.getContext())
                .load(model.getProfile_uri())
                .placeholder(R.drawable.ic_user)
                .centerCrop()
                .into(holder.cImageViewFeed);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvBody, tvUsername;
        CircleImageView cImageViewFeed;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.item_Feed_User_Name);
            tvBody = itemView.findViewById(R.id.item_Feed_Body);
            tvDate = itemView.findViewById(R.id.item_Feed_Date);
            cImageViewFeed = itemView.findViewById(R.id.item_Feed_Profile);
        }
    }
}
