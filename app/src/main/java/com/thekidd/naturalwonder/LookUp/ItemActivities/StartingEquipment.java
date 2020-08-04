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

public class StartingEquipment extends BasicItemActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_equipment);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TextView CN = findViewById(R.id.CNText);
        ListView SE = findViewById(R.id.SEList);
        try {
        String a = ItemData.getJSONObject("class").getString("name");
        JSONArray b = ItemData.getJSONArray("starting_equipment");
        JSONArray d = new JSONArray();
        for(int i =0;i<b.length();i++){
            JSONObject c = b.getJSONObject(i).getJSONObject("item");
            d.put(c);
        }

        int count = ItemData.getInt("choices_to_make");
        for(int i =1;i<count+1;i++){
            JSONArray aa = ItemData.getJSONArray("choice_"+i);
            for(int j =0;j<aa.length();j++){
                JSONArray aaa = aa.getJSONObject(j).getJSONArray("from");
                for(int k =0;k<aaa.length();k++){
                    JSONObject aaaa = aaa.getJSONObject(k).getJSONObject("item");
                    d.put(aaaa);
                }
            }
        }


        CN.setText(a);
        PopulateLists(SE,d);
    } catch (JSONException e) {
        e.printStackTrace();
        ErrorHandle(e,this);
    }
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
