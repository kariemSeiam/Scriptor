package com.scriptor.scriptor2020.cpanel.tabs.user_novels;

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
import com.scriptor.scriptor2020.sections.novels.NovelModel;

import java.util.List;

public class CPUsersNovelsAdapter extends RecyclerView.Adapter<CPUsersNovelsAdapter.CPUsersNovelsViewHolder> {

    Context context;
    List<NovelModel> models;

    public CPUsersNovelsAdapter(Context context, List<NovelModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public CPUsersNovelsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.novel_item, parent, false);
        return new CPUsersNovelsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CPUsersNovelsViewHolder holder, int position) {
        holder.btnAccept.setVisibility(View.VISIBLE);
        holder.btnDelete.setVisibility(View.VISIBLE);
        holder.star.setVisibility(View.GONE);
        NovelModel model = models.get(position);
        holder.tvNovelName.setText(model.getNovel_name());
        holder.tvUsername.setText(model.getUsername());
        holder.btnUrl.setOnClickListener(v -> {
            Uri uri = Uri.parse(model.getNovel_url());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            v.getContext().startActivity(intent);
        });
        holder.btnAccept.setOnClickListener(v -> {

        });
        holder.btnDelete.setOnClickListener(v -> {

        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class CPUsersNovelsViewHolder extends RecyclerView.ViewHolder {
        ImageView btnUrl, star , btnDelete , btnAccept;
        TextView tvNovelName, tvUsername;

        public CPUsersNovelsViewHolder(@NonNull View itemView) {
            super(itemView);
            btnUrl = itemView.findViewById(R.id.item_Novel_URL);
            tvNovelName = itemView.findViewById(R.id.item_Novel_Name);
            star = itemView.findViewById(R.id.novel_star);
            tvUsername = itemView.findViewById(R.id.novel_View_Username);
            btnDelete = itemView.findViewById(R.id.btn_Novel_Delete);
            btnAccept = itemView.findViewById(R.id.btn_Novel_Accept);

        }
    }
}
