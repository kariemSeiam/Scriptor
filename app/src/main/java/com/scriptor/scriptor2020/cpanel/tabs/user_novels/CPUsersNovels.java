package com.scriptor.scriptor2020.cpanel.tabs.user_novels;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.scriptor.scriptor2020.R;
import com.scriptor.scriptor2020.sections.novels.NovelModel;

import java.util.ArrayList;
import java.util.List;

public class CPUsersNovels extends Fragment {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<NovelModel> modelList2 = new ArrayList<>();
    private RecyclerView recyclerView2;
    private CPUsersNovelsAdapter adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c_p_users_novels, container, false);
        recyclerView2 = view.findViewById(R.id.recycler_CP_Users_Novel);
        adapterRunn();

        return view;
    }

    void adapterRunn() {
        adapter2 = new CPUsersNovelsAdapter(getContext(), modelList2);
        db.collection("temp_user_novels").get().addOnSuccessListener(queryDocumentSnapshots -> {
            modelList2.clear();
            for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                NovelModel model = document.toObject(NovelModel.class);
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