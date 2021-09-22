package com.scriptor.scriptor2020.cpanel.tabs.feedback;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.scriptor.scriptor2020.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CPUsersFeedBacks extends Fragment {

    private final List<FeedBackModel> modelList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private FeedAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_p_users_feed_backs, container, false);
        recyclerView = view.findViewById(R.id.recycler_FeedBaks);
        adapterRunn();
        return view;
    }

    private void adapterRunn() {
        adapter = new FeedAdapter(getContext(), modelList);
        db.collection("users_feedback").get().addOnSuccessListener(queryDocumentSnapshots -> {
            modelList.clear();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                FeedBackModel model = document.toObject(FeedBackModel.class);
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