package com.pakdev.homemenu;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Collections;


public class HomeMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperCallback {


    private final LayoutInflater mInflater;
    private Context context;
    private ArrayList<HomeMenu> arrMenus;
    public static ItemClickListener mClickListener;
    public boolean isMenuIcon;
    public boolean isGridMenu;

    public int itemSize;
    public int shapeDrawable;
    public int textColor;
    public int cardColor=-1;
    long DURATION = 0;
    private boolean on_attach = true;

    public HomeMenu.DRAWABLE_SHAPE drawable_shape;
    public HomeMenu.MENU_ANIMATION animationType;


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

        if(isGridMenu&&cardColor==-1) {
        view = mInflater.inflate(R.layout.rv_item_circle_grid_menu, parent, false);
           GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
           params.height = itemSize;
           view.setLayoutParams(params);

        }
        else if(cardColor!=-1&&isGridMenu)
        {
            view = mInflater.inflate(R.layout.rv_item_grid_menu, parent, false);
            GridLayoutManager.LayoutParams params = (GridLayoutManager.LayoutParams) view.getLayoutParams();
            params.height = itemSize;
            view.setLayoutParams(params);
        }
      else{
          view = mInflater.inflate(R.layout.rv_material_home_menu, parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder=(ViewHolder)holder;
        HomeMenu homeMenu = arrMenus.get(position);
        viewHolder.txtMenuTitle.setText(homeMenu.getMenuTitle());
        viewHolder.txtMenuDesc.setText(homeMenu.getMenuDesc());

        viewHolder.txtMenuTitle.setTextColor(context.getResources().getColor(textColor));
        viewHolder.txtMenuDesc.setTextColor(context.getResources().getColor(textColor));
        if(homeMenu.getMenuIcon()==0)
            return;
        viewHolder.imgMenuIcon.setImageDrawable(context.getResources().getDrawable(homeMenu.getMenuIcon()));

        viewHolder.imgMenuIcon.setVisibility(isMenuIcon?View.VISIBLE:View.GONE);

        if(isGridMenu&&cardColor!=-1)
        {
            viewHolder.cardMenu.setCardBackgroundColor(context.getResources().getColor(cardColor));
        }

        switch (drawable_shape)
        {
            case CIRCLE:
            {
                viewHolder.rootlayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.mhm_shape_circle));

            }
            break;
            case ROUNDED:
            {
                viewHolder.rootlayout.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.mhm_shape_rounded));

            }
            break;
            case CUSTOM:
            {
                viewHolder.rootlayout.setBackgroundDrawable(context.getResources().getDrawable(shapeDrawable));

            }
            break;

        }

        if(animationType!=null&&animationType.equals(HomeMenu.MENU_ANIMATION.FADE_IN)) {
            AnimationUtils.setFadeAnimation(holder.itemView, position, DURATION);
        }
        else if(animationType!=null&&animationType.equals(HomeMenu.MENU_ANIMATION.LEFT_TO_RIGHT)) {
            AnimationUtils.FromLeftToRight(holder.itemView, position, DURATION);
        }
        else if(animationType!=null&&animationType.equals(HomeMenu.MENU_ANIMATION.RIGHT_TO_LEFT)) {
            AnimationUtils.FromRightToLeft(holder.itemView, position, DURATION);
        }
    }


    public void setGridMenu(boolean gridMenu,HomeMenu.DRAWABLE_SHAPE shape) {
        isGridMenu = gridMenu;
        drawable_shape=shape;
        notifyDataSetChanged();
    }

    public void setItemSize(int itemSize) {
        this.itemSize = itemSize;
    }

    public void setAnimationType(HomeMenu.MENU_ANIMATION animationType,long duration) {
        this.animationType = animationType;
        this.DURATION=duration;
        notifyDataSetChanged();
    }
    public void setMenuCardColor(int mCardcolor) {
        this.cardColor = mCardcolor;
        notifyDataSetChanged();
    }
    public void setMenuIcon(boolean menuIcon) {
        isMenuIcon = menuIcon;
        notifyDataSetChanged();
    }

    public HomeMenu getMenumAt(int positon)
    {
        return arrMenus.get(positon);
    }
    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    public void setShapeDrawable(int shapeDrawable) {
        if(shapeDrawable==-1)
            return;
        this.shapeDrawable = shapeDrawable;
        notifyDataSetChanged();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    @Override
    public int getItemCount() {
        return arrMenus.size();
    }



     static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {



        TextView txtMenuTitle;
        LinearLayout rootlayout;
        TextView txtMenuDesc;
        ImageView imgMenuIcon;
        CardView cardMenu;


        ViewHolder(View view) {
            super(view);
         txtMenuTitle=view.findViewById(R.id.txtMenuTitle);
            rootlayout=view.findViewById(R.id.rootlayout);
         txtMenuDesc=view.findViewById(R.id.txtMenuDesc);
            imgMenuIcon=view.findViewById(R.id.imgMenuIcon);
            cardMenu=view.findViewById(R.id.cardMenu);

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
