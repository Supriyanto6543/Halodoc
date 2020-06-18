package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.ActivityDokter;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.CategoryModal;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<CategoryModal> categoryModals;
    private Context context;

    public AdapterCategory(ArrayList<CategoryModal> categoryModals, Context context) {
        this.categoryModals = categoryModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater.from(context).inflate(R.layout.adapter_category, parent, false);

        return new MyAdapter(inflater);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyAdapter) holder).image.setImageDrawable(context.getResources().getDrawable(categoryModals.get(position).getImage()));
        ((MyAdapter) holder).title.setText(categoryModals.get(position).getTitle());
        ((MyAdapter) holder).desc.setText(categoryModals.get(position).getDesc());
        ((MyAdapter) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDokter.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModals.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title, desc;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }
    }
}
