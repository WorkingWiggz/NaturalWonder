package com.thekidd.naturalwonder;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.thekidd.naturalwonder.CharacterSheets.SheetsActivity;
import com.thekidd.naturalwonder.Developer.DeveloperActivity;
import com.thekidd.naturalwonder.LookUp.LookingUpActivity;
import com.thekidd.naturalwonder.Rolling.RollingActivity;
import com.thekidd.naturalwonder.SessionNotes.NotesActivity;
import com.thekidd.naturalwonder.Settings.SettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseNWActivity {
    Button Settings,Lookup,Developer,Sheets,Rolling,Notes;
    RequestQueue queue;
    JSONObject hold;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckPerms();
        LoadBackUpData();


        queue = Volley.newRequestQueue(getApplicationContext());
        Settings = findViewById(R.id.SettButt);
        Lookup = findViewById(R.id.LookButt);
        Developer = findViewById(R.id.DevButt);
        Sheets = findViewById(R.id.SheetButt);
        Rolling = findViewById(R.id.RollStatsButt);
        Notes = findViewById(R.id.SessButt);




        Settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
              Intent S = new Intent (MainActivity.this, SettingsActivity.class);
              startActivity(S);
            }
        });

        Lookup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent L = new Intent (MainActivity.this, LookingUpActivity.class);
                startActivity(L);
            }
        });

        Developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent D = new Intent (MainActivity.this, DeveloperActivity.class);
                startActivity(D);
            }
        });

        Sheets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Sh = new Intent (MainActivity.this, SheetsActivity.class);
                startActivity(Sh);
            }
        });

        Rolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent (MainActivity.this, RollingActivity.class);
                startActivity(r);
            }
        });

        Notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent N = new Intent (MainActivity.this, NotesActivity.class);
                startActivity(N);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void CheckPerms(){
        ArrayList<String> PermsToRequest = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED){
            PermsToRequest.add(Manifest.permission.INTERNET);
        }

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            PermsToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            PermsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(PermsToRequest.size()>0){
            String[] a = PermsToRequest.toArray(new String[0]);
            requestPermissions(a,2);
        }

    }




}
