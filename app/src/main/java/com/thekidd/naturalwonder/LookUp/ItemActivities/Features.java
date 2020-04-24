package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONException;

public class Features extends BasicItemActivity {
    TextView Fname,Cname,Desc,RLvl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);

        Fname = findViewById(R.id.FeatName);
        Cname = findViewById(R.id.ClassName);
        Desc = findViewById(R.id.DescText);
        RLvl = findViewById(R.id.ReqLevel);

        try{
            Fname.setText(ItemData.getString("name"));
            Cname.setText(ItemData.getJSONObject("class").getString("name"));
            String c ="Required Level: "+ ItemData.getString("level");
            RLvl.setText(c);
            String a = "";
            String NL = System.getProperty("line.separator");
            for(int i = 0; i<ItemData.getJSONArray("desc").length();i++){
                String b = ItemData.getJSONArray("desc").getString(i);
                if(i>0){
                    a=a+NL+b;
                }else{
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
