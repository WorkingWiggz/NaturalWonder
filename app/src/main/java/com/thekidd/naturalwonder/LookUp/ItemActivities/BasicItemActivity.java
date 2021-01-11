package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.ErrorPage;
import com.thekidd.naturalwonder.LookUp.CustomListAdapter;
import com.thekidd.naturalwonder.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class BasicItemActivity extends BaseNWActivity {
    JSONObject ItemData;
    ArrayList<String> JSONData = new ArrayList<>();
    ArrayList<String> ActivityList = new ArrayList();
    int CurrentPageCount;
    String Cat;
    String ItemSelected;
    Class<?> PreviousActivity;
    JSONObject hold;
    ArrayList<String> TopMenu = new ArrayList<>();
    RequestQueue queue;
    String BaseURL = "https://dnd5eapi.co";
    Button BackButt;

    protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        try {
            if(getIntent().getBooleanExtra("Init",false)){
                JSONData.clear();
                ActivityList.clear();
                Cat = getIntent().getStringExtra("ItemCategory");
                ItemSelected = getIntent().getStringExtra("SelectedItem");
                SortItemData();
                ActivityList.add(getIntent().getStringExtra("PreviousActivity"));
                PreviousActivity = Class.forName(getIntent().getStringExtra("PreviousActivity"));
            } else {
                ConvertExtras();
                ItemData = new JSONObject(JSONData.get(CurrentPageCount));
                PreviousActivity = Class.forName(ActivityList.get(CurrentPageCount));
            }
        } catch (Exception e) {
            ErrorHandle(e,getApplicationContext());
        }
    }

    private void SortItemData() throws UnsupportedEncodingException, JSONException {
        String query = "{"+Cat+"(filter:{full_name:\""+ItemSelected+"\"}){name}}";
        String URL = BaseURL + "/graphql?query="+ URLEncoder.encode(query, "UTF-8");
        StringRequest sr = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject shaver = new JSONObject(response);
                    ItemData = shaver.getJSONObject("data").getJSONObject(Cat);
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e,getApplicationContext());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                ErrorHandle(error,getApplicationContext());
            }
        });

        JSONObject a = HandleRequest(sr,queue);
        if(!(a == null)){
            ItemData = a;
        }
    }


    public void GetItem(final String url){
        final String s = BaseURL + url;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    hold = new JSONObject(response);
                    processJSON(url);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        StringRequest GetTopMenus = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject MenuData = new JSONObject(response);
                    for(int i =0; i<MenuData.names().length();i++){
                        TopMenu.add(MenuData.names().getString(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorHandle(error,getApplicationContext());
            }
        });


        queue.add(stringRequest);
        queue.add(GetTopMenus);
    }
    private void processJSON(String Lasturl) throws JSONException {
        String a = Lasturl.substring(5);
        String[] b = a.split("/");
        String c = b[0];
        for(int i =0;i<TopMenu.size();i++){
            Boolean d = TopMenu.get(i).equals(c);
            if(d){
                ShowItem(i);
                break;
            }
        }
    }

    private void ShowItem(int a) {
        Intent ItemType = new Intent(this, ErrorPage.class);
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
        StartActivity(ItemType);
    }

   public void  ShowError(Exception e){
       Intent error = new Intent(this, ErrorPage.class);
       startActivity(error);
   }

   public void OnClickHandleList(JSONArray items,int pos){
       try {
           JSONObject c = items.getJSONObject(pos);
           GetItem(c.getString("url"));
       } catch (JSONException e) {
           e.printStackTrace();
           ShowError(e);
       }
   }

   public void PopulateLists(ListView a, final JSONArray Data){
       try {
           final ArrayList<String> AL = new ArrayList<>();
           for(int i =0; i<Data.length();i++){
               String s = Data.getJSONObject(i).getString("name");
               AL.add(s);
           }
           CustomListAdapter adap =new CustomListAdapter(this,AL);
           a.setAdapter(adap);
           a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   OnClickHandleList(Data,position);
               }
           });
       } catch (JSONException e) {
           e.printStackTrace();
           ErrorHandle(e,this);
       }
   }

   public void SortBackButt(final Button backButt){
        backButt.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                GotoPreviousActivity();
           }
       });
   }

   public void GotoPreviousActivity(){
       Intent PrevAct = new Intent(this, PreviousActivity);
       PrevAct.putExtra("IsAPreviousActivity",true);
       StartActivity(PrevAct);
    }

    public void StartActivity(Intent PassedIntent){
        if (PassedIntent.getBooleanExtra("IsAPreviousActivity",false)) {
            GoingBack(PassedIntent);
        } else {
            GoingForward(PassedIntent);
        }
        startActivity(PassedIntent);
    }

    private void GoingBack(Intent passedIntent) {
        if(ActivityList.size() >= 1){
            ActivityList.remove(CurrentPageCount);
        }
        if(JSONData.size() >= 1){
            JSONData.remove(CurrentPageCount);
        }


        CurrentPageCount--;
        String[] a = JSONData.toArray(new String[0]);
        String[] b = ActivityList.toArray(new String[0]);
        passedIntent.putExtra("JSONDataArray",a);
        passedIntent.putExtra("ActivityListArray",b);
        passedIntent.putExtra("CurrentCount",CurrentPageCount);
    }

    private void GoingForward(Intent passedIntent) {
        if(!JSONData.contains(ItemData.toString())){
            JSONData.add(ItemData.toString());
        }

        ActivityList.add(getClass().toString().split(" ")[1]);
        CurrentPageCount++;
        String[] a = JSONData.toArray(new String[0]);
        String[] b = ActivityList.toArray(new String[0]);
        passedIntent.putExtra("JSONDataArray",a);
        passedIntent.putExtra("ActivityListArray",b);
        passedIntent.putExtra("CurrentCount",CurrentPageCount);
    }

    public void MenuButtonHandle(final Button b){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(b.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }

    public void ConvertExtras() throws JSONException {
      JSONData.clear();
      ActivityList.clear();
      if(getIntent().getStringArrayExtra("JSONDataArray") != null){
          Collections.addAll(JSONData,getIntent().getStringArrayExtra("JSONDataArray"));
      }
      if(getIntent().getStringArrayExtra("ActivityListArray") != null){
          Collections.addAll(ActivityList,getIntent().getStringArrayExtra("ActivityListArray"));
      }
          CurrentPageCount = getIntent().getIntExtra("CurrentCount",0);

      if(getIntent().getStringExtra("ItemData") != null){
          if(!JSONData.contains(getIntent().getStringExtra("ItemData"))){
              JSONData.add(getIntent().getStringExtra("ItemData"));
          }
          ItemData = new JSONObject(JSONData.get(CurrentPageCount));
      }
    }

}

