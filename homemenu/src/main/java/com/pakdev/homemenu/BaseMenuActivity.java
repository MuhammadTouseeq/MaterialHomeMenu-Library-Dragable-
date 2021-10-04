package com.pakdev.homemenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;

public abstract class BaseMenuActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private boolean showMenuIcon;
    private int mShapeDrawable = -1;
    private boolean isGridView;
    private long ANIMATION_DURATION;
    private HomeMenu.DRAWABLE_SHAPE MenuShape;
    private HomeMenu.MENU_ANIMATION MenuAnimation;

private int menuTextColor=android.R.color.black;
private int menuCardColor=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);


        final HomeMenuAdapter adapter = new HomeMenuAdapter(getApplicationContext(), getHomeMenuList());
        recyclerView = findViewById(R.id.recycleView);

//attach the recycle view for drag and drop
        ItemTouchHelper.Callback callback =
                new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerView);
//binding the layout managers to recycleview

        if (isGridView) {
            bindGridLayoutManager(adapter);
        } else {

            bindLinearLayoutManager();

        }
        recyclerView.setAdapter(adapter);


        //setting the home menu binding
        adapter.setMenuIcon(showMenuIcon);
        adapter.setGridMenu(isGridView, MenuShape);
        adapter.setShapeDrawable(mShapeDrawable);
        adapter.setAnimationType(MenuAnimation, ANIMATION_DURATION);
        adapter.setMenuCardColor(menuCardColor);

        adapter.setTextColor(menuTextColor);
        adapter.setClickListener(new HomeMenuAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                HomeMenu model = adapter.getMenumAt(position);

                onMenuClick(model, position);
            }
        });


    }

    //set gridview and its shape
    protected void setGridView(boolean isGridMenu, HomeMenu.DRAWABLE_SHAPE menuShape) {

        isGridView = isGridMenu;
        MenuShape = menuShape;
    }

    protected void setShapeDrawable(int shapeDrawable) {
        mShapeDrawable = shapeDrawable;
    }

    protected void setMenuTextColor(int menuTextColor) {
        this.menuTextColor = menuTextColor;
    }

    protected void setMenuCardColor(int menuCardColor) {
        this.menuCardColor = menuCardColor;
    }

    //set grid view with default shape
    protected void setGridView(boolean gridView) {
        isGridView = gridView;
        MenuShape = HomeMenu.DRAWABLE_SHAPE.DEFAULT;
    }

    protected void setAnimation(HomeMenu.MENU_ANIMATION animation, long Duration) {
        MenuAnimation = animation;
        ANIMATION_DURATION = Duration;
    }


    private void bindLinearLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    private void bindGridLayoutManager(HomeMenuAdapter adapter) {
        Display display = BaseMenuActivity.this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int y = size.y;
        y = (int) y / 4;
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
