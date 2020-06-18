package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.halodoc.medical.ActivityDetailTag;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.SliderModal;
import com.halodoc.medical.modal.TagModal;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AdapterTag extends RecyclerView.Adapter<AdapterTag.AdapterTags> {

    private ArrayList<TagModal> sliderModals;
    private Context context;

    public AdapterTag(ArrayList<TagModal> sliderModals, Context context) {
        this.sliderModals = sliderModals;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterTag.AdapterTags onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_tag, parent, false);

        return new AdapterTags(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTag.AdapterTags holder, int position) {

        ((AdapterTags) holder).category.setText(sliderModals.get(position).getName());
        ((AdapterTags) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDetailTag.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sliderModals.size();
    }


    public class AdapterTags extends RecyclerView.ViewHolder{

        TextView category;

        public AdapterTags(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
        }
    }
}
