package com.scriptor.scriptor2020.sections.novels;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptor.scriptor2020.R;

import java.util.HashMap;
import java.util.Map;

public class CreateNovel extends AppCompatActivity {

    EditText edtName , edtURL ;
    Button btnPostNovel;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String,Object> map = new HashMap<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("novels");
    String username ,uid;
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
        setContentView(R.layout.activity_create_novel);
        edtName = findViewById(R.id.edt_Novel_Name);
        edtURL = findViewById(R.id.edt_Novel_URL);
        btnPostNovel = findViewById(R.id.btn_Create_Novel);
        getExstras();
        btnPostNovel.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String url = edtURL.getText().toString().trim();
            if (name.isEmpty() && url.isEmpty() ){
                Snackbar.make(findViewById(android.R.id.content), "You Must Add Name and URL of the Novel", Snackbar.LENGTH_SHORT).show();
            }else {
                if (isAdmin){
                    Snackbar.make(findViewById(android.R.id.content), "Adding Novel...", 100000).show();
                }else {
                    Snackbar.make(findViewById(android.R.id.content), "Adding Novel...\nNotice that admins must accept your novel...", 100000).show();
                }

                btnPostNovel.setClickable(false);
                map.put("novel_name",name);
                map.put("novel_url",url);
                map.put("uid",uid);
                map.put("username", username);
                map.put("is_admin", isAdmin);
                if (isAdmin) {

                    db.collection("novels").add(map).addOnSuccessListener(documentReference -> {
                        finish();
                    }).addOnFailureListener(e -> {
                        btnPostNovel.setClickable(true);
                        Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
                    });
                }else {
                    db.collection("temp_user_novels").add(map).addOnSuccessListener(documentReference -> {
                        finish();
                    }).addOnFailureListener(e -> {
                        btnPostNovel.setClickable(true);
                        Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
                    });
                }

//                myRef.child("main_novels").push().setValue(map).addOnCompleteListener(task -> {
//                    if (task.isSuccessful()){
//                        Snackbar.make(findViewById(android.R.id.content), "Added Successfully...", Snackbar.LENGTH_SHORT).show();
//                        finish();
//                    }else {
//                        btnPostNovel.setClickable(true);
//                        Snackbar.make(findViewById(android.R.id.content), "Check Your Internet...", Snackbar.LENGTH_SHORT).show();
//                    }
//                });
            }

        });

    }
}