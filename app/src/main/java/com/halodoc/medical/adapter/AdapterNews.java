package com.halodoc.medical.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halodoc.medical.R;
import com.halodoc.medical.modal.CategoryModal;
import com.halodoc.medical.modal.NewsModal;

import java.util.ArrayList;

public class AdapterNews  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<NewsModal> categoryModals;
    private Context context;

    public AdapterNews(ArrayList<NewsModal> categoryModals, Context context) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyAdapter) holder).image.setImageDrawable(context.getResources().getDrawable(categoryModals.get(position).getImage()));
        ((MyAdapter) holder).title.setText(categoryModals.get(position).getTitle());
        ((MyAdapter) holder).category.setText(categoryModals.get(position).getCategory());
    }

    @Override
    public int getItemCount() {
        return categoryModals.size();
    }

    public class MyAdapter extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title, category;
        private Typeface typeface;

        public MyAdapter(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.title);
            category = itemView.findViewById(R.id.category);
            typeface = Typeface.createFromAsset(context.getAssets(), "avenir3.otf");
            title.setTypeface(typeface);
        }
    }
}
