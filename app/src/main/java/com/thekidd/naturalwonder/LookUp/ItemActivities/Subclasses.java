package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;

public class Subclasses extends BasicItemActivity {

    TextView CNameText,OGCLassText,FTitleText,DescText;
    ListView FeatList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subclasses);

        CNameText = findViewById(R.id.CNameText);
        OGCLassText = findViewById(R.id.OGCLassText);
        FTitleText = findViewById(R.id.FTitleText);
        DescText = findViewById(R.id.DescText);
        FeatList = findViewById(R.id.Feats);

        try{
            String a = ItemData.getString("name");
            String b = ItemData.getJSONObject("class").getString("name");
            String c = ItemData.getString("subclass_flavor");
            String d = ItemData.getJSONArray("desc").getString(0);
            JSONArray e = ItemData.getJSONArray("features");

            CNameText.setText(a);
            OGCLassText.setText(b);
            FTitleText.setText(c);
            DescText.setText(d);
            PopulateLists(FeatList,e);

        } catch (Exception e){
            e.printStackTrace();
            ErrorHandle(e,this);
        }



        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
