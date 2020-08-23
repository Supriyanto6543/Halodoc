package com.halodoc.medical.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.ActivityChats;
import com.halodoc.medical.ActivityDokterDetail;
import com.halodoc.medical.R;
import com.halodoc.medical.modal.DokterModal;

import java.util.ArrayList;

public class AdapterDokter extends RecyclerView.Adapter<AdapterDokter.MyDokter> {

    ArrayList<DokterModal> dokterModals;
    Context context;

    public AdapterDokter(ArrayList<DokterModal> dokterModals, Context context) {
        this.dokterModals = dokterModals;
        this.context = context;
    }

    @NonNull
    @Override
    public MyDokter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dokter, parent, false);

        return new MyDokter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDokter holder, int position) {
        ((MyDokter) holder).image.setImageDrawable(context.getResources().getDrawable(dokterModals.get(position).getImage()));
        ((MyDokter) holder).title.setText(dokterModals.get(position).getTitle());
        ((MyDokter) holder).job.setText(dokterModals.get(position).getJob());
        ((MyDokter) holder).price.setText(dokterModals.get(position).getPrice());
        ((MyDokter) holder).rating.setText(dokterModals.get(position).getRating());
        ((MyDokter) holder).experience.setText(dokterModals.get(position).getExperience());
        ((MyDokter) holder).button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityChats.class);
                context.startActivity(intent);
            }
        });
        ((MyDokter) holder).click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityDokterDetail.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dokterModals.size();
    }

    public class MyDokter extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title, job, price, rating, experience;
        Typeface typeface;
        LinearLayout click;
        Button button;

        public MyDokter(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            job = itemView.findViewById(R.id.job);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            experience = itemView.findViewById(R.id.experience);
            click = itemView.findViewById(R.id.click);
            button = itemView.findViewById(R.id.chat);
            typeface = Typeface.createFromAsset(context.getAssets(), "avenir3.otf");
            title.setTypeface(typeface);
            price.setTypeface(typeface);
            button.setTypeface(typeface);
        }

    }
}
