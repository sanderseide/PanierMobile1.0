package com.example.paniermobil.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.paniermobil.Models.FirrstItem;
import com.example.paniermobil.R;
import com.example.paniermobil.activities.DetailsActivity;

import java.util.List;

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.ViewHolder> {

    private Context context;
    private List<FirrstItem> firrstItemList;

    public SecondAdapter(Context context, List<FirrstItem> firrstItemList) {
        this.context = context;
        this.firrstItemList = firrstItemList;
    }

    @NonNull
    @Override
    public SecondAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.first_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(firrstItemList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(firrstItemList.get(position).getName());
        holder.price.setText(String.valueOf(firrstItemList.get(position).getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("detailed", firrstItemList.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return firrstItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgfirst);
            name = itemView.findViewById(R.id.short_desctiption);
            price = itemView.findViewById(R.id.price);
        }
    }
}
