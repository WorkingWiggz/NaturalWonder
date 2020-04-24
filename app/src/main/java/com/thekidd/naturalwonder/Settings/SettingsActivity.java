package com.thekidd.naturalwonder.Settings;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import java.io.File;

public class SettingsActivity extends BaseNWActivity {
    Button MenuButt,PickColor,Import,Export,EraseData,SaveSettButt;
    Switch LightMode,DarkMode;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EraseData = findViewById(R.id.EraseDataButt);
        LightMode = findViewById(R.id.LightModeSwitch);
        DarkMode = findViewById(R.id.DarkModeSwitch);
        if(ThemeMode){
            LightMode.setChecked(true);
            DarkMode.setChecked(false);
        } else {
            LightMode.setChecked(false);
            DarkMode.setChecked(true);
        }
        LightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(LightMode.isChecked()){
                    SwitchToLight(savedInstanceState);
                } else {
                    SwitchToDark(savedInstanceState);
                }
            }
        });

        DarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(DarkMode.isChecked()){
                    SwitchToDark(savedInstanceState);
                } else {
                    SwitchToLight(savedInstanceState);
                }
            }
        });

        EraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EraseFilesAndData();
            }
        });

        MenuButt = findViewById(R.id.MenuButt);
        final Intent MenuBack = new Intent(this, MainActivity.class);
        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });

    }

    private void ImportData() {

    }

    private void ExportData() {

    }


    private void EraseFilesAndData() {


        File CS = new File(getApplicationContext().getExternalFilesDir(null)+"/NaturalWonder/CharacterSheets/");
        File SN = new File(getApplicationContext().getExternalFilesDir(null)+"/NaturalWonder/SessionNotes/");

        if(CS.exists() || SN.exists()){
            String [] CSEntries = CS.list();
            String [] SNEntries = SN.list();
            if(CSEntries !=null){
                for (String csEntry : CSEntries) {
                    File Curr = new File(CS.getPath(), csEntry);
                    Curr.delete();
                }
            }

            if(SNEntries !=null){
                for (String snEntry : SNEntries) {
                    File Curr = new File(SN.getPath(), snEntry);
                    Curr.delete();
                }
            }

        }

    }

    private void PickAccentColor() {

    }

    public void SwitchToDark (Bundle savedInstanceState){
        DarkMode.setChecked(true);
        LightMode.setChecked(false);
        SharedPreferences a = getSharedPreferences("NWSharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor myEditor = a.edit();
        myEditor.putInt("theme",R.style.MainAppTheme_Dark);
        myEditor.apply();
        recreate();
    }

    public void SwitchToLight(Bundle savedInstanceState){
        DarkMode.setChecked(false);
        LightMode.setChecked(true);
        SharedPreferences a = getSharedPreferences("NWSharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor myEditor = a.edit();
        myEditor.putInt("theme",R.style.MainAppTheme_Light);
        myEditor.apply();
        recreate();
    }

}
