package com.scriptor.scriptor2020.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
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

public class SignUp extends AppCompatActivity {
    private final int PICK_IMAGE_REQUEST = 143;
    Uri profilePath = null;
    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    EditText edtName1, edtName2, edtMail, edtPass, edtRePass;
    String formattedDate;
    Button btnSignUp;
    ImageView profilee;
    String uid;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    Map<String, Object> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        showStatusBarColor();
        findViewByIds();
        getDate();
        profilee.setOnClickListener(v -> {
            openChooser();
        });
        btnSignUp.setOnClickListener(v -> {
            String name1 = edtName1.getText().toString().trim();
            String name2 = edtName2.getText().toString().trim();
            String mail = edtMail.getText().toString().trim();
            String pass = edtPass.getText().toString().trim();
            String rePass = edtRePass.getText().toString().trim();
            if (name1.isEmpty() || name2.isEmpty() || mail.isEmpty() || pass.isEmpty() || rePass.isEmpty() || profilePath == null) {
                Snackbar.make(findViewById(android.R.id.content), "You Must Fill all of them.", Snackbar.LENGTH_SHORT).show();
            } else if (!pass.equals(rePass)) {
                Snackbar.make(findViewById(android.R.id.content), "Password not match.", Snackbar.LENGTH_SHORT).show();
            } else if (pass.length() < 8) {
                Snackbar.make(findViewById(android.R.id.content), "Password must be at least 8 or more letters or numbers.", Snackbar.LENGTH_SHORT).show();
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Creating your account...", 10000).show();
                btnSignUp.setClickable(false);
                map.put("username", name1 + " " + name2);
                map.put("mail", mail);
                map.put("password", pass);
                map.put("isAdmin", false);
                map.put("isStarted", false);
                map.put("validation", true);
                map.put("date_created", formattedDate);
                auth.createUserWithEmailAndPassword(mail, pass).addOnSuccessListener(authResult -> {
                    uid = auth.getCurrentUser().getUid();
                    map.put("uid", uid);
                    uploadProfileImage(profilePath);
                }).addOnFailureListener(e -> {
                    btnSignUp.setClickable(true);
                    Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_INDEFINITE).show();
                });
            }
        });

    }

    private void getDate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        // formattedDate is String...
        formattedDate = df.format(c);
    }

    private void findViewByIds() {
        edtName1 = findViewById(R.id.edt_First_Name);
        edtName2 = findViewById(R.id.edt_Second_Name);
        edtMail = findViewById(R.id.edt_Mail_SignUp);
        edtPass = findViewById(R.id.edt_Pass_SignUp);
        edtRePass = findViewById(R.id.edt_RePass_SignUp);
        btnSignUp = findViewById(R.id.btn_SignUp);
        profilee = findViewById(R.id.pick_profile);
    }

    @SuppressLint("NewApi")
    private void showStatusBarColor() {
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));
    }

    private void uploadProfileImage(Uri profilePat) {
        storageReference.child("ProfileImages").child(uid).putFile(profilePat)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        storageReference.child("ProfileImages").child(auth.getUid()).getDownloadUrl()
                                .addOnCompleteListener(task1 -> {
                                    if (task.isSuccessful()) {
                                        map.put("profile_url", task1.getResult().toString());
                                        firestore.collection("users").document(uid).set(map).addOnSuccessListener(aVoid -> {
                                            finish();
                                        }).addOnFailureListener(e -> {
                                            btnSignUp.setClickable(true);
                                            Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_LONG).show();
                                        });
                                    } else {
                                        btnSignUp.setClickable(true);
                                        Snackbar.make(findViewById(android.R.id.content), String.valueOf(task.getResult()), Snackbar.LENGTH_LONG).show();
                                    }
                                });
                    } else {
                        btnSignUp.setClickable(true);
                        String errorMessage = task.getException().getMessage();
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(taskSnapshot -> {

        });
    }

    void openChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            profilePath = data.getData();
            Glide.with(this)
                    .load(profilePath)
                    .into(profilee);
        }
    }

}