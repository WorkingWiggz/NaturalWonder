package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.android.volley.toolbox.Volley;
import com.thekidd.naturalwonder.LookUp.CustomListAdapter;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLClassLoader;
import java.util.ArrayList;

public class Classes extends BasicItemActivity {
    TextView ClassText,HitDice,ProfChoicesText,ProfClassText,SavingThrowsText,StartEquipText,SubclassesText,SpellCastText;
    ListView ProChoices,ProClassList,SubclassList,ClassLevelList,SavingThrowList,StartingEquipList,SpellcastList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        ClassText = findViewById(R.id.classText);
        HitDice = findViewById(R.id.hitDice);
        ProfClassText = findViewById(R.id.profClassText);
        ProfChoicesText = findViewById(R.id.profChoicesText);
        SavingThrowsText = findViewById(R.id.savingThrowsText);
        StartEquipText = findViewById(R.id.startEquipText);
        SubclassesText = findViewById(R.id.subclassesText);
        SpellCastText = findViewById(R.id.spellcastText);

        SpellcastList = findViewById(R.id.SList);
        ProChoices = findViewById(R.id.ProChoices);
        ProClassList = findViewById(R.id.ProClassList);
        SubclassList = findViewById(R.id.SubclassList);
        SavingThrowList = findViewById(R.id.SavingThrowList);
        StartingEquipList = findViewById(R.id.StartingEquipList);

        try {

            String se = ItemData.getJSONObject("starting_equipment").getString("class");
            ArrayList<String> StartEq = new ArrayList<>();
            StartEq.add(se);
            CustomListAdapter adapSE = new CustomListAdapter(this,StartEq);
            StartingEquipList.setAdapter(adapSE);




            if(ItemData.has("spellcasting")){
                String g ="Spells Usable by class";
                SpellCastText.setText(g);
                String SC = ItemData.getJSONObject("spellcasting").getString("class");
                ArrayList<String> SpellC = new ArrayList<>();
                SpellC.add(SC);
                CustomListAdapter adapSC = new CustomListAdapter(this,SpellC);
                SpellcastList.setAdapter(adapSC);
            } else{
                SpellCastText.setText("");
            }

            String d = ItemData.getString("name");
            ClassText.setText(d);
            String a = "1d"+ItemData.getString("hit_die")+" per level";
            HitDice.setText(a);
            String b ="Base Proficiencies";
            ProfClassText.setText(b);
            String c = "Selectable Proficiencies";
            ProfChoicesText.setText(c);

            String j = "Good Saving Throws";
            SavingThrowsText.setText(j);

            String s = "Starting Equipment";
            StartEquipText.setText(s);

            String Sub = "Possible Subclasses";
            SubclassesText.setText(Sub);

            JSONArray La = ItemData.getJSONArray("proficiencies");
            PopulateLists(ProClassList,La);

            JSONArray Lb = ItemData.getJSONArray("saving_throws");
            PopulateLists(SavingThrowList,Lb);

            JSONArray Lc = ItemData.getJSONArray("subclasses");
            PopulateLists(SubclassList,Lc);

            JSONArray Ld = ItemData.getJSONArray("proficiency_choices");
            ArrayList<String> Arr = new ArrayList<String>();
            JSONArray Jb = null;
            JSONObject Ja;

            for(int i =0;i<Ld.length();i++){
                Ja = Ld.getJSONObject(i);
                Jb = Ja.getJSONArray("from");
                for(int F =0;F<Jb.length();F++){
                    JSONObject x = Jb.getJSONObject(F);
                    String y = x.getString("name");
                    Arr.add(y);
                }
            }
            CustomListAdapter ALC = new CustomListAdapter(this,Arr);
            ProChoices.setAdapter(ALC);
            final JSONArray finalJb = Jb;
            ProChoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    OnClickHandleList(finalJb,position);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }


        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
        }
    }

