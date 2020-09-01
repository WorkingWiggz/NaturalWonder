package com.thekidd.naturalwonder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.jar.JarInputStream;

import de.cketti.mailto.EmailIntentBuilder;

public class BaseNWActivity extends Activity {
   protected int Theme;
   protected Boolean ThemeMode;
   protected String BaseURL = "https://dnd5eapi.co";
   protected RequestQueue queue;
   protected JSONObject RequestData = new JSONObject();
   protected ArrayList<StringRequest> UniqueRequests = new ArrayList<>();
   protected JSONObject LocalAPIData = new JSONObject();

    protected void LoadBackUpData()  {
        try {
            File sdcard = getApplicationContext().getExternalFilesDir(null);
            File dir = new File(sdcard + "/NaturalWonder/");

            if(!dir.exists()){
                dir.mkdir();
            }
            File localDateFile = new File(dir,"BackupData.JSON");
            if(!localDateFile.exists()){
                localDateFile.createNewFile();
            } else {
                   BufferedReader br = new BufferedReader(new FileReader(localDateFile));
                   if(br.ready()){
                       LocalAPIData = new JSONObject(br.readLine());
                   }
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }
    }

    protected void SaveBackUpData(){
        try {
            File sdcard = getApplicationContext().getExternalFilesDir(null);
            File dir = new File(sdcard + "/NaturalWonder/");

            if(!dir.exists()){
                dir.mkdir();
            }
            File localDateFile = new File(dir + "BackupData.JSON");
            if(localDateFile.exists()){
                FileWriter fw = new FileWriter(localDateFile);
                fw.write(LocalAPIData.toString());
                fw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e,getApplicationContext());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences a = getSharedPreferences("NWSharedPrefs",MODE_PRIVATE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Theme = a.getInt("theme",R.style.MainAppTheme_Light);
        if(Theme == R.style.MainAppTheme_Light){
            ThemeMode = true;
        } else if (Theme == R.style.MainAppTheme_Dark) {
            ThemeMode = false;
        }
        queue = Volley.newRequestQueue(this);
        setTheme(Theme);
        super.onCreate(savedInstanceState);
    }

    public void ErrorHandle(Exception error, Context c){
        Intent i = new Intent(c,ErrorPage.class);
        error.printStackTrace();
        i.putExtra("ErrorMSG",error.toString());
        i.putExtra("ErrorPage",c.getClass().toString().split(" ")[1].split(".naturalwonder.")[1]);
        startActivity(i);
    }

    protected static ArrayList<View> getViewsByTag(ViewGroup root, String tag){
        ArrayList<View> views = new ArrayList<>();
        final int childCount = root.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag((ViewGroup) child, tag));
            }

            final Object tagObj = child.getTag();
            if (tagObj != null && tagObj.equals(tag)) {
                views.add(child);
            }

        }
        return views;
    }

    protected void HandleRequest (final StringRequest SR) {
        Thread t = new Thread() {
            @Override
            public void run() {
                queue.add(SR);
        }
        };
        t.run();
    }

    protected void SaveToLocalBackup(String response, String url) throws JSONException {
        if(!LocalAPIData.has(url)){
            LocalAPIData.put(url,new JSONObject(response));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SaveBackUpData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        SaveBackUpData();
    }

    protected void HandleFeedBack(Context c){
        String Subject = "Feedback for Natural Wonder";
        String Body = "(Write your message here)" ;
        Intent emailIntent = EmailIntentBuilder.from(c).to("workingwiggz.info@gmail.com").subject(Subject).body(Body).build();
        startActivity(emailIntent);
    }
    protected void HandleFeedBack(Context c, String ErrorMessage,String ErrorType){
        String Subject = "Bug Report for Natural Wonder within " + ErrorType;
        String Body = "This was the Bug: "+ ErrorMessage;
        Intent emailIntent = EmailIntentBuilder.from(c).to("workingwiggz.info@gmail.com").subject(Subject).body(Body).build();
        startActivity(emailIntent);
    }
}
