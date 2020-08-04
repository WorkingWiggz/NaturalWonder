package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Proficiencies extends BasicItemActivity {
    TextView TitleText,TypeText,RacesText,ClassesText;
    ListView ClassList,RaceList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleText = findViewById(R.id.TitleText);
        TypeText = findViewById(R.id.TypeText);
        RacesText = findViewById(R.id.RacesText);
        ClassesText = findViewById(R.id.ClassText);
        ClassList  = findViewById(R.id.ClassList);
        RaceList = findViewById(R.id.RaceList);

        try{
            String a = ItemData.getString("name");
            TitleText.setText(a);

            String b = ItemData.getString("type");
            TypeText.setText("Type:" + b);

            JSONArray c = ItemData.getJSONArray("classes");
            String NL = System.getProperty("line.separator");
            if(c.length()==0){
                String e = "N/a";
                ClassesText.setText(e);
            } else {
                ClassesText.setText("");
                PopulateLists(ClassList,c);
            }

            JSONArray d = ItemData.getJSONArray("races");
            if(d.length()==0){
                String e = "N/a";
                RacesText.setText(e);
            } else {
                ClassesText.setText("");
                PopulateLists(RaceList,d);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
