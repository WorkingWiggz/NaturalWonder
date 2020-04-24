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
import org.json.JSONException;

public class EquipmentCategories extends BasicItemActivity {

    TextView TitleName;
    ListView EqList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_categories);


        TitleName = findViewById(R.id.TitleName);
        EqList = findViewById(R.id.EquipmentList);



        try {
            String a =ItemData.getString("name");
            TitleName.setText(a);
            JSONArray b = ItemData.getJSONArray("equipment");
            PopulateLists(EqList,b);
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }

        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
