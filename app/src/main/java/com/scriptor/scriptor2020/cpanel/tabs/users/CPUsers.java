package com.scriptor.scriptor2020.cpanel.tabs.users;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.cpanel.tabs.feedback.FeedAdapter;
import com.scriptor.scriptor2020.cpanel.tabs.feedback.FeedBackModel;
import com.scriptor.scriptor2020.user.UserModel;

import java.util.ArrayList;
import java.util.List;

public class CPUsers extends Fragment {


    private final List<UserModel> modelList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private UsersAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_p_users, container, false);
        recyclerView = view.findViewById(R.id.recycler_Users);
        adapterRunn();
        return view;
    }

    private void adapterRunn() {
        adapter = new UsersAdapter(getContext(), modelList);
        db.collection("users").get().addOnSuccessListener(queryDocumentSnapshots -> {
            modelList.clear();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                UserModel model = document.toObject(UserModel.class);
                modelList.add(model);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        });


    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

}