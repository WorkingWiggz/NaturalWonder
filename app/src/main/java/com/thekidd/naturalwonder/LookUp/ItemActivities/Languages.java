package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Languages extends BasicItemActivity {
    TextView TitleText,TypeText,TypicalSpeakerText,ScriptText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleText = findViewById(R.id.TitleText);
        TypeText = findViewById(R.id.TypeText);
        TypicalSpeakerText = findViewById(R.id.TypicalSpeakerText);
        ScriptText = findViewById(R.id.ScriptText);

        try {
            String a = ItemData.getString("name");
            String b = ItemData.getString("type");
            String c = ItemData.getString("script");

            TitleText.setText(a);
            TypeText.setText(b);
            ScriptText.setText(c);
            JSONArray x = ItemData.getJSONArray("typical_speakers");
            String d ="";
            String NL = System.lineSeparator();
            for(int i =0;i<x.length();i++){
                String g =x.getString(i);
                d = d+g+NL;
            }
            TypicalSpeakerText.setText(d);
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
