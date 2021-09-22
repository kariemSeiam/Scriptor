package com.scriptor.scriptor2020.sections.short_stories;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptor.scriptor2020.R;

import java.util.HashMap;
import java.util.Map;

public class CreateShortStory extends AppCompatActivity {

    EditText edtName, edtURL;
    Button btnPostNovel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> map = new HashMap<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String username, uid;
    Boolean isAdmin;

    synchronized void getExstras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            uid = extras.getString("uid");
            isAdmin = extras.getBoolean("isAdmin");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_short_story);
        edtName = findViewById(R.id.edt_shortS_Name);
        edtURL = findViewById(R.id.edt_shortS_URL);
        btnPostNovel = findViewById(R.id.btn_Create_shortS);
        getExstras();
        btnPostNovel.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String url = edtURL.getText().toString().trim();
            if (name.isEmpty() && url.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content), "You Must Add Name and Body of the Novel", Snackbar.LENGTH_SHORT).show();
            } else {
                if (isAdmin) {
                    Snackbar.make(findViewById(android.R.id.content), "Posting Short Story...", 100000).show();
                } else {
                    Snackbar.make(findViewById(android.R.id.content), "Posting Short Story...\nNotice that admins must accept your Short Story...", 100000).show();
                }

                btnPostNovel.setClickable(false);
                map.put("story_title", name);
                map.put("story_body", url);
                map.put("uid", uid);
                map.put("username", username);
                map.put("is_admin", isAdmin);
                if (isAdmin) {

                    db.collection("short_stories").add(map).addOnSuccessListener(documentReference -> {
                        finish();
                    }).addOnFailureListener(e -> {
                        btnPostNovel.setClickable(true);
                        Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
                    });
                } else {
                    db.collection("temp_short_stories").add(map).addOnSuccessListener(documentReference -> {
                        finish();
                    }).addOnFailureListener(e -> {
                        btnPostNovel.setClickable(true);
                        Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
                    });
                }

            }

        });

    }
}