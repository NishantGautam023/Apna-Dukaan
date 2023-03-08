package com.android.ApnaDukaan.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.ApnaDukaan.R;
import com.android.ApnaDukaan.model.RestaurantModel;
import com.bumptech.glide.Glide;

import java.util.List;

public class DukaanListAdapter extends RecyclerView.Adapter<DukaanListAdapter.MyViewHolder> {

    private List<RestaurantModel> restaurantModelList;
    private RestaurantListClickListener clickListener;

    public DukaanListAdapter(List<RestaurantModel> restaurantModelList, RestaurantListClickListener clickListener) {
        this.restaurantModelList = restaurantModelList;
        this.clickListener = clickListener;
    }

    public void updateData(List<RestaurantModel> restaurantModelList) {
        this.restaurantModelList = restaurantModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DukaanListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return  new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DukaanListAdapter.MyViewHolder holder, int position) {
        holder.dukaanType.setText(restaurantModelList.get(position).getName());
        holder.dukaanFrom.setText("From: "+restaurantModelList.get(position).getAddress());
        holder.dukaanHours.setText("Opening Hours: " + restaurantModelList.get(position).getHours().getTodaysHours());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(restaurantModelList.get(position));
            }
        });
        Glide.with(holder.thumbImage)
                .load(restaurantModelList.get(position).getImage())
                .into(holder.thumbImage);

    }

    @Override
    public int getItemCount() {
        return restaurantModelList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  dukaanType;
        TextView  dukaanFrom;
        TextView  dukaanHours;
        ImageView thumbImage;

        public MyViewHolder(View view) {
            super(view);
            dukaanType = view.findViewById(R.id.dukaanType);
            dukaanFrom = view.findViewById(R.id.dukaanFrom);
            dukaanHours = view.findViewById(R.id.dukaanHours);
            thumbImage = view.findViewById(R.id.thumbImage);

        }
    }

    public interface RestaurantListClickListener {
        public void onItemClick(RestaurantModel restaurantModel);
    }
}
