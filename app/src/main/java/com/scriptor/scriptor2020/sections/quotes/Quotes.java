package com.scriptor.scriptor2020.sections.quotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.cpanel.tabs.feedback.FeedAdapter;
import com.scriptor.scriptor2020.cpanel.tabs.feedback.FeedBackModel;

import java.util.ArrayList;
import java.util.List;


public class Quotes extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private final List<QuotesModel> modelList = new ArrayList<>();
    private QuotesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quotes, container, false);
        recyclerView = view.findViewById(R.id.recycler_Quotes);
        adapterRun();
        return view;
    }

    private void adapterRun() {
        adapter = new QuotesAdapter(getContext(), modelList);

//        adapter = new FeedAdapter(getContext(), modelList);
//        db.collection("users_feedback").get().addOnSuccessListener(queryDocumentSnapshots -> {
//            modelList.clear();
//            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                FeedBackModel model = document.toObject(FeedBackModel.class);
//                modelList.add(model);
//            }
//            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//            recyclerView.setHasFixedSize(true);
//            recyclerView.setAdapter(adapter);
//            adapter.notifyDataSetChanged();
//
//        });


        database.getReference("main_posts").child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                modelList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    QuotesModel model = snapshot.getValue(QuotesModel.class);
                    modelList.add(model);
                }
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                manager.setSmoothScrollbarEnabled(true);
                recyclerView.setLayoutManager(manager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.notifyDataSetChanged();

    }
}