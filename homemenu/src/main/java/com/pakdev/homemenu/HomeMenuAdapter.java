package com.pakdev.homemenu;

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Collections;


public class HomeMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter {


    private final LayoutInflater mInflater;
    private Context context;
    private ArrayList<HomeMenu> arrMenus;
    public static ItemClickListener mClickListener;
    public boolean isMenuIcon;
    public boolean isGridMenu;
    public int itemSize;


    // data is passed into the constructor
    HomeMenuAdapter(Context context, ArrayList<HomeMenu> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.arrMenus = data;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;

        if(itemSize!=0) {
        view = mInflater.inflate(R.layout.rv_item_grid_menu, parent, false);
           GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
           params.height = itemSize;
           view.setLayoutParams(params);
       }
      else{
          view = mInflater.inflate(R.layout.rv_item_menu, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        HomeMenu homeMenu = arrMenus.get(position);
        viewHolder.txtMenuTitle.setText(homeMenu.getMenuTitle());
        viewHolder.txtMenuDesc.setText(homeMenu.getMenuDesc());
        if(homeMenu.getMenuIcon()==0)
            return;
        viewHolder.imgMenuIcon.setImageDrawable(context.getResources().getDrawable(homeMenu.getMenuIcon()));

        viewHolder.imgMenuIcon.setVisibility(isMenuIcon?View.VISIBLE:View.GONE);

    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public void setMenuIcon(boolean menuIcon) {
        isMenuIcon = menuIcon;
    }

    public HomeMenu getMenumAt(int positon)
    {
        return arrMenus.get(positon);
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    @Override
    public int getItemCount() {
        return arrMenus.size();
    }



     static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        TextView txtMenuTitle;
        TextView txtMenuDesc;
        ImageView imgMenuIcon;


        ViewHolder(View view) {
            super(view);
         txtMenuTitle=view.findViewById(R.id.txtMenuTitle);
         txtMenuDesc=view.findViewById(R.id.txtMenuDesc);
            imgMenuIcon=view.findViewById(R.id.imgMenuIcon);

            view.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    @Override
    public void onItemDismiss(int position) {
        arrMenus.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(arrMenus, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(arrMenus, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
      //  return true;
    }


    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
