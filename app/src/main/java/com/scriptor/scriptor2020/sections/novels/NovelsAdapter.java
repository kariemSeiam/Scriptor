package com.scriptor.scriptor2020.sections.novels;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptor.scriptor2020.R;

import java.util.List;

public class NovelsAdapter extends RecyclerView.Adapter<NovelsAdapter.NovelViewHolder> {

    Context context;
    List<NovelModel> models;

    public NovelsAdapter(Context context, List<NovelModel> models) {
        this.context = context;
        this.models = models;
    }

    public NovelsAdapter() {

    }

    @NonNull
    @Override
    public NovelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.novel_item, parent, false);
        return new NovelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NovelViewHolder holder, int position) {
        NovelModel model = models.get(position);
        holder.tvNovelName.setText(model.getNovel_name());
        if (!model.getIs_admin()) {
            holder.star.setVisibility(View.GONE);
        }
        holder.tvUsername.setText(model.getUsername());
        holder.btnUrl.setOnClickListener(v -> {
            Uri uri = Uri.parse(model.getNovel_url());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class NovelViewHolder extends RecyclerView.ViewHolder {
        ImageView btnUrl, star;
        TextView tvNovelName, tvUsername;

        public NovelViewHolder(@NonNull View itemView) {
            super(itemView);
            btnUrl = itemView.findViewById(R.id.item_Novel_URL);
            tvNovelName = itemView.findViewById(R.id.item_Novel_Name);
            star = itemView.findViewById(R.id.novel_star);
            tvUsername = itemView.findViewById(R.id.novel_View_Username);

        }
    }
}
