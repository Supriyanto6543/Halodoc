package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.halodoc.medical.ActivityDetailTag;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.SliderModal;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AdapterSlider extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<SliderModal> sliderModals;
    private Context context;
    private ViewPager2 viewPager2;

    public AdapterSlider(ArrayList<SliderModal> sliderModals, ViewPager2 viewPager2, Context context) {
        this.sliderModals = sliderModals;
        this.viewPager2 = viewPager2;
        this.context = context;
    }

    @NonNull
    @Override
    public SliderCarousel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_slider, parent, false);

        return new SliderCarousel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((SliderCarousel) holder).roundedImageView.setImageResource(sliderModals.get(position).getImage());
        ((SliderCarousel) holder).roundedImageView.setOnClickListener(new View.OnClickListener() {
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


    public class SliderCarousel extends RecyclerView.ViewHolder{

        RoundedImageView roundedImageView;

        public SliderCarousel(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.image);
        }

        void setImage(SliderModal sliderModal){
            roundedImageView.setImageResource(sliderModal.getImage());
        }

    }

}
