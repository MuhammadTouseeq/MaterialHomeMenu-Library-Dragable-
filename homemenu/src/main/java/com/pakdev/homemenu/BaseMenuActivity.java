package com.pakdev.homemenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public abstract class BaseMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private boolean showMenuIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final HomeMenuAdapter adapter=new HomeMenuAdapter(getApplicationContext(),getHomeMenuList());
        recyclerView=findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
        adapter.setMenuIcon(true);

        adapter.setClickListener(new HomeMenuAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                HomeMenu model = adapter.getMenumAt(position);

                onMenuClick(model,position);
            }
        });



    }

    protected void setShowMenuIcon(boolean showMenuIcon) {
        this.showMenuIcon = showMenuIcon;
    }

    public abstract ArrayList<HomeMenu> getHomeMenuList();
    public abstract void onMenuClick(HomeMenu model, int position);


}
