package com.thekidd.naturalwonder.LookUp.ItemActivities;

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

public class Traits extends BasicItemActivity {

    TextView Title,DescText;
    ListView Races,subclasses;

protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_traits);
    Button MenuButt = findViewById(R.id.MenuButt);
    MenuButtonHandle(MenuButt);
    Title = findViewById(R.id.TitleText);
    DescText = findViewById(R.id.DescText);

    Races = findViewById(R.id.RacesList);
    subclasses = findViewById(R.id.SubracesList);

    try{
        String a = ItemData.getString("name");
        Title.setText(a);
        String b = ItemData.getJSONArray("desc").getString(0);
        DescText.setText(b);
        JSONArray c = ItemData.getJSONArray("races");
        if(c.get(0) == null){

        } else {
            PopulateLists(Races,c);
        }
        JSONArray d = ItemData.getJSONArray("subraces");
        if(d.get(0) == null){

        } else {
            PopulateLists(subclasses,d);
        }


    } catch (JSONException e) {
        e.printStackTrace();
    }

    BackButt = findViewById(R.id.BackButt);
    SortBackButt(BackButt);
}
}
