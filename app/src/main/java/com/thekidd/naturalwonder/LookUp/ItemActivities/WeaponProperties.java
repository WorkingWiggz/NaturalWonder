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


public class WeaponProperties extends BasicItemActivity{
    TextView Title, DescText;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_properties);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        Title = findViewById(R.id.TitleText);
        DescText = findViewById(R.id.DescText);
        try{
        String a = ItemData.getString("name");
        JSONArray b = ItemData.getJSONArray("desc");
        String x="";
        for(int i =0;i<b.length();i++){
            String c = b.getString(i);
            x=x+c+System.getProperty("line.separator");
        }
        Title.setText(a);
        DescText.setText(x);
    } catch (JSONException e) {
            e.printStackTrace();
        }

        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}