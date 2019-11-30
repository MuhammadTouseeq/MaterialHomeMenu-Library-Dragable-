package com.pakdev.homemenu;

public class HomeMenu {

    private String mMenuTitle;
    private String mMenuDesc;
    private int mMenuIcon;


    public HomeMenu setMenuTitle(String mMenuTitle) {
        this.mMenuTitle = mMenuTitle;
        return this;
    }

    public HomeMenu setMenuDesc(String mMenuDesc) {
        this.mMenuDesc = mMenuDesc;

        return this;
    }

    public HomeMenu setMenuIcon(int mMenuIcon) {
        this.mMenuIcon = mMenuIcon;
        return this;
    }

    public String getMenuTitle() {
        return mMenuTitle;
    }

    public String getMenuDesc() {
        return mMenuDesc;
    }

    public int getMenuIcon() {
        return mMenuIcon;
    }
}
