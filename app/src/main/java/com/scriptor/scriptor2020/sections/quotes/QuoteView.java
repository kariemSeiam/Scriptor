package com.scriptor.scriptor2020.sections.quotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.scriptor.scriptor2020.R;

public class QuoteView extends AppCompatActivity {

    TextView quoteViewText , quoteViewUsername;
    String imageURL , quote , username ;
    Boolean isAdmin ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_view);
        getExstras();
        quoteViewText.setText(quote);
        quoteViewUsername.setText(username);
        if (!isAdmin){

        }

    }

    synchronized void getExstras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            quote = extras.getString("quote");
            username = extras.getString("username");
            imageURL = extras.getString("backPhoto");
            isAdmin = extras.getBoolean("isAdmin");
        }
    }


}