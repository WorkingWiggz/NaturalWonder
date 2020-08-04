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
import org.json.JSONObject;

public class Skills extends BasicItemActivity {

    TextView Title,Desc;
    ListView Effected;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        Title  = findViewById(R.id.TitleText);
        Desc = findViewById(R.id.DescText);
        Effected = findViewById(R.id.EffectedStatList);

        try{
            String a = ItemData.getString("name");
            String b = ItemData.getJSONArray("desc").getString(0);
            Title.setText(a);
            Desc.setText(b);
            JSONObject c = ItemData.getJSONObject("ability_score");
            JSONArray d = new JSONArray();
            d.put(c);
            PopulateLists(Effected,d);
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
