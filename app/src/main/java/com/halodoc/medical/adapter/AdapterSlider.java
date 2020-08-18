package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.halodoc.medical.ActivityDetailTag;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.SliderModal;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterSlider extends SliderViewAdapter<AdapterSlider.SliderAdapterVH> {

    private ArrayList<SliderModal> sliderModals;
    private Context context;

    public AdapterSlider(Context context, ArrayList<SliderModal> ms) {
        this.context = context;
        this.sliderModals = ms;
    }

    @Override
    public AdapterSlider.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_slider, parent, false);

        return new SliderAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(AdapterSlider.SliderAdapterVH viewHolder, int position) {
        Picasso.get().load(sliderModals.get(position).getImage()).into(((SliderAdapterVH) viewHolder).image);
        Log.d("PHOTO", sliderModals.get(position).getImage());
    }

    @Override
    public int getCount() {
        return sliderModals.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {


        ImageView image;
        TextView title;
        Typeface typeface;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
