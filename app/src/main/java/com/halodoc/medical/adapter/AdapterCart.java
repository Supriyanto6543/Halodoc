package com.halodoc.medical.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.R;
import com.halodoc.medical.modal.ModalCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Supriyanto on 8/26/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class AdapterCart extends RecyclerView.Adapter<AdapterCart.MyCart> {

    private Context context;
    private ArrayList<ModalCart> modalCarts;

    public AdapterCart(Context context, ArrayList<ModalCart> modalCarts) {
        this.context = context;
        this.modalCarts = modalCarts;
    }

    @NonNull
    @Override
    public AdapterCart.MyCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_cart, parent, false);

        return new MyCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCart.MyCart holder, int position) {
        holder.title.setText(modalCarts.get(position).getTitle());
        holder.price.setText(modalCarts.get(position).getAmount());
        Picasso.get().load(modalCarts.get(position).getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HORES", "ANWAR ZAHID CS");
            }
        });
    }

    @Override
    public int getItemCount() {
        return modalCarts.size();
    }

    public class MyCart extends RecyclerView.ViewHolder{

        private TextView title, price, remove;
        private ImageView image;

        public MyCart(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            remove = itemView.findViewById(R.id.remove);
            image = itemView.findViewById(R.id.image);
        }
    }
}
