package com.scriptor.scriptor2020.cpanel.tabs.user_quotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.cpanel.tabs.feedback.FeedBackModel;
import com.scriptor.scriptor2020.sections.quotes.QuoteView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserQuotesAdapter extends RecyclerView.Adapter<UserQuotesAdapter.UserQuotesViewHolder> {


    Context context;
    List<UserQuotesModel> models;

    public UserQuotesAdapter(Context context, List<UserQuotesModel> models) {
        this.context = context;
        this.models = models;
    }

    public UserQuotesAdapter() {


    }

    @NonNull
    @Override
    public UserQuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_item, parent, false);
        return new UserQuotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserQuotesViewHolder holder, int position) {
        holder.star.setVisibility(View.GONE);
        UserQuotesModel model = models.get(position);
        holder.tvQuote.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuoteView.class);
            intent.putExtra("quote", model.getQuote());
            intent.putExtra("username", model.getUsername());
            intent.putExtra("backPhoto", model.getQuote_image_url());
            intent.putExtra("isAdmin", model.getAdmin());
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class UserQuotesViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuote, tvUsername;
        ImageView backGroundQuote, star, btnDelete, btnAccept;
        CircleImageView profile;

        public UserQuotesViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuote = itemView.findViewById(R.id.text_Quote);
            backGroundQuote = itemView.findViewById(R.id.image_Selected_Quote);
            tvUsername = itemView.findViewById(R.id.txt_Username_Quote);
            star = itemView.findViewById(R.id.started_quotes);
            btnAccept = itemView.findViewById(R.id.user_Accept_Quote);
            btnDelete = itemView.findViewById(R.id.user_Delete_Quote);
            profile = itemView.findViewById(R.id.profile_Card_Quote);
        }
    }
}
