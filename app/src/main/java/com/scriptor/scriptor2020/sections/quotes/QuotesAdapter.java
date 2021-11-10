package com.scriptor.scriptor2020.sections.quotes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptor.scriptor2020.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.RecyclerViewHolder> {

    Context context;
    List<QuotesModel> models;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public QuotesAdapter(Context context, List<QuotesModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_item, parent, false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        QuotesModel model = models.get(position);
        holder.tvQuote.setText(model.getPost());
        holder.tvUsername.setText(model.getUsername());
        holder.btnAccept.setVisibility(View.GONE);
        holder.btnDelete.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, QuoteView.class);
            intent.putExtra("text", holder.tvQuote.getText().toString());
            intent.putExtra("username", holder.tvUsername.getText().toString());
            intent.putExtra("is_admin", false);
            intent.putExtra("quote", holder.tvQuote.getText().toString() );
            v.getContext().startActivity(intent);

        });

//        Glide.with(holder.itemView.getContext())
//                .load(model.getQuote_image_url())
//                .placeholder(R.drawable.testayah)
//                .centerCrop()
//                .into(holder.imageView2);
//
//        Glide.with(holder.itemView.getContext())
//                .load(model.getcircleImageView())
//                .placeholder(R.drawable.ic_user)
//                .centerCrop()
//                .into(holder.circleImageView);
//
//        holder.itemView.setOnClickListener(v -> {
//            Intent intent = new Intent(context, QuoteView.class);
//            intent.putExtra("text", holder.tvQuote.getText().toString());
//            intent.putExtra("username", holder.vi.getText().toString());
//            v.getContext().startActivity(intent);
//
//        });
//        holder.tvQuote.setOnClickListener(v -> {
//            Intent intent = new Intent(context, QuoteView.class);
//            intent.putExtra("text", holder.tvQuote.getText().toString());
//            intent.putExtra("username", holder.vi.getText().toString());
//            v.getContext().startActivity(intent);
//        });
//        if (model.getAdmin()) {
//            holder.star.setVisibility(View.VISIBLE);
//        }

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuote, tvUsername;
        ImageView backGroundQuote, star, btnDelete, btnAccept;
        CircleImageView circleImageView;


        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            tvQuote = itemView.findViewById(R.id.text_Quote);
            backGroundQuote = itemView.findViewById(R.id.image_Selected_Quote);
            tvUsername = itemView.findViewById(R.id.txt_Username_Quote);
            star = itemView.findViewById(R.id.started_quotes);
            btnAccept = itemView.findViewById(R.id.user_Accept_Quote);
            btnDelete = itemView.findViewById(R.id.user_Delete_Quote);
            circleImageView = itemView.findViewById(R.id.profile_Card_Quote);


        }
    }

}
