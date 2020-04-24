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

public class Subraces extends BasicItemActivity {

    TextView NameText,BRText,DescText;
    ListView AbBoList,StartProList,LangList,RacTraList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subraces);

        NameText = findViewById(R.id.NameText);
        BRText = findViewById(R.id.BRText);
        DescText = findViewById(R.id.DescText);

        AbBoList = findViewById(R.id.AbBoList);
        StartProList = findViewById(R.id.StartProList);
        LangList = findViewById(R.id.LangList);
        RacTraList = findViewById(R.id.RacTraList);

        try {
            String a = ItemData.getString("name");
            String b = ItemData.getJSONObject("race").getString("name");
            String c = ItemData.getString("desc");
        NameText.setText(a);
        BRText.setText(b);
        DescText.setText(c);
            JSONArray d = ItemData.getJSONArray("ability_bonuses");
            JSONArray e = ItemData.getJSONArray("starting_proficiencies");
            JSONArray f = ItemData.getJSONArray("languages");
            JSONArray g = ItemData.getJSONArray("racial_traits");
        PopulateLists(AbBoList,d);
        PopulateLists(StartProList,e);
        PopulateLists(LangList,f);
        PopulateLists(RacTraList,g);

    } catch (JSONException e) {
        e.printStackTrace();
        ErrorHandle(e,this);
    }

        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
