package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONException;

public class Conditions extends BasicItemActivity {
    TextView Title, Desc;
    Button BackButt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conditions);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        Title = findViewById(R.id.ConName);
        Desc = findViewById(R.id.DescText);
        try {
        Title.setText(ItemData.getString("name"));
        String a ="";
        String NL = System.getProperty("line.separator");
        for(int i = 0;i<ItemData.getJSONArray("desc").length();i++){
                String b = ItemData.getJSONArray("desc").getString(i);
                if(i>0){
                    a =a+NL+b;
                } else{
                    a=b;
                }
        }
        Desc.setText(a);
    } catch (JSONException e) {
        e.printStackTrace();
        ErrorHandle(e,this);
    }

        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
