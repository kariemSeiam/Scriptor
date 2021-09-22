package com.scriptor.scriptor2020.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.scriptor.scriptor2020.R;

public class Login extends AppCompatActivity {
    TextView tvSignUp, tvRestPass;
    EditText edtMail, edtPass;
    Button btnLogin;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();
        showStatusBarColor();
        findViewByIds();
        setOnClickListeners();
    }

    private void findViewByIds() {
        tvSignUp = findViewById(R.id.tv_SignUp);
        tvRestPass = findViewById(R.id.tv_RestPass_Login);
        edtMail = findViewById(R.id.edt_Mail_Login);
        edtPass = findViewById(R.id.edt_Pass_Login);
        btnLogin = findViewById(R.id.btn_Login);
    }

    @SuppressLint("NewApi")
    private void showStatusBarColor() {
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccent));

    }

    private void setOnClickListeners() {
        tvSignUp.setOnClickListener(v -> {
            startActivity(new Intent(this, SignUp.class));
            if (auth.getCurrentUser() != null) {
                finish();
            }
        });
        tvRestPass.setOnClickListener(v -> {
            startActivity(new Intent(this, RestPassword.class));
        });
        btnLogin.setOnClickListener(v -> {
            Snackbar.make(findViewById(android.R.id.content), "We are signing you in...", Snackbar.LENGTH_SHORT).show();
            String mail = edtMail.getText().toString().trim();
            String pass = edtPass.getText().toString().trim();
            if (mail.isEmpty() || pass.isEmpty()) {
                Snackbar.make(findViewById(android.R.id.content), "You Must Fill all of them.", Snackbar.LENGTH_SHORT).show();
            } else {
                btnLogin.setClickable(false);
                auth.signInWithEmailAndPassword(mail, pass).addOnSuccessListener(authResult -> {
                    finish();
                }).addOnFailureListener(e -> {
                    btnLogin.setClickable(true);
                    Snackbar.make(findViewById(android.R.id.content), String.valueOf(e), Snackbar.LENGTH_SHORT).show();
                });
            }
        });
    }
}