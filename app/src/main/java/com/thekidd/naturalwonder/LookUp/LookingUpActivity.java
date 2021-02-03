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

    TextView Title;
    private Button MenuButt;
    private Toast delaytoast;
    private int TimerSeconds = 3250;
    private Toast Finisheddelaytoast;
    private Boolean DelayToastTriggered = false;
    private ListView listView;
    private String SelectedItem = "";
    private RequestQueue queue;
    private JSONObject Schema = new JSONObject();
    private HashMap<String, ArrayList> SchemaData = new HashMap<>();
    private String TopMenuUrl = "https://www.dnd5eapi.co/graphql?query={__type(name:%22Query%22){fields{name%20CheckType:type{kind}}}}";
    private CustomListAdapter toList;
    private ArrayList<String> reshold;
    private ArrayList<String> ItemTypes;
    private ArrayList<String> MenuItems;
    private ArrayList<String> SchemaNames;
    private ArrayList<String> SearchNames;
    private ArrayList<String> TopMenuItems;
    private ArrayList<String> SubMenuItems;
    private String Category;
    private JSONObject hold;
    private Boolean SchemaLoaded = false;
    private Thread SchemaDataPopulator = new Thread() {
        @Override
        public void run() {
            String SchemaURL = "https://www.dnd5eapi.co/graphql?query=%7B__type%28name%3A%22Query%22%29%7Bfields%7Bname%20type%7Bfields%7Bname%7D%7D%7D%7D%7D";
            final StringRequest sr = new StringRequest(Request.Method.GET, SchemaURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject shaver = new JSONObject(response);
                        setSchema(shaver.getJSONObject("data").getJSONObject("__type"));
                        SaveToLocalBackup(SchemaURL, getSchema());
                        DoSchemaPopulate();
                    } catch (Exception e) {
                        ErrorHandle(e, getApplicationContext());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

            try {
                setSchema(HandleRequest(sr, getQueue()));
                if (!(getSchema() == null)) {
                    DoSchemaPopulate();
                }
            } catch (JSONException e) {
                ErrorHandle(e, getApplicationContext());
            }

        }
    };
    private Boolean requestdelay = false;
    private Boolean TopMenu = true;
    private Boolean SubMenu = false;
    private Boolean Activity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looking_up);
        setListView(findViewById(R.id.ListView));
        Title = findViewById(R.id.TitleText);
        Title.setText("Looking Up Menu");

        setDelaytoast(Toast.makeText(this, "Please wait a few seconds...", Toast.LENGTH_SHORT));
        setFinisheddelaytoast(Toast.makeText(this, "Try pressing it now.", Toast.LENGTH_SHORT));
        setReshold(new ArrayList<>());
        setSubMenuItems(new ArrayList<>());
        setItemTypes(new ArrayList<>());
        setTopMenuItems(new ArrayList<>());
        setMenuItems(new ArrayList<>());
        setSchemaNames(new ArrayList<>());
        setSearchNames(new ArrayList<>());
        setMenuButt(findViewById(R.id.menubutt));
        setQueue(Volley.newRequestQueue(this));


        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getTopMenu() && getRequestdelay()) {
                    setDelayToastTriggered(true);
                    getDelaytoast().show();
                } else {
                    if (getTopMenu()) {
                        // startTimer(TimerSeconds);
                        ToSubMenu(position);
                    } else if (getSubMenu()) {
                        ToItemActivity(position);
                    }
                }
            }
        });

        //Initializes Menu
        final StringRequest s = new StringRequest(Request.Method.GET, getTopMenuUrl(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject shaver = new JSONObject(response);
                    setHold(shaver.getJSONObject("data").getJSONObject("__type"));
                    SaveToLocalBackup(getTopMenuUrl(), getHold());
                    SortMenuData();
                } catch (Exception e) {
                    ErrorHandle(e, getApplicationContext());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        try {
            setHold(HandleRequest(s, getQueue()));
            if (!(getHold() == null)) {
                SortMenuData();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }


        final Intent MenuBack = new Intent(this, MainActivity.class);

        getMenuButt().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getTopMenu()) {
                    startActivity(MenuBack);
                } else if (getSubMenu()) {
                    try {
                        ToTopMenu();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        ErrorHandle(e, v.getContext());
                    }
                }
            }
        });
    }

    private void startTimer(final int TimerLimt) {
        setRequestdelay(true);
        CountDownTimer CDT = new CountDownTimer(TimerLimt, 1000) {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                setRequestdelay(false);
                if (getDelayToastTriggered()) {
                    getFinisheddelaytoast().show();
                    setDelayToastTriggered(false);
                }
            }
        }.start();
    }


    private void SortMenuData() throws JSONException {
        JSONArray fields = getHold().getJSONArray("fields");
        for (int i = 0; i < fields.length(); i++) {
            JSONObject CurrObj = fields.getJSONObject(i);
            if (CurrObj.getJSONObject("CheckType").getString("kind").equals("OBJECT")) {
                String ItemName = CurrObj.getString("name");
                if (!getSchemaNames().contains(ItemName)) {
                    getSchemaNames().add(ItemName);
                }
            } else if (CurrObj.getJSONObject("CheckType").getString("kind").equals("NON_NULL")) {
                String ItemName = CurrObj.getString("name");
                if (!getSearchNames().contains(ItemName)) {
                    getSearchNames().add(ItemName);
                }
                String newname = "";
                if (ItemName.equals("startingequipments")) {
                    newname = "Starting Equipments";
                } else {
                    for (int j = 0; j < ItemName.toCharArray().length; j++) {
                        if (j == 0) {
                            newname = newname + ItemName.charAt(0);
                            newname = newname.toUpperCase();
                        } else {
                            char c = ItemName.charAt(j);
                            if (Character.isUpperCase(c)) {
                                newname = newname + " " + ItemName.charAt(j);
                            } else {
                                newname = newname + ItemName.charAt(j);
                            }
                        }
                    }
                }
                if (!getTopMenuItems().contains(newname)) {
                    getTopMenuItems().add(newname);
                }
            }
        }
        getSchemaDataPopulator().start();
        ToTopMenu();
    }


    private void ToTopMenu() throws JSONException {
        setTopMenu(true);
        setSubMenu(false);
        setActivity(false);
        getMenuButt().setText("Menu");
        Title.setText("Looking Up Menu");
        getReshold().clear();
        getMenuItems().clear();
        getItemTypes().clear();
        for (int i = 0; i < getTopMenuItems().size(); i++) {
            getMenuItems().add(getTopMenuItems().get(i));
            getItemTypes().add(getTopMenuItems().get(i));
            getReshold().add(getTopMenuItems().get(i));
        }
        UpdateView();
    }

    private void ToSubMenu(int position) {
        setTopMenu(false);
        setSubMenu(true);
        setActivity(false);
        getMenuButt().setText("Back");
        setCategory(getTopMenuItems().get(position));
        try {
            GetMenus(getSearchNames().get(position), getTopMenuItems().get(position));
        } catch (JSONException | UnsupportedEncodingException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }
    }

    private void ToItemActivity(int position) {
        setSelectedItem(getSubMenuItems().get(position));
        ShowItem();
    }

    private void UpdateView() {
        setToList(new CustomListAdapter(this, getReshold()));
        getListView().setAdapter(getToList());
    }


    private void GetMenus(String url, final String TitleString) throws JSONException, UnsupportedEncodingException {
        final String query = BuildQuery(url);
        final String s = BaseURL + "/graphql?query=" + query;


        StringRequest stringRequest = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject shaver = new JSONObject(response);
                    setHold(shaver.getJSONObject("data"));
                    SaveToLocalBackup(s, getHold());
                    Title.setText(TitleString);
                    CheckData(getTopMenuItems().indexOf(TitleString));
                } catch (Exception e) {
                    ErrorHandle(e, getApplicationContext());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorHandle(error, getApplicationContext());
            }
        });

        JSONObject testObject = HandleRequest(stringRequest, getQueue());
        if (!(testObject == null)) {
            setHold(testObject);
            Title.setText(TitleString);
            CheckData(getTopMenuItems().indexOf(TitleString));
        }

    }


    private void CheckData(int selectedpos) throws JSONException {
        getReshold().clear();
        getSubMenuItems().clear();
        JSONArray data = getHold().getJSONArray(getSearchNames().get(selectedpos));
        switch (selectedpos) {
            case 8:
                for (int i = 0; i < data.length(); i++) {
                    String t = "";
                    t = data.getJSONObject(i).getJSONObject("class").getString("name");
                    if (!getSubMenuItems().contains(t)) {
                        getSubMenuItems().add(t);
                        getReshold().add(t);
                    }
                }
                break;
            case 17:
                for (int i = 0; i < data.length(); i++) {
                    String t = "";
                    t = data.getJSONObject(i).getJSONObject("class").getString("name");
                    getSubMenuItems().add(t);
                    getReshold().add(t);
                }
                break;
            default:
                for (int i = 0; i < data.length(); i++) {
                    String t = "";
                    try {
                        t = data.getJSONObject(i).getString("full_name");
                    } catch (JSONException e) {
                        t = data.getJSONObject(i).getString("name");
                    }
                    getSubMenuItems().add(t);
                    getReshold().add(t);
                }
                break;
        }
        UpdateView();
    }


    protected String BuildQuery(String url) throws UnsupportedEncodingException {
        String query = "";
        if (getSearchNames().contains(url)) {

            switch (getSearchNames().indexOf(url)) {
                case 0:
                    query = "{" + url + "{full_name}}";
                    break;
                case 8:
                    query = "{" + url + "(limit:250){class{name} level}}";
                    break;
                case 4:
                case 16:
                case 12:
                    query = "{" + url + "(limit:1000){name}}";
                    break;
                case 17:
                    query = "{" + url + "{class{name}}}";
                    break;
                default:
                    query = "{" + url + "{name}}";
                    break;
            }
        }
        return URLEncoder.encode(query, "UTF-8");
    }


    private void ShowItem() {
        Intent ItemType = new Intent(this, ErrorPage.class);
        int a = -1;
        String PrevCategory = "";
        for (int i = 0; i < getTopMenuItems().size(); i++) {
            String x = getTopMenuItems().get(i);
            boolean CatCheck = (getCategory().equals(x));
            if (CatCheck) {
                a = i;
                break;
            }
        }
        PrevCategory = getTopMenuItems().get(a);
        ItemType = PickIntent(a);

        String b = getClass().toString().split(" ")[1];
        ItemType.putExtra("PreviousActivity", b);
        ItemType.putExtra("ItemCategory", getSchemaNames().get(getTopMenuItems().indexOf(getCategory())));
        ItemType = AddSchemaToExtras(ItemType, getSchemaNames().get(getTopMenuItems().indexOf(getCategory())));
        ItemType.putExtra("SelectedItem", getSelectedItem());
        ItemType.putExtra("Init", true);
        startActivity(ItemType);
    }

    private Intent AddSchemaToExtras(Intent itemType, String s) {
        synchronized (getSchemaDataPopulator()) {
            while (!getSchemaLoaded()) {
                System.out.println();
            }
            itemType.putStringArrayListExtra("CategorySchema", getSchemaData().get(s));
        }
        return itemType;
    }


    public void ShowError() {
        Intent error = new Intent(this, ErrorPage.class);
        startActivity(error);
    }

    private void DoSchemaPopulate() throws JSONException {
        JSONArray Schemas = getSchema().getJSONArray("fields");
        for (int i = 0; i < Schemas.length(); i++) {
            JSONObject Data = Schemas.getJSONObject(i);
            String Name = Data.getString("name");
            if (getSchemaNames().contains(Name)) {
                JSONArray Array = Data.getJSONObject("type").getJSONArray("fields");
                ArrayList<String> AL = new ArrayList<>();
                for (int j = 0; j < Array.length(); j++) {
                    if (!Array.getJSONObject(j).getString("name").equals("_id")) {
                        AL.add(Array.getJSONObject(j).getString("name"));
                    }

                }
                getSchemaData().put(Name, AL);
            }
        }
        setSchemaLoaded(true);
    }

    public Button getMenuButt() {
        return MenuButt;
    }

    public void setMenuButt(Button menuButt) {
        MenuButt = menuButt;
    }

    public Toast getDelaytoast() {
        return delaytoast;
    }

    public void setDelaytoast(Toast delaytoast) {
        this.delaytoast = delaytoast;
    }

    public int getTimerSeconds() {
        return TimerSeconds;
    }

    public void setTimerSeconds(int timerSeconds) {
        TimerSeconds = timerSeconds;
    }

    public Toast getFinisheddelaytoast() {
        return Finisheddelaytoast;
    }

    public void setFinisheddelaytoast(Toast finisheddelaytoast) {
        Finisheddelaytoast = finisheddelaytoast;
    }

    public Boolean getDelayToastTriggered() {
        return DelayToastTriggered;
    }

    public void setDelayToastTriggered(Boolean delayToastTriggered) {
        DelayToastTriggered = delayToastTriggered;
    }

    public ListView getListView() {
        return listView;
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    public String getSelectedItem() {
        return SelectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        SelectedItem = selectedItem;
    }

    public RequestQueue getQueue() {
        return queue;
    }

    public void setQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public JSONObject getSchema() {
        return Schema;
    }

    public void setSchema(JSONObject schema) {
        Schema = schema;
    }

    public HashMap<String, ArrayList> getSchemaData() {
        return SchemaData;
    }

    public void setSchemaData(HashMap<String, ArrayList> schemaData) {
        SchemaData = schemaData;
    }

    public Thread getSchemaDataPopulator() {
        return SchemaDataPopulator;
    }

    public void setSchemaDataPopulator(Thread schemaDataPopulator) {
        SchemaDataPopulator = schemaDataPopulator;
    }

    public String getTopMenuUrl() {
        return TopMenuUrl;
    }

    public void setTopMenuUrl(String topMenuUrl) {
        TopMenuUrl = topMenuUrl;
    }

    public CustomListAdapter getToList() {
        return toList;
    }

    public void setToList(CustomListAdapter toList) {
        this.toList = toList;
    }

    public ArrayList<String> getReshold() {
        return reshold;
    }

    public void setReshold(ArrayList<String> reshold) {
        this.reshold = reshold;
    }

    public ArrayList<String> getItemTypes() {
        return ItemTypes;
    }

    public void setItemTypes(ArrayList<String> itemTypes) {
        ItemTypes = itemTypes;
    }

    public ArrayList<String> getMenuItems() {
        return MenuItems;
    }

    public void setMenuItems(ArrayList<String> menuItems) {
        MenuItems = menuItems;
    }

    public ArrayList<String> getSchemaNames() {
        return SchemaNames;
    }

    public void setSchemaNames(ArrayList<String> schemaNames) {
        SchemaNames = schemaNames;
    }

    public ArrayList<String> getSearchNames() {
        return SearchNames;
    }

    public void setSearchNames(ArrayList<String> searchNames) {
        SearchNames = searchNames;
    }

    public ArrayList<String> getTopMenuItems() {
        return TopMenuItems;
    }

    public void setTopMenuItems(ArrayList<String> topMenuItems) {
        TopMenuItems = topMenuItems;
    }

    public ArrayList<String> getSubMenuItems() {
        return SubMenuItems;
    }

    public void setSubMenuItems(ArrayList<String> subMenuItems) {
        SubMenuItems = subMenuItems;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public JSONObject getHold() {
        return hold;
    }

    public void setHold(JSONObject hold) {
        this.hold = hold;
    }

    public Boolean getSchemaLoaded() {
        return SchemaLoaded;
    }

    public void setSchemaLoaded(Boolean schemaLoaded) {
        SchemaLoaded = schemaLoaded;
    }

    public Boolean getRequestdelay() {
        return requestdelay;
    }

    public void setRequestdelay(Boolean requestdelay) {
        this.requestdelay = requestdelay;
    }

    public Boolean getTopMenu() {
        return TopMenu;
    }

    public void setTopMenu(Boolean topMenu) {
        TopMenu = topMenu;
    }

    public Boolean getSubMenu() {
        return SubMenu;
    }

    public void setSubMenu(Boolean subMenu) {
        SubMenu = subMenu;
    }

    public Boolean getActivity() {
        return Activity;
    }

    public void setActivity(Boolean activity) {
        Activity = activity;
    }
}
