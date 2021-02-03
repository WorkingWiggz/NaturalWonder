package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Subraces extends BasicItemActivity {

    TextView NameText, BRText, DescText, ChooseText, rtoText;
    ListView AbBoList, StartProList, LangList, RacTraList, rtolist;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subraces);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        NameText = findViewById(R.id.NameText);
        BRText = findViewById(R.id.BRText);
        DescText = findViewById(R.id.DescText);
        rtoText = findViewById(R.id.RTOtext);
        rtolist = findViewById(R.id.RTOList);
        ChooseText = findViewById(R.id.ChooseText);
        AbBoList = findViewById(R.id.AbBoList);
        StartProList = findViewById(R.id.StartProList);
        LangList = findViewById(R.id.LangList);
        RacTraList = findViewById(R.id.RacTraList);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            String b = ItemData.getJSONObject("race").getString("name");
            String c = ItemData.getString("desc");

            NameText.setText(a);
            BRText.setText(b);
            DescText.setText(c);
            JSONArray d = ItemData.getJSONArray("ability_bonuses");
            JSONArray abbo = new JSONArray();
            for (int i = 0; i < d.length(); i++) {
                JSONObject tmpab = d.getJSONObject(i).getJSONObject("ability_score");
                JSONObject ABJA = new JSONObject();
                String name = tmpab.getString("name") + " +" + d.getJSONObject(i).getInt("bonus");
                String url = tmpab.getString("url");
                ABJA.put("name", name);
                ABJA.put("url", url);
                abbo.put(ABJA);
            }
            JSONArray e = ItemData.getJSONArray("starting_proficiencies");
            JSONArray g = ItemData.getJSONArray("racial_traits");
            PopulateLists(AbBoList, abbo);
            PopulateLists(StartProList, e);
            PopulateLists(RacTraList, g);

            if (!ItemData.isNull("language_options")) {
                String h = "Choose: " + ItemData.getJSONObject("language_options").getInt("choose");
                ChooseText.setText(h);
                JSONArray f = ItemData.getJSONObject("language_options").getJSONArray("from");
                PopulateLists(LangList, f);
            } else {
                ChooseText.setText("");
                JSONArray CTLang = new JSONArray();
                JSONObject CTJO = new JSONObject();
                String CLname = "";
                String CLurl = "";
                CTJO.put("name", CLname);
                CTJO.put("url", CLurl);
                CTLang.put(CTJO);
                PopulateLists(LangList, CTLang);
            }

            if (!ItemData.isNull("racial_trait_options")) {
                JSONArray rto = ItemData.getJSONObject("racial_trait_options").getJSONArray("from");
                String rtochoice = "Choose: " + ItemData.getJSONObject("racial_trait_options").getInt("choose");
                rtoText.setText(rtochoice);
                PopulateLists(rtolist, rto);
            } else {
                rtoText.setText("");
                rtolist.setAdapter(null);
            }

        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
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
