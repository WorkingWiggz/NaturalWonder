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
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Character.toUpperCase;


public abstract class BasicItemActivity extends BaseNWActivity {
    protected Thread AssignDataThread;
    protected volatile Boolean LoadedData = false;
    protected boolean ReadyToSort = false;
    JSONObject ItemData;
    Boolean Loaded = false;
    ArrayList<Integer> FetchedPos = new ArrayList<>();
    ArrayList<String> JSONData = new ArrayList<>();
    ArrayList<String> ActivityList = new ArrayList();
    int CurrentPageCount;
    JSONArray LevelData;
    String Cat;
    ArrayList<String> Schema = new ArrayList<>();
    String ItemSelected;
    volatile ArrayList<JSONObject> FetchedData = new ArrayList<>();
    Class<?> PreviousActivity;
    JSONObject hold;
    Thread LoadDataThread = new Thread();
    ArrayList<String> TopMenu = new ArrayList<>();
    RequestQueue queue;
    String BaseURL = "https://www.dnd5eapi.co";
    Button BackButt;

    protected void onCreate(Bundle savedInstanceState) {
        queue = Volley.newRequestQueue(this);
        super.onCreate(savedInstanceState);
        LoadDataThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SortItemData();
                    PreloadData();
                } catch (Exception e) {
                    ErrorHandle(e, getApplicationContext());
                }
            }
        });
        try {

            if (getIntent().getBooleanExtra("Init", false)) {
                Cat = getIntent().getStringExtra("ItemCategory");
                ItemSelected = getIntent().getStringExtra("SelectedItem");
                Schema = getIntent().getStringArrayListExtra("CategorySchema");
                JSONData.clear();
                ActivityList.clear();
                ActivityList.add(getIntent().getStringExtra("PreviousActivity"));
                PreviousActivity = Class.forName(getIntent().getStringExtra("PreviousActivity"));
                LoadDataThread.start();
            } else {
                ConvertExtras();
                ItemData = new JSONObject(JSONData.get(CurrentPageCount));
                PreviousActivity = Class.forName(ActivityList.get(CurrentPageCount));

            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e, getApplicationContext());
        }

        AssignDataThreadFactory();
    }

    protected abstract void SortDataToItems() throws Exception;

    private void SortItemData() throws UnsupportedEncodingException, JSONException {
        String query = CreateQuery();
        String URL = BaseURL + "/graphql?query=" + query;
        StringRequest sr = new StringRequest(Request.Method.GET, URL, response -> {
            try {
                JSONObject shaver = new JSONObject(response);
                if (Cat.equals("level")) {
                    LevelData = shaver.getJSONObject("data").getJSONArray("levels");
                    SaveToLocalBackup(URL, LevelData);
                } else {
                    ItemData = shaver.getJSONObject("data").getJSONObject(Cat);
                    SaveToLocalBackup(URL, ItemData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ErrorHandle(e, getApplicationContext());
            }
        }, error -> {
            error.printStackTrace();
            ErrorHandle(error, getApplicationContext());
        });

        if (!Cat.equals("level")) {
            JSONObject a = HandleRequest(sr, queue);
            if (!(a == null)) {
                ItemData = a;
            }
        } else {
            JSONArray a = HandleRequestJA(sr, queue);
            if (!(a == null)) {
                LevelData = a;
            }
        }

    }

    private String CreateQuery() throws UnsupportedEncodingException {
        String CQ = "";
        String NameOption = "";
        String FirstPart = "";
        if (Cat.equals("abilityScore")) {
            NameOption = "full_name";
            FirstPart = "{" + Cat + "(filter:{" + NameOption + ":\"" + ItemSelected + "\"}){";
        } else if (Cat.equals("level") || Cat.equals("startingequipment")) {
            NameOption = "class:{name";
            FirstPart = "{" + Cat + "(filter:{" + NameOption + ":\"" + ItemSelected + "\"}}){";
        } else {
            NameOption = "name";
            FirstPart = "{" + Cat + "(filter:{" + NameOption + ":\"" + ItemSelected + "\"}){";
        }
        String Schema = BuildQuery();
        CQ = FirstPart + Schema + "}}";
        CQ = CQ.replace(" ", "%20");
        CQ = CQ.replace("+", "%2B");
        return CQ;
    }

    private String BuildQuery() {
        String query = "";
        for (int i = 0; i < this.Schema.size(); i++) {
            String item = this.Schema.get(i);
            switch (item) {
                case "skills":
                case "subclass":
                case "saving_throws":
                case "racial_traits":
                case "subsections":
                case "spellcasting_ability":
                case "starting_proficiencies":
                case "classes":
                case "races":
                case "references":
                case "traits":
                case "race":
                case "subraces":
                case "subclasses":
                case "properties":
                case "school":
                case "gear_category":
                case "equipment_category":
                case "equipment":
                case "feature_choices":
                case "features":
                case "forms":
                case "condition_immunities":
                    query = query + item + "{name url}" + " ";
                    break;
                case "choice":
                    query = query + item + "{type from{name url} choose}" + " ";
                    break;
                case "actions":
                    query = query + item + "{desc name damage{damage_dice damage_type{name url}} attack_bonus}" + " ";
                    break;
                case "class":
                    if (Cat.equals("level")) {
                        query = query + item + "{name url}" + " ";
                    } else {
                        query = query + item + "{name}" + " ";
                    }

                    break;
                case "prerequisites":
                    query = query + item + "{type level}" + " ";
                    break;
                case "proficiency_choices":

                case "language_options":
                case "trait_options":
                case "starting_proficiency_options":
                case "racial_trait_options":

                    query = query + item + "{from{name url} type choose}" + " ";
                    break;
                case "spellcasting":
                    if (Cat.equals("level")) {
                        query = query + item + "{spells_known cantrips_known spell_slots_level_1 spell_slots_level_2 spell_slots_level_3 spell_slots_level_4 spell_slots_level_5 spell_slots_level_6 spell_slots_level_7 spell_slots_level_8 spell_slots_level_9}" + " ";
                    } else {
                        query = query + item + "{info{name desc}}" + " ";
                    }
                    break;
                case "armor_class":
                    if (!Cat.equals("monster")) {
                        query = query + item + "{base dex_bonus max_bonus}" + " ";
                    } else {
                        query = query + item + " ";
                    }
                    break;
                case "contents":
                    query = query + item + "{item{name url} quantity}" + " ";
                    break;
                case "cost":

                case "speed":
                    if (Cat.equals("monster")) {
                        query = query + item + "{hover fly swim walk burrow climb}" + " ";
                    } else if (Cat.equals("race")) {
                        query = query + item + " ";
                    } else {
                        query = query + item + "{unit quantity}" + " ";
                    }

                    break;
                case "damage":
                    if (Cat.equals("spell")) {
                        query = query + item + "{damage_type{name url}}" + " ";
                    } else {
                        query = query + item + "{damage_dice damage_type{name url}}" + " ";
                    }
                    break;
                case "two_handed_damage":
                    query = query + item + "{damage_dice damage_type{name url}}" + " ";
                    break;

                case "range":
                case "throw_range":
                    if (Cat.equals("spell")) {
                        query = query + item + " ";
                    } else {
                        query = query + item + "{normal long}" + " ";
                    }

                    break;
                case "subclass_specific":
                    query = query + item + "{aura_range additional_magical_secrets_max_lvl}" + " ";
                    break;
                case "class_specific":
                    query = query + item + "{ki_points aura_range rage_count martial_arts{dice_count dice_value} sneak_attack{dice_count dice_value} action_surges extra_attacks sorcery_points wild_shape_fly favored_enemies favored_terrain metamagic_known wild_shape_swim arcane_recovery_levels bardic_inspiration_die mystic_arcanum_level_6 mystic_arcanum_level_7 mystic_arcanum_level_8 mystic_arcanum_level_9 channel_divinity_charges indomitable_uses song_of_rest_die destroy_undead_cr invocations_known rage_damage_bonus wild_shape_max_cr unarmored_movement brutal_critical_dice creating_spell_slots{spell_slot_level sorcery_point_cost} magical_secrets_max_5 magical_secrets_max_7 magical_secrets_max_9}" + " ";
                    break;
                case "legendary_actions":
                    query = query + item + "{name desc attack_bonus}" + " ";
                    break;
                case "senses":
                    query = query + item + "{truesight blindsight darkvision tremorsense passive_perception}" + " ";
                    break;
                case "reactions":
                case "special_abilities":
                    query = query + item + "{name desc}" + " ";
                    break;
                case "proficiencies":
                    if (Cat.equals("monster")) {
                        query = query + item + "{value proficiency{name url}}" + " ";
                    } else {
                        query = query + item + "{name url}" + " ";
                    }
                    break;
                case "ability_bonuses":
                    query = query + item + "{ability_score{name url} bonus}" + " ";
                    break;
                case "starting_equipment":
                    if (!Cat.equals("class")) {
                        query = query + item + "{quantity equipment{name url}}" + " ";
                    } else {
                        query = query + item + " ";
                    }

                    break;
                case "spells":
                    if (Cat.equals("subclass")) {
                        query = query + item + "{spell{name url} prerequisites{name url}}" + " ";
                    } else {
                        query = query + item + " ";
                    }

                    break;
                case "area_of_effect":
                    query = query + item + "{size type}" + " ";
                    break;
                case "dc":
                    query = query + item + "{desc dc_type{name url} dc_success}" + " ";
                    break;
                case "ability_bonus_options":
                    query = query + item + "{from{bonus ability_score{name url}} type choose}" + " ";
                    break;
                case "languages":
                    if (!Cat.equals("monster")) {
                        query = query + item + "{name url}" + " ";
                    } else {
                        query = query + item + " ";
                    }
                    break;

                case "starting_equipment_options":
                    query = query + item + "{from{quantity equipment{name url}} type choose}" + " ";
                    break;
                default:
                    query = query + item + " ";
                    break;
            }
        }
        query = query.replace(" ", "%20");
        return query;
    }

    public void FetchSpecificURL(String URL, RequestQueue Queue, Boolean OldStyle) throws UnsupportedEncodingException, JSONException {
        String RequestURL = "";
        int test = FetchedData.size() + 1;
        FetchedData.add(new JSONObject());
        if (URL.equals("null")) {
            StartAssocaiedFunction(test - 1);
            return;
        }
        String shaved = URL.substring(5);
        String Category = shaved.substring(0, shaved.indexOf('/'));
        String Filter = shaved.substring(shaved.indexOf('/') + 1);


        if (OldStyle) {
            RequestURL = this.BaseURL + URL;
        } else {
            RequestURL = CreateQuery(Category, Filter);
        }
        String finalRequestURL = RequestURL;
        StringRequest SR = new StringRequest(Request.Method.GET, finalRequestURL, response -> {
            try {
                if (OldStyle) {
                    JSONObject jo = new JSONObject(response);
                    FetchedData.set(test - 1, jo);
                } else {
                    JSONObject Shaver = new JSONObject(response).getJSONObject("data");
                    FetchedData.set(test - 1, Shaver.getJSONObject(Category));
                }
                LoadFetchedDatatoViews();
            } catch (JSONException e) {
                e.printStackTrace();
                ErrorHandle(e, getApplicationContext());
            }
        }, Error -> {
            Error.printStackTrace();
            ErrorHandle(Error, getApplicationContext());
        });
        HandleRequest(SR, Queue);
        JSONObject ahold = HandleRequest(SR, Queue);
        if (ahold != null) {
            FetchedData.add(ahold);
        }
    }

    protected void LoadFetchedDatatoViews() {
        for (int i = 0; i < FetchedData.size(); i++) {
            JSONObject tmpJO = FetchedData.get(i);
            if (tmpJO.length() != 0) {
                StartAssocaiedFunction(i);
            }
        }
    }

    protected abstract void StartAssocaiedFunction(int pos);

    protected abstract void PreloadData();


    private String CreateQuery(String category, String filter) throws UnsupportedEncodingException {
        String CQ = "";
        String NameOption = "";
        Boolean CappChar = false;
        String temp = "";
        for (int i = 0; i < category.length(); i++) {
            char a = category.charAt(i);
            if (a == '-') {
                CappChar = true;
                continue;
            }

            if (CappChar) {
                temp = temp + toUpperCase(a);
            } else {
                temp = temp + a;
            }
            CappChar = false;
        }
        if (temp.equals("startingEquipment")) {
            temp = temp.toLowerCase();
        }


        category = temp;
        if (category.equals("abilityScore")) {
            NameOption = "full_name";
        } else if (category.equals("startingequipment")) {
            NameOption = "class:{name";
        } else {
            NameOption = "name";
        }
        String Schema = BuildQuery();
        String FirstPart = "{" + category + "(filter:{" + NameOption + ":\"" + filter + "\"}){";
        CQ = FirstPart + Schema + "}}";
        CQ = this.BaseURL + "/graphql?query=" + CQ;
        return CQ;
    }


    public void GetItem(final String url) {
        final String s = BaseURL + url;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, s, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    hold = new JSONObject(response);
                    processJSON(url);
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, getApplicationContext());
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
                    for (int i = 0; i < MenuData.names().length(); i++) {
                        TopMenu.add(MenuData.names().getString(i));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                ErrorHandle(error, getApplicationContext());
            }
        });
        try {
            JSONObject a = HandleRequest(stringRequest, queue);
            JSONObject b = HandleRequest(GetTopMenus, queue);

            if (!(a == null)) {
                hold = a;
                processJSON(url);
            }

            if (!(b == null)) {
                JSONObject MenuData = b;
                for (int i = 0; i < MenuData.names().length(); i++) {
                    TopMenu.add(MenuData.names().getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }

    }

    private void processJSON(String Lasturl) throws JSONException {
        String a = Lasturl.substring(5);
        String[] b = a.split("/");
        String c = b[0];
        for (int i = 0; i < TopMenu.size(); i++) {
            boolean d = TopMenu.get(i).equals(c);
            if (d) {
                switch (i) {
                    case 4:
                        ShowItem(5);
                        break;
                    case 5:
                        ShowItem(4);
                        break;
                    case 1:
                        ShowItem(2);
                        break;
                    case 2:
                        ShowItem(1);
                        break;
                    case 15:
                        ShowItem(22);
                        break;
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                        ShowItem(i + 1);
                        break;


                    default:
                        ShowItem(i);
                        break;
                }
                break;
            } else {
                if (c.equals("levels")) {
                    ShowItem(8);
                    break;
                }
            }
        }
    }

    private void ShowItem(int a) {
        Intent ItemType = PickIntent(a);
        ItemType.putExtra("ItemData", hold.toString());
        StartActivity(ItemType);
    }

    public void ShowError(Exception e) {
        Intent error = new Intent(this, ErrorPage.class);
        startActivity(error);
    }

    public void OnClickHandleList(JSONArray items, int pos) {
        try {
            JSONObject c = items.getJSONObject(pos);
            if (!c.getString("url").equals("#")) {
                GetItem(c.getString("url"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, getApplicationContext());
        }
    }

    public void PopulateLists(ListView a, final JSONArray Data) {
        try {
            final ArrayList<String> AL = new ArrayList<>();
            for (int i = 0; i < Data.length(); i++) {
                String s = Data.getJSONObject(i).getString("name");
                AL.add(s);
            }
            CustomListAdapter adap = new CustomListAdapter(this, AL);
            a.setAdapter(adap);
            a.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    OnClickHandleList(Data, position);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }
    }

    public void SortBackButt(final Button backButt) {
        backButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoPreviousActivity();
            }
        });
    }

    public void GotoPreviousActivity() {
        Intent PrevAct = new Intent(this, PreviousActivity);
        PrevAct.putExtra("IsAPreviousActivity", true);
        StartActivity(PrevAct);
    }

    public void StartActivity(Intent PassedIntent) {
        if (PassedIntent.getBooleanExtra("IsAPreviousActivity", false)) {
            GoingBack(PassedIntent);
        } else {
            GoingForward(PassedIntent);
        }
        startActivity(PassedIntent);
    }

    private void GoingBack(Intent passedIntent) {
        if (ActivityList.size() >= 1) {
            ActivityList.remove(CurrentPageCount);
        }
        if (JSONData.size() >= 1) {
            JSONData.remove(CurrentPageCount);
        }

        if (CurrentPageCount > 0) {
            CurrentPageCount--;
        }

        String[] a = JSONData.toArray(new String[0]);
        String[] b = ActivityList.toArray(new String[0]);
        passedIntent.putExtra("JSONDataArray", a);
        passedIntent.putExtra("ActivityListArray", b);
        passedIntent.putExtra("CurrentCount", CurrentPageCount);
    }

    protected void GoingForward(Intent passedIntent) {
        if (LevelData != null) {
            if (!JSONData.contains(LevelData.toString())) {
                JSONData.add(LevelData.toString());
            }
        } else {
            if (!JSONData.contains(ItemData.toString())) {
                JSONData.add(ItemData.toString());
            }
        }


        ActivityList.add(getClass().toString().split(" ")[1]);
        if (CurrentPageCount < JSONData.size()) {
            CurrentPageCount++;
        }


        String[] a = JSONData.toArray(new String[0]);
        String[] b = ActivityList.toArray(new String[0]);
        passedIntent.putExtra("JSONDataArray", a);
        passedIntent.putExtra("ActivityListArray", b);
        passedIntent.putExtra("CurrentCount", CurrentPageCount);
    }

    public void MenuButtonHandle(final Button b) {
        b.setOnClickListener(v -> {
            Intent i = new Intent(b.getContext(), MainActivity.class);
            startActivity(i);
        });
    }

    public void ConvertExtras() throws JSONException {
        JSONData.clear();
        ActivityList.clear();
        if (getIntent().getStringArrayExtra("JSONDataArray") != null) {
            Collections.addAll(JSONData, getIntent().getStringArrayExtra("JSONDataArray"));
        }
        if (getIntent().getStringArrayExtra("ActivityListArray") != null) {
            Collections.addAll(ActivityList, getIntent().getStringArrayExtra("ActivityListArray"));
        }
        CurrentPageCount = getIntent().getIntExtra("CurrentCount", 0);

        if (getIntent().getStringExtra("ItemData") != null) {
            JSONData.add(getIntent().getStringExtra("ItemData"));
            try {
                ItemData = new JSONObject(JSONData.get(CurrentPageCount));
            } catch (IndexOutOfBoundsException e) {
                ItemData = new JSONObject(JSONData.get(CurrentPageCount - 1));
            }

        }
    }


    protected abstract void AssignDataThreadFactory();

}

