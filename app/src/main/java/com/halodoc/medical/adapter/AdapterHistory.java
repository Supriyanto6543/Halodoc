package com.halodoc.medical.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.R;
import com.halodoc.medical.interfaces.DeleteCart;
import com.halodoc.medical.modal.ModalCart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Supriyanto on 10/5/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.MyCart> {

    private Context context;
    private ArrayList<ModalCart> modalCarts;
    DeleteCart deleteCart;

    public AdapterHistory(Context context, ArrayList<ModalCart> modalCarts, DeleteCart deleteCart) {
        this.context = context;
        this.modalCarts = modalCarts;
        this.deleteCart = deleteCart;
    }

    @NonNull
    @Override
    public AdapterHistory.MyCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_history, parent, false);

        return new MyCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistory.MyCart holder, final int position) {
        holder.title.setText(modalCarts.get(position).getTitle());
        holder.price.setText("Rp: " + modalCarts.get(position).getAmount());
        holder.qty.setText("(" + modalCarts.get(position).getQty() + "x)" + "");
        Picasso.get().load(modalCarts.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return modalCarts.size();
    }

    public class MyCart extends RecyclerView.ViewHolder{

        private TextView title, price, qty;
        private ImageView image;


        public MyCart(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            qty = itemView.findViewById(R.id.qty);
            image = itemView.findViewById(R.id.image);
        }
    }
}
