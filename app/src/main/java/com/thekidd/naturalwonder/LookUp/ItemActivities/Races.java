package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Races extends BasicItemActivity {

    TextView NameText,SpeedText,AlignText,AgeText,sizeText,SizeDescText, TypeText,LangDescText;
    ListView AbBoList,StartProList,LangList,TraitsList, TraitOptList,SubRacesList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);

        NameText = findViewById(R.id.DescText);
        SpeedText = findViewById(R.id.SpeedText);
        AlignText = findViewById(R.id.AlignmentText);
        AgeText = findViewById(R.id.AgeText);
        sizeText = findViewById(R.id.sizeText);
        SizeDescText = findViewById(R.id.SizeDescText);
        LangDescText = findViewById(R.id.LangDescText);
        AbBoList = findViewById(R.id.AbBoList);
        StartProList = findViewById(R.id.StartProList);
        LangList = findViewById(R.id.LangList);
        TraitsList = findViewById(R.id.TraitsList);
        TraitOptList = findViewById(R.id.TraitOptList);
        SubRacesList = findViewById(R.id.SubRacesList);
        try {
            String a = ItemData.getString("name");
            String b = ItemData.getString("speed");
            String c = ItemData.getString("alignment");
            String d = ItemData.getString("age");
            String e = ItemData.getString("size");
            String f = ItemData.getString("size_description");
            String g = ItemData.getString("language_desc");
            JSONArray i = ItemData.getJSONArray("starting_proficiencies");
            JSONArray j = ItemData.getJSONArray("languages");
            JSONArray k = ItemData.getJSONArray("traits");
            if(ItemData.has("trait_options")){
                JSONArray l = ItemData.getJSONObject("trait_options").getJSONArray("from");
                PopulateLists(TraitOptList,l);
            }

            JSONArray m = ItemData.getJSONArray("subraces");
            JSONArray n = ItemData.getJSONArray("ability_bonuses");



            NameText.setText(a);
            SpeedText.setText(b);
            AlignText.setText(c);
            AgeText.setText(d);
            sizeText.setText(e);
            SizeDescText.setText(f);
            LangDescText.setText(g);
            PopulateLists(AbBoList,n);
            PopulateLists(StartProList,i);
            PopulateLists(LangList,j);
            PopulateLists(TraitsList,k);

            PopulateLists(SubRacesList,m);



        } catch (JSONException ex) {
            ex.printStackTrace();
            ErrorHandle(ex,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
