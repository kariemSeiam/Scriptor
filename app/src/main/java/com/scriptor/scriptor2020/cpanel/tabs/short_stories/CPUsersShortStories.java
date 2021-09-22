package com.scriptor.scriptor2020.cpanel.tabs.short_stories;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.scriptor.scriptor2020.R;

public class CPUsersShortStories extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c_p_users_short_stories, container, false);
    }
}