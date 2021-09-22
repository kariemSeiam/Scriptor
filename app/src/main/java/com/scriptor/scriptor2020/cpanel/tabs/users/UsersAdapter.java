package com.scriptor.scriptor2020.cpanel.tabs.users;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.user.UserModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    Context context;
    List<UserModel> models;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public UsersAdapter(Context context, List<UserModel> models) {
        this.context = context;
        this.models = models;
    }

    public UsersAdapter() {

    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UserModel model = models.get(position);
        holder.tvUserName.setText(model.getUsername());
        holder.tvDate.setText(model.getDate_created());
        holder.tvMail.setText(model.getMail());
        Glide.with(holder.itemView.getContext())
                .load(model.getProfile_url())
                .placeholder(R.drawable.ic_user)
                .centerCrop()
                .into(holder.circleImageView);
        holder.btnDelete.setOnClickListener(v -> {
                db.collection("users").document(model.getUid()).update("validation", false)
                        .addOnSuccessListener(aVoid -> Snackbar.make(v, "Account deactivated...", BaseTransientBottomBar.LENGTH_SHORT).show())
                        .addOnFailureListener(e -> Snackbar.make(v, String.valueOf(e), BaseTransientBottomBar.LENGTH_SHORT).show());
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class UsersViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView;
        TextView tvDate, tvUserName, tvMail;
        ImageView btnDelete;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.users_ImageView);
            tvDate = itemView.findViewById(R.id.tv_Date_Users);
            tvMail = itemView.findViewById(R.id.tv_Mail_User);
            tvUserName = itemView.findViewById(R.id.users_Username);
            btnDelete = itemView.findViewById(R.id.btn_Delete_Acc);

        }
    }
}
