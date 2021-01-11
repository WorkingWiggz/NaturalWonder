package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.LookUp.CustomListAdapter;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AbilityScores extends BasicItemActivity {
    TextView ShortName,FullName,DescText,SkillsTitle;
    Button BackButt;
    ListView Skills;
    CustomListAdapter ToListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ability_scores);
        ShortName = findViewById(R.id.ShortName);
        FullName = findViewById(R.id.FullName);
        DescText = findViewById(R.id.DescText);
        SkillsTitle = findViewById(R.id.SkillsTitle);
        BackButt = findViewById(R.id.BackButt);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        Skills =  findViewById(R.id.SkillsList);


        Thread t = new Thread() {
            @Override
            public void run() {
                while (true){
                    if(ItemData != null){
                        try {
                            SortDataToItems();
                        } catch (JSONException e) {
                            ErrorHandle(e,getApplicationContext());
                        }
                        break;
                    }
                }
            }
        };
        t.start();
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);

        }

    private void SortDataToItems() throws JSONException {
        ShortName.setText(ItemData.getString("name"));
        FullName.setText(ItemData.getString("full_name"));
        JSONArray desc = ItemData.getJSONArray("desc");
        String b="";
        String NL =System.getProperty("line.separator");
        for(int i = 0;i<desc.length();i++){
            String a = desc.getString(i);
            if(i>0){
                b = b+NL+NL+a;
            }else {
                b = a;
            }
        }
        DescText.setText(b);
        String c = "Related skills:";
        SkillsTitle.setText(c);
        final JSONArray skills = ItemData.getJSONArray("skills");
        final ArrayList<String> SkillName = new ArrayList<String>();
        for(int i =0;i<skills.length();i++){
            JSONObject a =skills.getJSONObject(i);
            SkillName.add(a.getString("name"));
        }
        ToListAdapter = new CustomListAdapter(this,SkillName);
        Skills.setAdapter(ToListAdapter);
        Skills.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OnClickHandleList(skills,position);
            }
        });

    }
}
