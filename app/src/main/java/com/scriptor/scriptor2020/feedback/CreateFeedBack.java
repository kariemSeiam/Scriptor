package com.scriptor.scriptor2020.feedback;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.user.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateFeedBack extends AppCompatActivity {

    EditText edtFeed;
    Button btnFeed;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> map = new HashMap<>();
    FirebaseAuth auth;
    String formattedDate;
    Date c;
    String feed;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_feed_back);
        auth = FirebaseAuth.getInstance();
        getDate();
        edtFeed = findViewById(R.id.edt_Create_FeedBack);
        btnFeed = findViewById(R.id.btn_Create_FeedBack_Post);
        btnFeed.setOnClickListener(v -> {
            uid = auth.getUid();
            feed = edtFeed.getText().toString().trim();
            if (feed.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content), "You can not send empty feedback...", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Sending your feedback...", Snackbar.LENGTH_LONG).show();
                db.collection("users").document(uid).get().addOnSuccessListener(documentSnapshot -> {
                    UserModel user = documentSnapshot.toObject(UserModel.class);
                    map.put("username", user.getUsername());
                    map.put("uid", user.getUid());
                    map.put("date_created", formattedDate);
                    map.put("profile_uri", user.getProfile_url());
                    map.put("feedback", feed);
                    db.collection("users_feedback").add(map).addOnSuccessListener(documentReference -> {
                        finish();
                    }).addOnFailureListener(e -> Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_LONG).show());
                }).addOnFailureListener(e -> {
                    Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_LONG).show();
                });
            }
        });
    }


    void getDate() {
        c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        formattedDate = df.format(c);
    }
}