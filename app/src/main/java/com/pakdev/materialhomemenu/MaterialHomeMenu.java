package com.pakdev.materialhomemenu;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pakdev.homemenu.BaseMenuActivity;
import com.pakdev.homemenu.HomeMenu;

import java.util.ArrayList;

public class MaterialHomeMenu extends BaseMenuActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //to show /hide icon
       setShowMenuIcon(true);
        //For List View
       setGridView(true);
        //For Grid View
       setGridView(true, HomeMenu.DRAWABLE_SHAPE.CIRCLE);
//       setShapeDrawable(R.drawable.shape_rounded_base);
     //   setMenuCardColor(android.R.color.holo_green_dark);
       setMenuTextColor(android.R.color.black);
       // setAnimation(HomeMenu.MENU_ANIMATION.RIGHT_TO_LEFT, 500);

        super.onCreate(savedInstanceState);

    }

    @Override
    public ArrayList<HomeMenu> getHomeMenuList() {


        ArrayList<HomeMenu> arrData = new ArrayList<>();
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.cloth_icon));
        arrData.add(new HomeMenu().setMenuTitle("About us").setMenuDesc("Description").setMenuIcon(R.drawable.cloth_icon));
        arrData.add(new HomeMenu().setMenuTitle("Sports").setMenuDesc("Description").setMenuIcon(R.drawable.cloth_icon));
        arrData.add(new HomeMenu().setMenuTitle("News").setMenuDesc("Description").setMenuIcon(R.drawable.cloth_icon));
        arrData.add(new HomeMenu().setMenuTitle("Help").setMenuDesc("Description").setMenuIcon(R.drawable.cloth_icon));
        arrData.add(new HomeMenu().setMenuTitle("Entertainments").setMenuDesc("Description").setMenuIcon(R.drawable.cloth_icon));

        return arrData;
    }

    @Override
    public void onMenuClick(HomeMenu model, int position) {

        Toast.makeText(this, "Click item is " + model.getMenuTitle(), Toast.LENGTH_SHORT).show();
    }
}
