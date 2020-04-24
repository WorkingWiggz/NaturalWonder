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
import org.json.JSONObject;

public class Spells extends BasicItemActivity {
    TextView NameText,DescText,HigherLevel,RangeText,CompText,MatsText,RitText,
    DurText,ConcText,CastText,LevelText;
    ListView SchoolList,ClassesList,SubClassesList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);

        NameText = findViewById(R.id.NameText);
        DescText = findViewById(R.id.DescText);
        HigherLevel = findViewById(R.id.HigherLevel);
        RangeText = findViewById(R.id.RangeText);
        CompText = findViewById(R.id.CompText);
        MatsText = findViewById(R.id.MatsText);
        RitText = findViewById(R.id.RitText);
        DurText = findViewById(R.id.DurText);
        ConcText = findViewById(R.id.ConcText);
        CastText = findViewById(R.id.CastText);
        LevelText = findViewById(R.id.LevelText);
        SchoolList = findViewById(R.id.SchoolList);
        ClassesList = findViewById(R.id.ClassesList);
        SubClassesList = findViewById(R.id.SubclassList);

        try {
            String a = ItemData.getString("name");
            String b = ItemData.getJSONArray("desc").getString(0);
            String c;
            if(ItemData.has("higher_level")){
                 c = ItemData.getJSONArray("higher_level").getString(0);
            } else {
                 c = "N/a";
            }

            String d;
            if(ItemData.has("")){
                d = ItemData.getString("range");
            } else {
                d = "N/a";
            }
            String e ="";
            if(ItemData.has("components")){
                JSONArray ee = ItemData.getJSONArray("components");

                for(int ii =0;ii<ee.length();ii++){
                    if(ii != ee.length()-1){
                        e = e + ee.getString(ii);
                    } else {
                        e = e+ ee.getString(ii) + " , ";
                    }
                }
            }

            String f;
            if(ItemData.has("material")){
                f = ItemData.getString("material");
            } else {
                f = "N/a";
            }

            String g;
                if(ItemData.has("ritual")){
                    g = ItemData.getString("ritual");
                } else {
                    g = "N/a";
                }

            String h;
                if(ItemData.has("duration")){
                    h = ItemData.getString("duration");
                } else {
                    h = "N/a";
                }
            String i;
                if(ItemData.has("casting_time")){
                    i = ItemData.getString("casting_time");
                } else {
                    i = "";
                }
                String z;
                if(ItemData.has("concentration")){
                    z= ItemData.getString("concentration");
                }  else {
                    z= "N/a";
                }
                String j = "Required Level: " +ItemData.getString("level");


            JSONObject kk = ItemData.getJSONObject("school");
            JSONArray k = new JSONArray();
            k.put(kk);
            JSONArray l = ItemData.getJSONArray("classes");
            JSONArray m = ItemData.getJSONArray("subclasses");


            NameText.setText(a);
            DescText.setText(b);
            HigherLevel.setText(c);
            RangeText.setText(d);
            CompText.setText(e);
            MatsText.setText(f);
            RitText.setText(g);
            DurText.setText(h);
            CastText.setText(i);
            LevelText.setText(j);
            PopulateLists(SchoolList,k);
            PopulateLists(ClassesList,l);
            PopulateLists(SubClassesList,m);
            ConcText.setText(z);
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }

        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
