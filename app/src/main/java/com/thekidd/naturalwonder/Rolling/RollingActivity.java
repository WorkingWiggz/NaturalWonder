package com.thekidd.naturalwonder.Rolling;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;
import com.thekidd.naturalwonder.RolingBaseNW;
import com.thekidd.naturalwonder.Rolling.ui.main.SectionsPagerAdapter;

public class RollingActivity extends RolingBaseNW {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rolling);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.ViewPager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.MenuTabs);
        tabs.setupWithViewPager(viewPager);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToMenu();
            }
        });

    }

    private void BackToMenu() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}