package com.pakdev.materialhomemenu;

import android.os.Bundle;
import android.widget.Toast;

import com.pakdev.homemenu.BaseMenuActivity;
import com.pakdev.homemenu.HomeMenu;

import java.util.ArrayList;

public class MaterialHomeMenu extends BaseMenuActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setShowMenuIcon(true);
        setGridView(true);
        super.onCreate(savedInstanceState);


    }


    @Override
    public ArrayList<HomeMenu> getHomeMenuList() {



        ArrayList<HomeMenu> arrData=new ArrayList<>();
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.ic_home));
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.ic_home));
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.ic_home));
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.ic_home));
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.ic_home));
        arrData.add(new HomeMenu().setMenuTitle("Home").setMenuDesc("Description").setMenuIcon(R.drawable.ic_home));

        return arrData;
    }

    @Override
    public void onMenuClick(HomeMenu model, int position) {

        Toast.makeText(this, "Click item is "+model.getMenuTitle(), Toast.LENGTH_SHORT).show();
    }
}
