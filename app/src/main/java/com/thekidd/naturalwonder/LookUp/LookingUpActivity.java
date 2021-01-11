package com.thekidd.naturalwonder.LookUp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.ErrorPage;
import com.thekidd.naturalwonder.LookUp.ItemActivities.AbilityScores;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Classes;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Conditions;
import com.thekidd.naturalwonder.LookUp.ItemActivities.DamageTypes;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Equipment;
import com.thekidd.naturalwonder.LookUp.ItemActivities.EquipmentCategories;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Features;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Languages;
import com.thekidd.naturalwonder.LookUp.ItemActivities.MagicSchools;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Monsters;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Proficiencies;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Races;
import com.thekidd.naturalwonder.LookUp.ItemActivities.RuleSections;
import com.thekidd.naturalwonder.LookUp.ItemActivities.Rules;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class LookingUpActivity extends BaseNWActivity {

    Button MenuButt;
    Toast delaytoast;
    int TimerSeconds = 3250;
    Toast Finisheddelaytoast;
    Boolean DelayToastTriggered = false;
    ListView listView;
    String SelectedItem="";
    private RequestQueue queue;
    HashMap<String,ArrayList> SchemaData = new HashMap<>();
    Thread SchemaDataPopulator = new Thread(){
        @Override
        public void run() {
            DoSchemaPopulate();
        }
    };
    String TopMenuUrl = "https://www.dnd5eapi.co/graphql?query={__type(name:%22Query%22){fields{name%20CheckType:type{kind}}}}";
    CustomListAdapter toList;
    ArrayList<String> reshold,ItemTypes,MenuItems,SchemaNames,SearchNames,TopMenuItems,SubMenuItems;
    String Category;g
    JSONObject  hold;
    Boolean requestdelay = false;
    Boolean TopMenu = true;
    TextView Title;
    Boolean SubMenu = false;
    Boolean Activity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_up);
        listView = findViewById(R.id.ListView);
        Title = findViewById(R.id.TitleText);
        Title.setText("Looking Up Menu");

        delaytoast = Toast.makeText(this,"Please wait a few seconds...",Toast.LENGTH_SHORT);
        Finisheddelaytoast = Toast.makeText(this,"Try pressing it now.",Toast.LENGTH_SHORT);
        reshold = new ArrayList<>();
        SubMenuItems= new ArrayList<>();
        ItemTypes = new ArrayList<>();
        TopMenuItems = new ArrayList<>();
        MenuItems = new ArrayList<>();
        SchemaNames = new ArrayList<>();
        SearchNames = new ArrayList<>();
        MenuButt = findViewById(R.id.menubutt);
        queue = Volley.newRequestQueue(this);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(TopMenu&&requestdelay){
                    DelayToastTriggered = true;
                    delaytoast.show();
                }else {
                    if(TopMenu){
                       // startTimer(TimerSeconds);
                        ToSubMenu(position);
                    } else if (SubMenu) {
                        ToItemActivity(position);
                    }
                }
            }
        });

        //Initializes Menu
        final StringRequest s = new StringRequest(Request.Method.GET, TopMenuUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject shaver = new JSONObject(response);
                    hold = shaver.getJSONObject("data").getJSONObject("__type");
                    SaveToLocalBackup(TopMenuUrl,hold);
                    SortMenuData();
                } catch (Exception e) {
                    ErrorHandle(e,getApplicationContext());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        try {
            hold = HandleRequest(s,queue);
            if(!(hold == null)){
                SortMenuData();
            }
        } catch (JSONException e) {
            ErrorHandle(e,this);
        }


        final Intent MenuBack = new Intent(this, MainActivity.class);

        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TopMenu){
                    startActivity(MenuBack);
                } else if (SubMenu){
                    try {
                        ToTopMenu();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        ErrorHandle(e,v.getContext());
                    }
                }
            }
        });
    }

    private void startTimer(final int TimerLimt) {
         requestdelay = true;
         CountDownTimer CDT = new CountDownTimer(TimerLimt, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                requestdelay = false;
                if(DelayToastTriggered){
                    Finisheddelaytoast.show();
                    DelayToastTriggered = false;
                }
            }
        }.start();
    }


    private void SortMenuData() throws JSONException {
        JSONArray fields = hold.getJSONArray("fields");
        for(int i =0;i<fields.length();i++){
           JSONObject CurrObj = fields.getJSONObject(i);
            if(CurrObj.getJSONObject("CheckType").getString("kind").equals("OBJECT")){
                String ItemName = CurrObj.getString("name");
                if(!SchemaNames.contains(ItemName)){
                    SchemaNames.add(ItemName);
                }
            } else if(CurrObj.getJSONObject("CheckType").getString("kind").equals("NON_NULL")){
                String ItemName = CurrObj.getString("name");
                if(!SearchNames.contains(ItemName)){
                    SearchNames.add(ItemName);
                }
                String newname = "";
                if(ItemName.equals("startingequipments")){
                    newname = "Starting Equipments";
                } else{
                    for(int j = 0;j<ItemName.toCharArray().length;j++){
                        if(j == 0){
                            newname = newname + ItemName.charAt(0);
                            newname = newname.toUpperCase();
                        } else {
                            char c = ItemName.charAt(j);
                            if(Character.isUpperCase(c)){
                                newname = newname + " "+ ItemName.charAt(j);
                            } else {
                                newname = newname + ItemName.charAt(j);
                            }
                        }
                    }}
                if(!TopMenuItems.contains(newname)){TopMenuItems.add(newname);}
            }
        }
        ToTopMenu();
    }




    private void ToTopMenu() throws JSONException {
        TopMenu = true;
        SubMenu = false;
        Activity = false;
        MenuButt.setText("Menu");
        Title.setText("Looking Up Menu");
        reshold.clear();
        MenuItems.clear();
        ItemTypes.clear();
        for(int i = 0;i<TopMenuItems.size();i++){
            MenuItems.add(TopMenuItems.get(i));
            ItemTypes.add(TopMenuItems.get(i));
            reshold.add(TopMenuItems.get(i));
        }
        UpdateView();
    }

    private void ToSubMenu(int position) {
        TopMenu = false;
        SubMenu = true;
        Activity = false;
        MenuButt.setText("Back");
        Category = TopMenuItems.get(position);
        try {
            GetMenus(SearchNames.get(position),TopMenuItems.get(position));
        } catch (JSONException | UnsupportedEncodingException e ) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }
    }

    private void ToItemActivity(int position){
        SelectedItem = SubMenuItems.get(position);
        ShowItem();
    }

    private void UpdateView() {
            toList = new CustomListAdapter(this,reshold);
            listView.setAdapter(toList);
        }



    private void GetMenus(String url, final String TitleString) throws JSONException, UnsupportedEncodingException {
        final String query = BuildQuery(url);
        final String s = BaseURL + "/graphql?query="+ query;


            StringRequest stringRequest = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject shaver = new JSONObject(response);
                    hold = shaver.getJSONObject("data");
                    SaveToLocalBackup(s,hold);
                    Title.setText(TitleString);
                    CheckData(TopMenuItems.indexOf(TitleString));
                } catch (Exception e) {
                    ErrorHandle(e,getApplicationContext());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorHandle(error,getApplicationContext());
            }
        });

            JSONObject testObject = HandleRequest(stringRequest, queue);
            if(!(testObject == null)){
                hold = testObject;
                Title.setText(TitleString);
                CheckData(TopMenuItems.indexOf(TitleString));
            }

        }




    private void CheckData(int selectedpos) throws JSONException{
        reshold.clear();
        SubMenuItems.clear();
        JSONArray data = hold.getJSONArray(SearchNames.get(selectedpos));
        switch (selectedpos){
            case 8:
                for(int i=0;i<data.length();i++){
                    String t = "";
                    t = data.getJSONObject(i).getJSONObject("class").getString("name");
                    if(!SubMenuItems.contains(t)){
                        SubMenuItems.add(t);
                        reshold.add(t);
                    }
                }
            break;
            case 17:
                for(int i = 0;i<data.length();i++){
                    String t ="";
                    t = data.getJSONObject(i).getJSONObject("class").getString("name");
                    SubMenuItems.add(t);
                    reshold.add(t);
                }
            break;
            default:
                for(int i = 0;i<data.length();i++){
                    String t = "";
                    try{
                        t = data.getJSONObject(i).getString("full_name");
                    } catch (JSONException e){
                        t = data.getJSONObject(i).getString("name");
                    }
                    SubMenuItems.add(t);
                    reshold.add(t);
                }
            break;
        }
        UpdateView();
    }


    protected String BuildQuery(String url) throws UnsupportedEncodingException {
        String query="";
        if(SearchNames.contains(url)){

            switch (SearchNames.indexOf(url)){
                case 0:
                    query = "{"+url+"{full_name}}";
                break;
                case 8:
                    query = "{"+url+"(limit:250){class{name} level}}";
                break;
                case 16:
                    query = "{"+url+"(limit:1000){name}}";
                break;
                case 17:
                    query = "{"+url+"{class{name}}}";
                break;
                default:
                    query = "{"+url+"{name}}";
                break;
            }
        }
        return URLEncoder.encode(query, "UTF-8");
    }




    private void ShowItem() {
        Intent ItemType = new Intent(this, ErrorPage.class);
        int a =-1;
        String PrevCategory="";
        for(int i =0; i<TopMenuItems.size();i++){
            String x = TopMenuItems.get(i);
            boolean CatCheck = (Category.equals(x));
            if(CatCheck){
                a = i;
                break;
            }
        }
        PrevCategory = TopMenuItems.get(a);

        switch(a){
            case 0:
                ItemType = new Intent(this, AbilityScores.class);
                break;
            case 2:
                ItemType = new Intent(this, Classes.class);
                break;
            case 1:
                ItemType = new Intent(this, Conditions.class);
                break;
            case 3:
                ItemType = new Intent(this, DamageTypes.class);
                break;
            case 5:
                ItemType = new Intent(this, EquipmentCategories.class);
                break;
            case 4:
                ItemType = new Intent(this, Equipment.class);
            break;
            case 6:
                ItemType = new Intent(this, Features.class);
                break;
            case 7:
                ItemType = new Intent(this, Languages.class);
                break;
            case 10:
                ItemType = new Intent(this, MagicSchools.class);
                break;
            case 11:
                ItemType = new Intent(this, Monsters.class);
                break;
            case 12:
                ItemType = new Intent(this, Proficiencies.class);
                break;
            case 13:
                ItemType = new Intent(this, Races.class);
                break;
            case 14:
                ItemType = new Intent(this, Rules.class);
                break;
            case 15:
                ItemType = new Intent(this, RuleSections.class);
                break;
            case 16:
                ItemType = new Intent(this, Spells.class);
                break;
            case 17:
                ItemType = new Intent(this, StartingEquipment.class);
                break;
            case 18:
                ItemType = new Intent(this, Subclasses.class);
                break;
            case 19:
                ItemType = new Intent(this, Subraces.class);
                break;
            case 20:
                ItemType = new Intent(this, Traits.class);
                break;
            case 21:
                ItemType = new Intent(this, WeaponProperties.class);
                break;
        }
        String b = getClass().toString().split(" ")[1];
        ItemType.putExtra("PreviousActivity", b);
        ItemType.putExtra("ItemCategory",SchemaNames.get(TopMenuItems.indexOf(Category)));
        ItemType = AddSchemaToExtras(ItemType,SchemaNames.get(TopMenuItems.indexOf(Category)));
        ItemType.putExtra("SelectedItem",SelectedItem);
        ItemType.putExtra("Init",true);
        startActivity(ItemType);
    }

    private Intent AddSchemaToExtras(Intent itemType, String s) {
        ArrayList a = new ArrayList();
        synchronized (SchemaDataPopulator){

        }
        return itemType;
    }


    public void  ShowError(){
        Intent error = new Intent(this, ErrorPage.class);
        startActivity(error);
    }

    private void DoSchemaPopulate() {
        for(int i = 0;i<SchemaData.size();i++){
            
        }
    }

}
