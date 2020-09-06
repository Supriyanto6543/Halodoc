package com.halodoc.medical.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.R;
import com.halodoc.medical.interfaces.CheckBoxs;
import com.halodoc.medical.modal.ModalBank;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Supriyanto on 9/5/2020.
 * Copyright (c) 2020 Murottal Pro . All rights reserved.
 */
public class AdapterBank extends RecyclerView.Adapter<AdapterBank.MyBankAccount> {

    private ArrayList<ModalBank> modalBanks;
    private Context context;
    CheckBoxs boxs;

    public AdapterBank(ArrayList<ModalBank> modalBanks, Context context) {
        this.modalBanks = modalBanks;
        this.context = context;
    }

    @NonNull
    @Override
    public MyBankAccount onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_transfer, parent, false);

        return new MyBankAccount(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyBankAccount holder, final int position) {

        holder.bank.setText(modalBanks.get(position).getName_bank());
        holder.name.setText(modalBanks.get(position).getAccount_bank());
        holder.number.setText(modalBanks.get(position).getNumber_bank());
        Picasso.get().load(modalBanks.get(position).getImage_bank()).into(holder.image);

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    //holder.checkBox.setChecked(boxs.onClick(modalBanks.get(position).getId_bank()));
                    holder.checkBox.setChecked(false);
                }else{
                    holder.checkBox.setChecked(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return modalBanks.size();
    }

    class MyBankAccount extends RecyclerView.ViewHolder{

        RadioButton checkBox;
        private ImageView image;
        private TextView bank, name, number;

        public MyBankAccount(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkbox);
            image = itemView.findViewById(R.id.image);
            bank = itemView.findViewById(R.id.bank);
            name = itemView.findViewById(R.id.name);
            number = itemView.findViewById(R.id.number);

        }
    }
}
