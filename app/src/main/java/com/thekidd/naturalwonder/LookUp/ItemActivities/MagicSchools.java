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

public class MagicSchools extends BasicItemActivity {
    TextView TitleText,DescText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_schools);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleText = findViewById(R.id.TitleText);
        DescText = findViewById(R.id.DescText);
        try{
            String a = ItemData.getString("name");
            TitleText.setText(a);

            String b = ItemData.getString("desc");
            DescText.setText(b);
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
