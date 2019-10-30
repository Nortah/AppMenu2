package com.example.appmenu.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.appmenu.R;
import com.example.appmenu.adapter.RecyclerAdapter;
import com.example.appmenu.database.AppDatabase;
import com.example.appmenu.database.entity.TypeEntity;
import com.example.appmenu.util.RecyclerViewItemClickListener;
import com.example.appmenu.viewModel.TypeListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TypesActivity extends BaseActivity {

    private static final String TAG = "TypesActivity";

    private List<TypeEntity> types;
    private RecyclerAdapter<TypeEntity> adapter = null;
    private TypeListViewModel viewModel;

    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
            AppDatabase.class, "Menu").build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_types, frameLayout);


        RecyclerView recyclerView = findViewById(R.id.typesRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        //mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        SharedPreferences settings = getSharedPreferences(BaseActivity.PREFS_NAME, 0);
        String user = settings.getString(BaseActivity.PREFS_USER, null);

        types = new ArrayList<>();
        adapter = new RecyclerAdapter<>(new RecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d(TAG, "clicked position:" + position);
                Log.d(TAG, "clicked on: " + types.get(position).getName());


            }

            @Override
            public void onItemLongClick(View v, int position) {
                Log.d(TAG, "longClicked position:" + position);
                Log.d(TAG, "longClicked on: " + types.get(position).getName());

            }
        });



        TypeListViewModel.Factory factory = new TypeListViewModel.Factory(getApplication());
        viewModel = ViewModelProviders.of(this, factory).get(TypeListViewModel.class);
        viewModel.getTypes().observe(this, typeEntities -> {
            if (typeEntities != null) {
                types = typeEntities;
                adapter.setData(types);
            }
        });

        recyclerView.setAdapter(adapter);
    }




}
