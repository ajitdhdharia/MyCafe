package com.example.mycafe.ui.Desserts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycafe.Db_frall;
import com.example.mycafe.R;
import com.example.mycafe.ShowItemsAdapter;
import com.example.mycafe.faltu_context;

public class DessertFragment extends Fragment {
    private Db_frall databaseHandler;
    private RecyclerView recyclerView;
    private ShowItemsAdapter rvAdapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.desserts_layout, container, false);
        recyclerView= view.findViewById(R.id.recycler);
        databaseHandler = new Db_frall(faltu_context.context);
        //  rating=view.findViewById(R.id.rating);
        rvAdapter =new ShowItemsAdapter(databaseHandler.getAllImagesData("Desserts","Active") );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(faltu_context.context));
        recyclerView.setAdapter(rvAdapter);
        return view;
    }
}

