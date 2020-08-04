package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.LookUp.NameDescArrayAdapter;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SpellCasting extends BasicItemActivity {

    TextView NameText,LevelText;
    ListView SPABList, InfoList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_casting);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        try {
            NameText = findViewById(R.id.DescText);
            LevelText = findViewById(R.id.LevelText);
            SPABList = findViewById(R.id.SPABList);
            InfoList = findViewById(R.id.InfoList);

            String a = ItemData.getJSONObject("class").getString("name");
            String b = ItemData.getString("level");
            JSONArray c = new JSONArray();
            c.put(ItemData.getJSONObject("spellcasting_ability"));
            JSONArray d = ItemData.getJSONArray("info");
            NameText.setText(a);
            LevelText.setText(b);
            PopulateLists(SPABList,c);
            ArrayList<String> names = new ArrayList<>();
            ArrayList<String> descs = new ArrayList<>();
            for(int i =0;i<d.length();i++){
                JSONObject JO = d.getJSONObject(i);
                names.add(JO.getString("name"));
                descs.add(JO.getJSONArray("desc").getString(0));
            }
            NameDescArrayAdapter NDAA = new NameDescArrayAdapter(this,names,descs);
            InfoList.setAdapter(NDAA);

        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
