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

import java.util.ArrayList;
import java.util.Iterator;

public class Equipment extends BasicItemActivity {

    TextView TitleName,EqCat,GearCat,Cost,Weight,Desc;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleName = findViewById(R.id.TitleName);
        EqCat = findViewById(R.id.EqCat);
        Cost = findViewById(R.id.Cost);
        Weight = findViewById(R.id.Weight);
        Desc = findViewById(R.id.Desc);

        try{
        String a = ItemData.getString("name");
        TitleName.setText(a);
        String b = "(" + ItemData.getJSONObject("equipment_category").getString("name")+")";
        EqCat.setText(b);
            ArrayList<String> keys = new ArrayList<>();
            Iterator<String> ItemKeys =ItemData.keys();
         while(ItemKeys.hasNext()){
             keys.add(ItemKeys.next());
         }
        String d = "Cost: "+ ItemData.getJSONObject("cost").getString("quantity") + " "+ ItemData.getJSONObject("cost").getString("unit");
        Cost.setText(d);


      if(ItemData.has("weight")){
          String e = "Weight: "+ItemData.getString("weight");
          Weight.setText(e);
      } else {
          String e = "N/a";
          Weight.setText(e);
      }


        if(ItemData.has("desc")){
            String f = ItemData.getJSONArray("desc").getString(0);
            Desc.setText(f);
        } else{
           String f = "This item has no recorded description for this item. Try to google for this perhaps?";
           Desc.setText(f);
        }
        } catch (JSONException e){
            e.printStackTrace();
            ErrorHandle(e,this);
        }

        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
