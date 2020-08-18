package com.halodoc.medical.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.R;
import com.halodoc.medical.modal.ProductModal;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterNews  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ProductModal> categoryModals;
    private Context context;

    public AdapterNews(ArrayList<ProductModal> categoryModals, Context context) {
        this.categoryModals = categoryModals;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflater = LayoutInflater.from(context).inflate(R.layout.adapter_news, parent, false);

        return new AdapterNews.MyAdapter(inflater);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((MyAdapter) holder).title.setText(categoryModals.get(position).getName_product());
        ((MyAdapter) holder).price.setText("Rp " + categoryModals.get(position).getPrice_product());
        Picasso.get().load(categoryModals.get(position).getImage_product()).into(((MyAdapter) holder).image);
        ((MyAdapter) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "DETAIL NEWS " + position + " DIKLIK", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModals.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title, category, price;
        private Typeface typeface;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            category = itemView.findViewById(R.id.category);
            typeface = Typeface.createFromAsset(context.getAssets(), "fon.otf");
            title.setTypeface(typeface);
            price.setTypeface(typeface);
        }
    }
}
