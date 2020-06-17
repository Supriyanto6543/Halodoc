package com.halodoc.medical.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.halodoc.medical.R;
import com.halodoc.medical.modal.SliderModal;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class AdapterSlider extends RecyclerView.Adapter<AdapterSlider.SliderCarousel>{

    private ArrayList<SliderModal> sliderModals;
    private Context context;
    private ViewPager2 viewPager2;

    public AdapterSlider(ArrayList<SliderModal> sliderModals, ViewPager2 viewPager2) {
        this.sliderModals = sliderModals;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public SliderCarousel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_slider, parent, false);

        return new SliderCarousel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderCarousel holder, int position) {
        holder.setImage(sliderModals.get(position));
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
