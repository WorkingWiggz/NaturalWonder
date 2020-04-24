package com.thekidd.naturalwonder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class BaseNWActivity extends Activity {
   int Theme;
   protected Boolean ThemeMode;
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
        setTheme(Theme);
        super.onCreate(savedInstanceState);
    }

    public void ErrorHandle(Exception error, Context c){
        Intent i = new Intent(c,ErrorPage.class);
        i.putExtra("ErrorMSG",error.toString());
        startActivity(i);
    }

}
