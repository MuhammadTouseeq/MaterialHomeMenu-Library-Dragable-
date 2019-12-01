package com.pakdev.homemenu;

public class HomeMenu {

    public enum DRAWABLE_SHAPE{
        CIRCLE,ROUNDED,DEFAULT
    }

    public enum MENU_ANIMATION{
        FADE_IN,LEFT_TO_RIGHT,RIGHT_TO_LEFT
    }

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
