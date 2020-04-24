package com.thekidd.naturalwonder.LookUp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.LookUp.ItemActivities.AbilityScores;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Classes;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Conditions;
import com.thekidd.naturalwonder.LookUp.ItemActivities.DamageTypes;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Equipment;
import com.thekidd.naturalwonder.LookUp.ItemActivities.EquipmentCategories;
import com.thekidd.naturalwonder.ErrorPage;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Features;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Languages;
import com.thekidd.naturalwonder.LookUp.ItemActivities.MagicSchools;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Monsters;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Proficiencies;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Races;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Skills;
import com.thekidd.naturalwonder.LookUp.ItemActivities.SpellCasting;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Spells;
import com.thekidd.naturalwonder.LookUp.ItemActivities.StartingEquipment;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Subclasses;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Subraces;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Traits;
import com.thekidd.naturalwonder.LookUp.ItemActivities.WeaponProperties;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LookingUpActivity extends BaseNWActivity {

    Button MenuButt;
    ListView listView;
    String BaseURL = "https://dnd5eapi.co";
    String TopMenuString = "/api/?";
    private RequestQueue queue;
    int Itemcount;
    CustomListAdapter toList;
    ArrayList<String> reshold,UrlHistory,ItemTypes;
    String Category;
    JSONObject  hold;
    Boolean TopMenu;
    TextView Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_up);
        listView = findViewById(R.id.ListView);
        Title = findViewById(R.id.TitleText);
        Title.setText("Main Menu");
        reshold = new ArrayList<String>();
        ItemTypes = new ArrayList<String>();
        MenuButt = findViewById(R.id.menubutt);
        queue = Volley.newRequestQueue(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(TopMenu){
                    Title.setText(reshold.get(position));
                    if (reshold.get(position).contains(" ")){
                        String a = reshold.get(position).replace(' ','-').toLowerCase();
                        try {
                            Category = a;
                            String b = hold.get(a).toString();
                            GetMenus(b);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                            String a = reshold.get(position).toLowerCase();
                            Category = a;
                        try {
                            String b = hold.get(a).toString();
                            GetMenus(b);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    if(reshold.get(position).contains("Back")){
                        Title.setText("Main Menu");
                        GetMenus(TopMenuString);
                    } else {
                        try {
                            JSONArray a = hold.getJSONArray("results");
                            JSONObject b = a.getJSONObject(position-1);
                            String c = b.getString("url");
                            GetMenus(c);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        //Initializes Menu

        try {
            hold = new JSONObject("{\n" +
                    "\t\"ability-scores\": \"/api/ability-scores\",\n" +
                    "\t\"classes\": \"/api/classes\",\n" +
                    "\t\"conditions\": \"/api/conditions\",\n" +
                    "\t\"damage-types\": \"/api/damage-types\",\n" +
                    "\t\"equipment-categories\": \"/api/equipment-categories\",\n" +
                    "\t\"equipment\": \"/api/equipment\",\n" +
                    "\t\"features\": \"/api/features\",\n" +
                    "\t\"languages\": \"/api/languages\",\n" +
                    "\t\"magic-schools\": \"/api/magic-schools\",\n" +
                    "\t\"monsters\": \"/api/monsters\",\n" +
                    "\t\"proficiencies\": \"/api/proficiencies\",\n" +
                    "\t\"races\": \"/api/races\",\n" +
                    "\t\"skills\": \"/api/skills\",\n" +
                    "\t\"spellcasting\": \"/api/spellcasting\",\n" +
                    "\t\"spells\": \"/api/spells\",\n" +
                    "\t\"starting-equipment\": \"/api/starting-equipment\",\n" +
                    "\t\"subclasses\": \"/api/subclasses\",\n" +
                    "\t\"subraces\": \"/api/subraces\",\n" +
                    "\t\"traits\": \"/api/traits\",\n" +
                    "\t\"weapon-properties\": \"/api/weapon-properties\"\n" +
                    "}");
            processJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final Intent MenuBack = new Intent(this, MainActivity.class);

        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });
    }

        private void UpdateView() {
            toList = new CustomListAdapter(this,reshold);
            listView.setAdapter(toList);
        }



    private void GetMenus(String url){
        String s = BaseURL + url;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    hold = new JSONObject(response);
                    processJSON();
                } catch (JSONException e) {
                    e.printStackTrace();
                    ShowError();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ShowError();
            }
        });
        queue.add(stringRequest);
    }

    private void processJSON() throws JSONException {
       reshold.clear();
       Boolean Item = false ,SubMenu = false;

       for(int i =0;i<hold.names().length();i++){
           String a =hold.names().get(i).toString();

           if(a.contains("_id")){
               Item = true;
               SubMenu = false;
           } else if (a.contains("count")){
               Item = false;
               SubMenu = true;
           }
       }


       if(Item){
            ShowItem();
       } else if(SubMenu){
           Boolean u = hold.has("class");
           if(u){
                TopMenu = false;
                reshold.add("<---Back--->");
               JSONArray a = hold.getJSONArray("results");
               for(int i = 0; i<a.length();i++){
                   String b;
                   if(a.getJSONObject(i).has("name")){
                      b = a.getJSONObject(i).getString("name");
                   } else  {
                      b = a.getJSONObject(i).getString("class");
                   }
                   reshold.add(b);
               }
           } else {
               TopMenu = false;
               Itemcount = hold.getInt("count");
               reshold.add("<---Back--->");
               JSONArray a = hold.getJSONArray("results");
               for(int i = 0; i<a.length();i++){
                   String b;
                   if(a.getJSONObject(i).has("name")){
                       b =  a.getJSONObject(i).getString("name");
                   } else  {
                       b = a.getJSONObject(i).getString("class");
                   }
                   reshold.add(b);
               }
           }
           UpdateView();
        } else {
            TopMenu = true;
            if(ItemTypes.size() <= 0){
                for(int i = 0;i<hold.length();i++){
                    String a = hold.names().get(i).toString();
                    ItemTypes.add(a);
                }
            }

            for(int i = 0;i<hold.length();i++){
                String g = hold.names().get(i).toString();
                String k = Character.toUpperCase(g.charAt(0)) + g.substring(1);
                String j = k;
                String a = "";
                if(j.contains("-")) {
                    a = j.replace('-', ' ');
                    String[] b = a.split(" ");
                    String c = Character.toUpperCase(b[0].charAt(0))+ b[0].substring(1);
                    String d = Character.toUpperCase(b[1].charAt(0))+ b[1].substring(1);
                    a = c+" "+d;
                    reshold.add(a);
                } else {
                    reshold.add(j);
                }

            }
           UpdateView();
        }

    }

    private void ShowItem() {
        Intent ItemType = new Intent(this, ErrorPage.class);
        int a =-1;
        for(int i =0; i<ItemTypes.size();i++){
            String x = ItemTypes.get(i);
            Boolean CatCheck =(Category.equals(x));
            if(CatCheck){
                a = i;
            }
        }
        switch(a){
            case 0:
                ItemType = new Intent(this, AbilityScores.class);
                break;
            case 1:
                ItemType = new Intent(this, Classes.class);
                break;
            case 2:
                ItemType = new Intent(this, Conditions.class);
                break;
            case 3:
                ItemType = new Intent(this, DamageTypes.class);
                break;
            case 4:
                ItemType = new Intent(this, EquipmentCategories.class);
                break;
            case 5:
                ItemType = new Intent(this, Equipment.class);
            break;
            case 6:
                ItemType = new Intent(this, Features.class);
                break;
            case 7:
                ItemType = new Intent(this, Languages.class);
                break;
            case 8:
                ItemType = new Intent(this, MagicSchools.class);
                break;
            case 9:
                ItemType = new Intent(this, Monsters.class);
                break;
            case 10:
                ItemType = new Intent(this, Proficiencies.class);
                break;
            case 11:
                ItemType = new Intent(this, Races.class);
                break;
            case 12:
                ItemType = new Intent(this, Skills.class);
                break;
            case 13:
                ItemType = new Intent(this, SpellCasting.class);
                break;
            case 14:
                ItemType = new Intent(this, Spells.class);
                break;
            case 15:
                ItemType = new Intent(this, StartingEquipment.class);
                break;
            case 16:
                ItemType = new Intent(this, Subclasses.class);
                break;
            case 17:
                ItemType = new Intent(this, Subraces.class);
                break;
            case 18:
                ItemType = new Intent(this, Traits.class);
                break;
            case 19:
                ItemType = new Intent(this, WeaponProperties.class);
                break;
        }
        ItemType.putExtra("ItemData",hold.toString());
        startActivity(ItemType);
    }

    public void  ShowError(){
        Intent error = new Intent(this, ErrorPage.class);
        startActivity(error);
    }
}
