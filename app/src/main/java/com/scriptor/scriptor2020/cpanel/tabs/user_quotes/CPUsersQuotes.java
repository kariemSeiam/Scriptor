package com.scriptor.scriptor2020.cpanel.tabs.user_quotes;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scriptor.scriptor2020.R;


public class CPUsersQuotes extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c_p_users_quotes, container, false);

        return view;
    }
}