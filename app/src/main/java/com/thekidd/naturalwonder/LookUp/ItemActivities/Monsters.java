package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.LookUp.CustomListAdapter;
import com.thekidd.naturalwonder.LookUp.NameDescArrayAdapter;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class Monsters extends BasicItemActivity {

    TextView NameText,SizeText,TypeText,SubTypeText,AlignmentText,ACText,HPText,HDText,SpeedText,StrText,DexText,ConText,IntText,WisText,
            ChaText,LangText,ChallRText,DmgImmText,DmgResText,DmgVunText;
    ListView ProfList,CondImmList,SensesList,SpeAbList,ActionList,LegActList;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        NameText = findViewById(R.id.DescText);
        SizeText = findViewById(R.id.SizeText);
        TypeText = findViewById(R.id.TypeText);
        SubTypeText = findViewById(R.id.SubTypeText);
        AlignmentText = findViewById(R.id.AlignmentText);
        ACText = findViewById(R.id.ACText);
        HPText = findViewById(R.id.HPText);
        HDText = findViewById(R.id.HDText);
        SpeedText = findViewById(R.id.SpeedText);
        StrText = findViewById(R.id.StrText);
        DexText = findViewById(R.id.DexText);
        ConText = findViewById(R.id.ConText);
        IntText = findViewById(R.id.IntText);
        WisText = findViewById(R.id.WisText);
        ChaText = findViewById(R.id.ChaText);
        LangText = findViewById(R.id.LangText);
        ChallRText = findViewById(R.id.ChallRText);
        ProfList = findViewById(R.id.ProfList);
        DmgVunText = findViewById(R.id.DmgVunText);
        DmgResText = findViewById(R.id.DmgResText);
        DmgImmText = findViewById(R.id.DmgImmText);
        CondImmList = findViewById(R.id.CondImmList);
        SensesList = findViewById(R.id.SensesList);
        SpeAbList = findViewById(R.id.SpeAbList);
        ActionList = findViewById(R.id.ActionList);
        LegActList = findViewById(R.id.LegActList);


        try{
            String a = ItemData.getString("name");
            String b = ItemData.getString("size");
            String c = ItemData.getString("type");
            String d = ItemData.getString("subtype");
            String e = ItemData.getString("alignment");
            String f = ItemData.getString("armor_class");
            String g = ItemData.getString("hit_points");
            String h = ItemData.getString("hit_dice");
            String i = ItemData.getString("strength");
            String j = ItemData.getString("dexterity");
            String k = ItemData.getString("constitution");
            String l = ItemData.getString("intelligence");
            String m = ItemData.getString("wisdom");
            String n = ItemData.getString("charisma");
            String o = ItemData.getString("languages");
            String p = ItemData.getString("challenge_rating");



            final JSONArray q = ItemData.getJSONArray("proficiencies");
            ArrayList<String> aa = new ArrayList<>();
            for(int ii=0;ii<q.length();ii++){
                String hold;
                JSONObject JO = q.getJSONObject(ii);
                hold = JO.getString("name") + "   " + JO.getString("value");
                aa.add(hold);
            }
            CustomListAdapter adap = new CustomListAdapter(this,aa);
            ProfList.setAdapter(adap);
            ProfList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    OnClickHandleList(q,position);
                }
            });
            String LineSep = System.lineSeparator();
            final JSONArray r = ItemData.getJSONArray("damage_vulnerabilities");
            if(r.length() != 0){
                String rr = "";
                for(int ii =0;ii<r.length();ii++){
                    String hold = r.getString(ii);
                    if(ii==0){
                        rr = hold + ", " + LineSep;
                    } else if(ii==r.length()-1){
                        rr = rr + hold;
                    } else {
                        rr = rr + hold + ", "+LineSep;
                    }
                }
                DmgVunText.setText(rr);
            } else {
                DmgVunText.setText("N/a");
            }

            final JSONArray s = ItemData.getJSONArray("damage_resistances");

           if(s.length() != 0){
               String ss = "";
               for(int ii =0;ii<s.length();ii++){
                   String hold = s.getString(ii);
                   if(ii==0){
                       ss = hold + ", " + LineSep;
                   } else if(ii==s.length()-1){
                       ss = ss + hold;
                   } else {
                       ss = ss + hold + ", "+LineSep;
                   }
               }
               DmgResText.setText(ss);
           } else {
               DmgResText.setText("N/a");
           }

           final JSONArray t = ItemData.getJSONArray("damage_immunities");
           if(t.length() != 0){
               String tt = "";
               for(int ii =0;ii<t.length();ii++){
                   String hold = t.getString(ii);
                   if(ii==s.length()-1){
                       tt = tt + hold;
                   } else if(ii==0){
                       tt = hold + LineSep;
                   }   else {
                       tt = tt + hold + LineSep;
                   }
               }
               DmgImmText.setText(tt);
           } else {
               DmgImmText.setText("N/a");
           }

            final JSONArray u = ItemData.getJSONArray("condition_immunities");
            PopulateLists(CondImmList,u);

            JSONObject v = ItemData.getJSONObject("senses");
            Iterator<String> keys = v.keys();
            ArrayList<String> AL = new ArrayList<>();
            while(keys.hasNext()){
                String key = keys.next();
                v.get(key);
                String hold = key + " " + v.getString(key);
                AL.add(hold);
            }
            CustomListAdapter CLA = new CustomListAdapter(this,AL);
            SensesList.setAdapter(CLA);

            ArrayList<String> Names = new ArrayList<>();
            ArrayList<String> Descs = new ArrayList<>();

            if(ItemData.has("special_abilities")){
                JSONArray w = ItemData.getJSONArray("special_abilities");
                if(w.getString(0) != null){
                    for(int ii =0;ii<w.length();ii++){
                        JSONObject JO = w.getJSONObject(ii);
                        Names.add(JO.getString("name"));
                        Descs.add(JO.getString("desc"));
                    }
                    NameDescArrayAdapter NDAA = new NameDescArrayAdapter(this,Names,Descs);
                    SpeAbList.setAdapter(NDAA);
                } else{
                    Names.add("N/a");
                    Descs.add("N/a");
                }
            }


            if(ItemData.has("actions")){
                JSONArray x = ItemData.getJSONArray("actions");
                Names.clear();
                Descs.clear();
                if(x.getString(0)!=null){
                    for(int ii =0;ii<x.length();ii++){
                        JSONObject JO = x.getJSONObject(ii);
                        Names.add(JO.getString("name"));
                        Descs.add(JO.getString("desc"));
                    }
                } else{
                    Names.add("N/a");
                    Descs.add("N/a");
                }
            }

            if(ItemData.has("legendary_actions")){
                JSONArray y = ItemData.getJSONArray("legendary_actions");
                Names.clear();
                Descs.clear();
                if(y.getString(0)!=null){
                    for(int ii=0;ii<y.length();ii++){
                        JSONObject JO = y.getJSONObject(ii);
                        Names.add(JO.getString("name"));
                        Descs.add(JO.getString("desc"));
                    }
                } else {
                    Names.add("N/a");
                    Descs.add("N/a");
                }
            }

            String z ="";
            JSONObject zz = ItemData.getJSONObject("speed");
            Iterator<String> Datakeys = zz.keys();
            String NL = System.lineSeparator();
            while(Datakeys.hasNext()) {
                String key = Datakeys.next();
                zz.get(key);
                z = z + key + " " + zz.getString(key)+ NL;
            }


            NameText.setText(a);
            SizeText.setText(b);
            TypeText.setText(c);
            SubTypeText.setText(d);
            AlignmentText.setText(e);
            ACText.setText(f);
            HPText.setText(g);
            HDText.setText(h);
            StrText.setText(i);
            DexText.setText(j);
            ConText.setText(k);
            IntText.setText(l);
            WisText.setText(m);
            ChaText.setText(n);
            LangText.setText(o);
            ChallRText.setText(p);
            SpeedText.setText(z);
        } catch (Exception e){
            e.printStackTrace();
            ErrorHandle(e,this);
        }



        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }
}
