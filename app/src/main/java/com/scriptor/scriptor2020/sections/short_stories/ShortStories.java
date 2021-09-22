package com.scriptor.scriptor2020.sections.short_stories;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.sections.novels.NovelModel;
import com.scriptor.scriptor2020.sections.novels.NovelsAdapter;

import java.util.ArrayList;
import java.util.List;


public class ShortStories extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<ShortStoriesModel> modelList2 = new ArrayList<>();
    private RecyclerView recyclerView2;
    private ShortStoriesAdapter adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_short_stories, container, false);
        recyclerView2 = view.findViewById(R.id.recycler_Short_Stories);
        adapterRunn();
        return view;
    }

    void adapterRunn() {
        adapter2 = new ShortStoriesAdapter(getContext(), modelList2);
        db.collection("short_stories").get().addOnSuccessListener(queryDocumentSnapshots -> {
            modelList2.clear();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                ShortStoriesModel model = document.toObject(ShortStoriesModel.class);
                modelList2.add(model);
            }
            recyclerView2.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView2.setHasFixedSize(true);
            recyclerView2.setAdapter(adapter2);
            adapter2.notifyDataSetChanged();

        });


    }


    @Override
    public void onStart() {
        super.onStart();
        adapter2.notifyDataSetChanged();

    }

}