package com.halodoc.medical;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.halodoc.medical.fragment.FragmentHome;
import com.halodoc.medical.fragment.FragmentLainnya;
import com.halodoc.medical.fragment.FragmentProfile;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

public class MainActivity extends AppCompatActivity {

    SpaceNavigationView spaceNavigationView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spaceNavigationView = (SpaceNavigationView) findViewById(R.id.space);
        spaceNavigationView.initWithSaveInstanceState(savedInstanceState);
        spaceNavigationView.setCentreButtonIcon(R.drawable.ic_android);
        spaceNavigationView.addSpaceItem(new SpaceItem("PROFILE", R.drawable.ic_account));
        spaceNavigationView.addSpaceItem(new SpaceItem("LAINNYA", R.drawable.ic_lainnya));
        spaceNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                changeFragment(new FragmentHome());
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                int pos = itemIndex;
                switch (pos){
                    case 0:
                        changeFragment(new FragmentProfile());
                        break;
                    case 1:
                        changeFragment(new FragmentLainnya());
                        break;

                    default:
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                int pos = itemIndex;
                switch (pos){
                    case 0:
                        changeFragment(new FragmentProfile());
                        break;
                    case 1:
                        changeFragment(new FragmentLainnya());
                        break;

                    default:
                }
            }
        });

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FragmentHome()).commit();

    }

    private boolean changeFragment(Fragment f){

        if (f != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame, f)
                    .commit();
            return true;
        }

        return false;
    }

}