package com.halodoc.medical.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.halodoc.medical.ActivityCategoryAll;
import com.halodoc.medical.R;
import com.halodoc.medical.adapter.AdapterCategory;
import com.halodoc.medical.adapter.AdapterNews;
import com.halodoc.medical.adapter.AdapterSlider;
import com.halodoc.medical.adapter.AdapterTag;
import com.halodoc.medical.modal.CategoryModal;
import com.halodoc.medical.modal.NewsModal;
import com.halodoc.medical.modal.SliderModal;
import com.halodoc.medical.modal.TagModal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class FragmentHome extends Fragment {

    RecyclerView recyclerView, recyclerView0, recyclerView1;
    AdapterCategory adapterCategory;
    AdapterTag adapterTag;
    AdapterNews adapterNews;
    ArrayList<CategoryModal> cm;
    ArrayList<SliderModal> sm;
    ArrayList<TagModal> tm;
    ArrayList<NewsModal> nm;
    ViewPager2 viewPager2;
    TextView lihat_semua;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_dashboard, container, false);
        initsCategory(view);
        initsSlider(view);
        initsTags(view);
        initsNews(view);
        lihat_semua = view.findViewById(R.id.lihat_semua);
        lihat_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCategoryAll.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initsCategory(View view){
        recyclerView = view.findViewById(R.id.recycler);
        cm = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapterCategory = new AdapterCategory(addingItem(cm), getActivity());
        recyclerView.setAdapter(adapterCategory);
    }

    private void initsSlider(View view){
        viewPager2 = view.findViewById(R.id.viewpager);
        sm = new ArrayList<>();
        sm.add(new SliderModal(R.drawable.banner1));
        sm.add(new SliderModal(R.drawable.banner2));
        sm.add(new SliderModal(R.drawable.banner3));
        viewPager2.setAdapter(new AdapterSlider(sm, viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer cpt = new CompositePageTransformer();
        cpt.addTransformer(new MarginPageTransformer(40));
        cpt.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float ss = Math.abs(position);
                page.setScaleY(0.85f + ss * 0.15f);
            }
        });
        viewPager2.setPageTransformer(cpt);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                new Handler().removeCallbacks(slider);
                new Handler().postDelayed(slider, 15000);
            }
        });
    }

    private void initsTags(View view){
        recyclerView0 = view.findViewById(R.id.recycler0);
        tm = new ArrayList<>();
        recyclerView0.setLayoutManager(new GridLayoutManager(getActivity(), 1, LinearLayoutManager.HORIZONTAL, false));
        adapterTag = new AdapterTag(addingItemTag(tm), getActivity());
        recyclerView0.setAdapter(adapterTag);
    }

    private void initsNews(View view){
        recyclerView1 = view.findViewById(R.id.recycler1);
        nm = new ArrayList<>();
        recyclerView1.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        adapterNews = new AdapterNews(addingItemNews(nm), getActivity());
        recyclerView1.setAdapter(adapterNews);
    }

    private ArrayList<CategoryModal> addingItem(ArrayList<CategoryModal> modals){

        modals.add(new CategoryModal(R.drawable.nurse1, "Chat dengan Dokter", "Dokter terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse3, "Beli Obat", "Apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse4, "Tes Covid 19", "Dari apotek terpercaya"));
        modals.add(new CategoryModal(R.drawable.nurse1, "Kesehatan Jiwa", "Temukan jawabanmu"));

        return modals;
    }

    private ArrayList<TagModal> addingItemTag(ArrayList<TagModal> tagModals){

        tagModals.add(new TagModal("Kesehatan"));
        tagModals.add(new TagModal("Covid 19"));
        tagModals.add(new TagModal("Perawatan"));
        tagModals.add(new TagModal("Pasien"));
        tagModals.add(new TagModal("Doctor Umum"));

        return tagModals;
    }

    private ArrayList<NewsModal> addingItemNews(ArrayList<NewsModal> newsModals){

        newsModals.add(new NewsModal(R.drawable.banner3, "Doctor", "Berotabat Langsung ke Dokter Apabila Sakit"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Kesehatan", "Jangan Lupa jaga Kesehatan di Pandemi ini"));
        newsModals.add(new NewsModal(R.drawable.banner2, "Makanan", "Makan dan Minum yang Membuat Tubuh Menjadi Sehat"));
        newsModals.add(new NewsModal(R.drawable.banner1, "Berobat", "Jangan Lupa Meminum Obat atau Resep dari Dokter"));
        newsModals.add(new NewsModal(R.drawable.banner3, "Protein", "Protein adalah Sumber Kekuatan Bagi Manusia"));

        return newsModals;
    }

    private Runnable slider = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };
}
