package com.pakdev.homemenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;

public abstract class BaseMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private boolean showMenuIcon;
    private boolean isGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        final HomeMenuAdapter adapter=new HomeMenuAdapter(getApplicationContext(),getHomeMenuList());
        recyclerView=findViewById(R.id.recycleView);


        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);

        if(isGridView)
        {
            bindGridLayoutManager(adapter);
        }
        else {

            bindLinearLayoutManager();
            adapter.setItemSize(0);
        }


        recyclerView.setAdapter(adapter);
        adapter.setMenuIcon(showMenuIcon);

        adapter.setClickListener(new HomeMenuAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                HomeMenu model = adapter.getMenumAt(position);

                onMenuClick(model,position);
            }
        });



    }

    public void setGridView(boolean gridView) {
        isGridView = gridView;
    }

    private void bindLinearLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void bindGridLayoutManager(HomeMenuAdapter adapter) {
        Display display = BaseMenuActivity.this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int  y = size.y;
        y=(int)y/4;
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setItemSize(y);
    }

    protected void setShowMenuIcon(boolean showMenuIcon) {
        this.showMenuIcon = showMenuIcon;
    }

    public abstract ArrayList<HomeMenu> getHomeMenuList();
    public abstract void onMenuClick(HomeMenu model, int position);



}
