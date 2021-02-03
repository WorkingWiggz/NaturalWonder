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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thekidd.naturalwonder.LookUp.ItemActivities.AbilityScores;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Classes;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Conditions;
import com.thekidd.naturalwonder.LookUp.ItemActivities.DamageTypes;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Equipment;
import com.thekidd.naturalwonder.LookUp.ItemActivities.EquipmentCategories;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Features;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Languages;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Levels;
import com.thekidd.naturalwonder.LookUp.ItemActivities.MagicItems;
import com.thekidd.naturalwonder.LookUp.ItemActivities.MagicSchools;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Monsters;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Proficiencies;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Races;
import com.thekidd.naturalwonder.LookUp.ItemActivities.RuleSections;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Rules;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Skills;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Spells;
import com.thekidd.naturalwonder.LookUp.ItemActivities.StartingEquipment;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Subclasses;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Subraces;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Traits;
import com.thekidd.naturalwonder.LookUp.ItemActivities.WeaponProperties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import de.cketti.mailto.EmailIntentBuilder;

public class BaseNWActivity extends Activity {
    protected int Theme;
    protected Boolean ThemeMode;
    protected String BaseURL = "https://www.dnd5eapi.co";
    protected RequestQueue queue;
    protected JSONObject LocalAPIData = new JSONObject();

