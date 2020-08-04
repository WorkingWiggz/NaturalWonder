package com.thekidd.naturalwonder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseNWActivity extends Activity {
   protected int Theme;
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
        error.printStackTrace();
        i.putExtra("ErrorMSG",error.toString());
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
}
