package com.scriptor.scriptor2020.sections.quotes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.scriptor.scriptor2020.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class CreateQuote extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 12;
    Button btnPost;
    EditText edtWriteQuote;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String uid, username, profile;
    Uri profilePath = null;
    Map<String, Object> map = new HashMap<>();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    ImageView imageViewQuote;
    String formattedDate;
    String path = UUID.randomUUID().toString();
    Boolean isAdmin ;

    synchronized void getExstras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            uid = extras.getString("uid");
            username = extras.getString("username");
            profile = extras.getString("profile");
            isAdmin = extras.getBoolean("isAdmin");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quote);
        btnPost = findViewById(R.id.btn_Create_Quote_Post);
        edtWriteQuote = findViewById(R.id.edt_Create_Quote);
        imageViewQuote = findViewById(R.id.pick_image_quote);
        getExstras();
        getDate();
        showStatusBarColor();
        imageViewQuote.setOnClickListener(v -> {
            openChooser();
        });
        btnPost.setOnClickListener(v -> {
            String quote = edtWriteQuote.getText().toString().trim();
            map.put("username", username);
            map.put("quote", quote);
            map.put("quote_date", formattedDate);
            map.put("isAdmin", isAdmin);
            map.put("uid", uid);
            map.put("profile", profile);

            if (quote.isEmpty() || profilePath == null) {
                Snackbar.make(findViewById(android.R.id.content), "You must write and put a photo to the quote...", Snackbar.LENGTH_LONG).show();
            } else {
                btnPost.setClickable(false);
                if (isAdmin){
                    Snackbar.make(findViewById(android.R.id.content), "Creating Quote...", 100000).show();
                }else {
                    Snackbar.make(findViewById(android.R.id.content), "Creating Quote...\nNotice that admins must accept your Quote...", 100000).show();
                }
                uploadImage(profilePath);
            }

        });


    }


    @SuppressLint("NewApi")
    private void showStatusBarColor() {
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
    }


    void isAdmin() {
        if (isAdmin) {
            db.collection("temp_quotes_admins").add(map).addOnSuccessListener(documentReference -> {
                finish();
            }).addOnFailureListener(e -> {
                btnPost.setClickable(true);
                Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
            });
        } else if (!isAdmin){
            db.collection("temp_quotes_users").add(map).addOnSuccessListener(documentReference -> finish()).addOnFailureListener(e -> {
                btnPost.setClickable(true);
                Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
            });
        }
    }

    private void uploadImage(Uri profilePat) {
        storageReference.child("QuotesImages").child(path).putFile(profilePat)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        storageReference.child("QuotesImages").child(path).getDownloadUrl()
                                .addOnCompleteListener(task1 -> {
                                    if (task.isSuccessful()) {
                                        map.put("quote_image_url", task1.getResult().toString());
                                        isAdmin();
                                    } else {
                                        btnPost.setClickable(true);
                                        Snackbar.make(findViewById(android.R.id.content), task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        btnPost.setClickable(true);
                        Snackbar.make(findViewById(android.R.id.content), task.getException().getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            profilePath = data.getData();
            Glide.with(this)
                    .load(profilePath)
                    .into(imageViewQuote);
        }
    }


    void openChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    private void getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        formattedDate = df.format(c);
    }
}