    protected static ArrayList<View> getViewsByTag(ViewGroup root, String tag) {
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

    protected void LoadBackUpData() {
        try {
            File sdcard = getApplicationContext().getExternalFilesDir(null);
            File dir = new File(sdcard + "/NaturalWonder/");

            if (!dir.exists()) {
                dir.mkdir();
            }
            File localDateFile = new File(dir + "/BackupData.json");
            if (localDateFile.exists()) {
                Scanner scan = new Scanner(localDateFile);
                String a = "";
                while (scan.hasNextLine()) {
                    a = a + scan.nextLine();
                }
                if (!a.isEmpty()) {
                    LocalAPIData = new JSONObject(a);
                }
                scan.close();
            } else {
                localDateFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }
    }

    protected void SaveBackUpData() {
        try {
            File sdcard = getApplicationContext().getExternalFilesDir(null);
            File dir = new File(sdcard + "/NaturalWonder/");

            if (!dir.exists()) {
                dir.mkdir();
            }
            File localDateFile = new File(dir + "/BackupData.json");
            if (localDateFile.exists()) {
                FileWriter fw = new FileWriter(localDateFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(LocalAPIData.toString());
                bw.flush();
                bw.close();
            } else {
                localDateFile.createNewFile();
                FileWriter fw = new FileWriter(localDateFile);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(LocalAPIData.toString());
                bw.flush();
                bw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e, getApplicationContext());
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences a = getSharedPreferences("NWSharedPrefs", MODE_PRIVATE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Theme = a.getInt("theme", R.style.MainAppTheme_Light);
        if (Theme == R.style.MainAppTheme_Light) {
            ThemeMode = true;
        } else if (Theme == R.style.MainAppTheme_Dark) {
            ThemeMode = false;
        }
        LoadBackUpData();
        queue = Volley.newRequestQueue(this);
        setTheme(Theme);
        super.onCreate(savedInstanceState);
    }

    public void ErrorHandle(Exception error, Context c) {
        Intent i = new Intent(c, ErrorPage.class);
        error.printStackTrace();
        i.putExtra("ErrorMSG", error.toString());
        startActivity(i);
    }

    protected JSONObject HandleRequest(final StringRequest SR, RequestQueue queue) throws JSONException {

        if (LocalAPIData.has(SR.getUrl())) {
            JSONObject DataObject = LocalAPIData.getJSONObject(SR.getUrl());
            return DataObject;
        } else {
            SR.setShouldCache(false);
            queue.add(SR);
            return null;
        }

    }

    protected JSONArray HandleRequestJA(final StringRequest SR, RequestQueue queue) throws JSONException {
        if (LocalAPIData.has(SR.getUrl())) {
            JSONArray DataObject = LocalAPIData.getJSONArray(SR.getUrl());
            return DataObject;
        } else {
            SR.setShouldCache(false);
            queue.add(SR);
            return null;
        }

    }

    protected void SaveToLocalBackup(String url, JSONArray data) throws JSONException {
        if (ValidateForBackup(url, data)) {
            LocalAPIData.put(url, data);
        }
        SaveBackUpData();
    }


    protected void SaveToLocalBackup(String url, JSONObject data) throws JSONException {
        if (ValidateForBackup(url, data)) {
            LocalAPIData.put(url, data);
        }
        SaveBackUpData();
    }

    private boolean ValidateForBackup(String url, JSONObject data) {
        Boolean Valid = false;

        if (!LocalAPIData.has(url)) {
            if (data.keys().next().equals("fields")) {
                Valid = true;
            } else {
                String a = "https://dnd5eapi.co/graphql?query=%7B";
                String Shaver = url.substring(a.length());
                String Shaved = Shaver.substring(0, Shaver.indexOf('%'));
                if (Shaved.equals(data.keys().next())) {
                    Valid = true;
                }
            }

        }


        return Valid;
    }

    private boolean ValidateForBackup(String url, JSONArray data) {
        Boolean Valid = false;
        if (!LocalAPIData.has(url)) {
            Valid = true;
        }
        return Valid;
    }


    @Override
    protected void onStart() {
        super.onStart();
        LoadBackUpData();
    }


    @Override
    protected void onPause() {
        super.onPause();
        SaveBackUpData();
    }


    protected Intent PickIntent(int a) {
        Intent ItemType = new Intent(this, ErrorPage.class);
        switch (a) {
            case 0:
                ItemType = new Intent(this, AbilityScores.class);
                break;
            case 2:
                ItemType = new Intent(this, Classes.class);
                break;
            case 1:
                ItemType = new Intent(this, Conditions.class);
                break;
            case 3:
                ItemType = new Intent(this, DamageTypes.class);
                break;
            case 5:
                ItemType = new Intent(this, EquipmentCategories.class);
                break;
            case 4:
                ItemType = new Intent(this, Equipment.class);
                break;
            case 6:
                ItemType = new Intent(this, Features.class);
                break;
            case 7:
                ItemType = new Intent(this, Languages.class);
                break;
            case 8:
                ItemType = new Intent(this, Levels.class);
                break;
            case 9:
                ItemType = new Intent(this, MagicItems.class);
                break;
            case 10:
                ItemType = new Intent(this, MagicSchools.class);
                break;
            case 11:
                ItemType = new Intent(this, Monsters.class);
                break;
            case 12:
                ItemType = new Intent(this, Proficiencies.class);
                break;
            case 13:
                ItemType = new Intent(this, Races.class);
                break;
            case 14:
                ItemType = new Intent(this, Rules.class);
                break;
            case 15:
                ItemType = new Intent(this, RuleSections.class);
                break;
            case 16:
                ItemType = new Intent(this, Spells.class);
                break;
            case 17:
                ItemType = new Intent(this, StartingEquipment.class);
                break;
            case 18:
                ItemType = new Intent(this, Subclasses.class);
                break;
            case 19:
                ItemType = new Intent(this, Subraces.class);
                break;
            case 20:
                ItemType = new Intent(this, Traits.class);
                break;
            case 21:
                ItemType = new Intent(this, WeaponProperties.class);
                break;
            case 22:
                ItemType = new Intent(this, Skills.class);
                break;
        }
        return ItemType;
    }


    protected void HandleFeedBack(Context c) {
        String Subject = "Feedback for Natural Wonder";
        String Body = "(Write your message here)";
        Intent emailIntent = EmailIntentBuilder.from(c).to("workingwiggz.info@gmail.com").subject(Subject).body(Body).build();
        startActivity(emailIntent);
    }

    protected void HandleFeedBack(Context c, String ErrorMessage, String ErrorType) {
        String Subject = "Bug Report for Natural Wonder within " + ErrorType;
        String Body = "This was the Bug: " + ErrorMessage;
        Intent emailIntent = EmailIntentBuilder.from(c).to("workingwiggz.info@gmail.com").subject(Subject).body(Body).build();
        startActivity(emailIntent);
    }

    public int GetRandomInt(int Low, int High) {
        if (Low >= High) {
            throw new IllegalArgumentException("High end of range must be higher than lower end");
        }
        return ThreadLocalRandom.current().nextInt((High - Low) + 1) + Low;
    }

}
