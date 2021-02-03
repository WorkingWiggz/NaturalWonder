package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Races extends BasicItemActivity {

    TextView NameText, SpeedText, AlignText, AgeText, sizeText, SizeDescText, TypeText, LangDescText;
    ListView AbBoList, StartProList, LangList, TraitsList, TraitOptList, SubRacesList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        NameText = findViewById(R.id.DescText);
        SpeedText = findViewById(R.id.SpeedText);
        AlignText = findViewById(R.id.AlignmentText);
        AgeText = findViewById(R.id.AgeText);
        sizeText = findViewById(R.id.sizeText);
        SizeDescText = findViewById(R.id.SizeDescText);
        LangDescText = findViewById(R.id.LangDescText);
        AbBoList = findViewById(R.id.AbBoList);
        StartProList = findViewById(R.id.StartProList);
        LangList = findViewById(R.id.LangList);
        TraitsList = findViewById(R.id.TraitsList);
        TraitOptList = findViewById(R.id.TraitOptList);
        SubRacesList = findViewById(R.id.SubRacesList);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            String b = ItemData.getString("speed");
            String c = ItemData.getString("alignment");
            String d = ItemData.getString("age");
            String e = ItemData.getString("size");
            String f = ItemData.getString("size_description");
            String g = ItemData.getString("language_desc");
            JSONArray i = ItemData.getJSONArray("starting_proficiencies");
            JSONArray j = ItemData.getJSONArray("languages");
            JSONArray k = ItemData.getJSONArray("traits");
            if (!ItemData.isNull("trait_options")) {
                JSONArray l = ItemData.getJSONObject("trait_options").getJSONArray("from");
                PopulateLists(TraitOptList, l);
            }

            JSONArray m = ItemData.getJSONArray("subraces");
            JSONArray n = ItemData.getJSONArray("ability_bonuses");

            JSONArray abs = new JSONArray();
            for (int xx = 0; xx < n.length(); xx++) {
                String name = n.getJSONObject(xx).getJSONObject("ability_score").getString("name") + ": " + n.getJSONObject(xx).getString("bonus");
                String url = n.getJSONObject(xx).getJSONObject("ability_score").getString("url");
                JSONObject JO = new JSONObject();
                JO.put("name", name);
                JO.put("url", url);
                abs.put(JO);
            }


            NameText.setText(a);
            SpeedText.setText(b);
            AlignText.setText(c);
            AgeText.setText(d);
            sizeText.setText(e);
            SizeDescText.setText(f);
            LangDescText.setText(g);
            PopulateLists(AbBoList, abs);
            PopulateLists(StartProList, i);
            PopulateLists(LangList, j);
            PopulateLists(TraitsList, k);

            PopulateLists(SubRacesList, m);


        } catch (JSONException ex) {
            ex.printStackTrace();
            ErrorHandle(ex, this);
        }
    }

    @Override
    protected void LoadFetchedDatatoViews() {

    }

    @Override
    protected void StartAssocaiedFunction(int pos) {

    }

    @Override
    protected void PreloadData() {

    }

    @Override
    protected void AssignDataThreadFactory() {
        AssignDataThread = new Thread() {
            @Override
            public void run() {
                synchronized (LoadDataThread) {
                    while (!LoadedData) {
                        if (ItemData != null) {
                            try {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            SortDataToItems();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            ErrorHandle(e, getApplicationContext());
                                        }
                                    }
                                });
                            } catch (Exception e) {
                                ErrorHandle(e, getApplicationContext());
                            }
                            break;
                        }

                    }
                }
            }
        };
        AssignDataThread.start();
    }
}
