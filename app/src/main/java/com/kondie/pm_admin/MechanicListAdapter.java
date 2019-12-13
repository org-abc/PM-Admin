package com.kondie.pm_admin;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by kondie on 2018/02/17.
 */

public class MechanicListAdapter extends RecyclerView.Adapter<MechanicItemHolder> {

    public static Activity activity;
    private LayoutInflater inflater;
    private List<MechanicItem> userItems;
    private SharedPreferences prefs;
    private final int VIEW_TYPE_USER = 0;
    private final int VIEW_TYPE_LOADER = 1;
    private int totalItems, lastVisibleItems, itemsThreshold = 3;
    private static boolean isLoading;
    private OnEndOfListListener onEndOfListListener;

    public MechanicListAdapter(Activity activity, List<MechanicItem> userItems, RecyclerView userItemList){

        this.activity = activity;
        this.userItems = userItems;
        inflater = LayoutInflater.from(activity);
        prefs = MainActivity.activity.getSharedPreferences("DoubleChill", Context.MODE_PRIVATE);

        final LinearLayoutManager linearLayMan = (LinearLayoutManager) userItemList.getLayoutManager();
        userItemList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItems = linearLayMan.getItemCount();
                lastVisibleItems = linearLayMan.findLastVisibleItemPosition();
                if(!isLoading && totalItems < (lastVisibleItems + itemsThreshold)){

                    if(onEndOfListListener != null){
                        onEndOfListListener.onEndOfList();
                    }
                    isLoading = true;
                }
            }
        });
    }

    public static void isLoaded(){
        isLoading = false;
    }

    @Override
    public MechanicItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View shopViewItem = inflater.inflate(R.layout.mechanic_item, parent, false);
        MechanicItemHolder holder = new MechanicItemHolder(shopViewItem);

        return holder;
    }

    public void setOnEndOfListListener(OnEndOfListListener onEndOfListListener) {
        this.onEndOfListListener = onEndOfListListener;
    }

    @Override
    public int getItemCount() {
        return userItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(MechanicItemHolder holder, int position) {

        try {
            MechanicItem item = userItems.get(position);
            holder.shopName.setText(item.getMechanicName());
            holder.hiddenMechanicEmail.setText(item.getMechanicEmail());
            if (item.getMechanicStatus().equalsIgnoreCase("active")) {
                holder.shopStatusButton.setText("ACTIVE");
                holder.shopStatusButton.setBackgroundResource(R.drawable.shop_button_backg);
            }
            if (!item.getMechanicDpPath().equalsIgnoreCase("null") && !item.getMechanicDpPath().equalsIgnoreCase("")) {
                Picasso.with(activity).load(item.getMechanicDpPath()).into(holder.shopDp);
            } else {
                //holder.userDp.setImageResource(R.drawable.user_icon);
            }
        }catch (Exception e){
            Toast.makeText(activity, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}

