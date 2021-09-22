package com.scriptor.scriptor2020;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.scriptor.scriptor2020.auth.Login;
import com.scriptor.scriptor2020.cpanel.CPanel;
import com.scriptor.scriptor2020.feedback.CreateFeedBack;
import com.scriptor.scriptor2020.sections.novels.CreateNovel;
import com.scriptor.scriptor2020.sections.quotes.CreateQuote;
import com.scriptor.scriptor2020.sections.short_stories.CreateShortStory;
import com.scriptor.scriptor2020.setting.Setting;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    ViewPager viewPager;
    TabLayout tabLayout;
    ConstraintLayout layout;
    ExtendedFloatingActionButton btnQuote, btnShortStories, btnNovel;
    ImageView btnFeedBack;
    CircleImageView btnProfile;
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    LinearLayout btn_CPanel;
    Boolean validation, isAdmin;
    String url, username, uid;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showStatusBarColor();
        findViewByIds();
        getAccData();
        setUpTabLayout();
        setOnClickListeners();
    }

    private void getAccData() {
        uid = auth.getUid();
        if (uid == null) {
            auth.signOut();
            if (auth.getCurrentUser() == null) {
                startActivity(new Intent(this, Login.class));
            }
        }
        db.collection("users")
                .document(uid)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        validation = task.getResult().getBoolean("validation");
                        isAdmin = task.getResult().getBoolean("isAdmin");
                        url = task.getResult().getString("profile_url");
                        username = task.getResult().getString("username");

                        if (!validation) {
                            auth.signOut();
                            if (auth.getCurrentUser() == null) {
                                startActivity(new Intent(this, Login.class));
                            }
                        } else {
                            Glide.with(this)
                                    .load(url)
                                    .placeholder(R.drawable.ic_user)
                                    .centerCrop()
                                    .into(btnProfile);
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
    }


    void setOnClickListeners() {
        btnQuote.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateQuote.class);
            intent.putExtra("username", username);
            intent.putExtra("profile", url);
            intent.putExtra("isAdmin", isAdmin);
            intent.putExtra("uid", uid);
            startActivity(intent);


        });
        btnFeedBack.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, CreateFeedBack.class));
        });
        btnProfile.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Setting.class));
        });
        btnNovel.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateNovel.class);
            intent.putExtra("username", username);
            intent.putExtra("isAdmin", isAdmin);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });

        btn_CPanel.setOnClickListener(v -> {
            if (isAdmin)
                startActivity(new Intent(MainActivity.this, CPanel.class));
            Snackbar.make(findViewById(android.R.id.content), "Welcome " + username, Snackbar.LENGTH_LONG).show();

        });
        btnShortStories.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateShortStory.class);
            intent.putExtra("username", username);
            intent.putExtra("isAdmin", isAdmin);
            intent.putExtra("uid", uid);
            startActivity(intent);
        });


    }

    void findViewByIds() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.viewpager);
        btnQuote = findViewById(R.id.fab_Btn_Quotes);
        btnShortStories = findViewById(R.id.fab_Btn_Short_Stories);
        btnNovel = findViewById(R.id.fab_Btn_Novels);
        btnFeedBack = findViewById(R.id.image_Btn_FeedBack);
        btnProfile = findViewById(R.id.btn_Profile);
        btn_CPanel = findViewById(R.id.logo);
        layout = findViewById(R.id.mainBar);
        progressBar = findViewById(R.id.progress_circular_main);
    }

    @SuppressLint("ResourceAsColor")
    void setUpTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText("Quotes"));
        tabLayout.addTab(tabLayout.newTab().setText("Writing Basics"));
        tabLayout.addTab(tabLayout.newTab().setText("Novels"));
        tabLayout.addTab(tabLayout.newTab().setText("Short Stories"));
        tabLayout.addTab(tabLayout.newTab().setText("Poems"));
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setBackgroundColor(Color.BLACK);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0:
                btnQuote.setVisibility(View.VISIBLE);
                btnNovel.setVisibility(View.GONE);
                btnShortStories.setVisibility(View.GONE);
                break;
            case 1:
                btnQuote.setVisibility(View.GONE);
                btnNovel.setVisibility(View.GONE);
                btnShortStories.setVisibility(View.GONE);
                break;
            case 2:
                btnQuote.setVisibility(View.GONE);
                btnNovel.setVisibility(View.VISIBLE);
                btnShortStories.setVisibility(View.GONE);
                break;
            case 3:
                btnQuote.setVisibility(View.GONE);
                btnNovel.setVisibility(View.GONE);
                btnShortStories.setVisibility(View.VISIBLE);
                break;

        }
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @SuppressLint("NewApi")
    void showStatusBarColor() {
        Window window = this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        if (!validation)
            auth.signOut();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(new Intent(this, Login.class)));
        }
    }
}