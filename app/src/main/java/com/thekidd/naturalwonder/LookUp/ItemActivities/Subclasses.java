package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Subclasses extends BasicItemActivity {

    TextView CNameText, OGCLassText, FTitleText, DescText;
    ListView FeatList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subclasses);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        CNameText = findViewById(R.id.CNameText);
        OGCLassText = findViewById(R.id.OGCLassText);
        FTitleText = findViewById(R.id.FTitleText);
        DescText = findViewById(R.id.DescText);
        FeatList = findViewById(R.id.Feats);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            String b = ItemData.getJSONObject("class").getString("name");
            String c = ItemData.getString("subclass_flavor");
            String d = ItemData.getJSONArray("desc").getString(0);
            JSONArray f = new JSONArray();
            if (!ItemData.isNull("spells")) {
                JSONArray e = ItemData.getJSONArray("spells");
                for (int i = 0; i < e.length(); i++) {
                    JSONObject tmpspell = new JSONObject();
                    JSONObject prequistque = new JSONObject();
                    String name = e.getJSONObject(i).getJSONObject("spell").getString("name");
                    String url = e.getJSONObject(i).getJSONObject("spell").getString("url");
                    tmpspell.put("name", name);
                    tmpspell.put("url", url);
                    f.put(tmpspell);
                    JSONArray PreqJA = e.getJSONObject(i).getJSONArray("prerequisites");
                    prequistque.put("name", "<---- Prerequisite ---->");
                    prequistque.put("url", "#");
                    f.put(prequistque);
                    for (int j = 0; j < PreqJA.length(); j++) {
                        JSONObject tmpa = new JSONObject();
                        JSONObject tmp = PreqJA.getJSONObject(j);
                        String PreqName = tmp.getString("name");
                        String PreqUrl = tmp.getString("url");
                        tmpa.put("name", PreqName);
                        tmpa.put("url", PreqUrl);
                        f.put(tmpa);
                    }
                }
            } else {
                TextView tmpTV = findViewById(R.id.textView89);
                tmpTV.setText("");
            }

            CNameText.setText(a);
            OGCLassText.setText(b);
            FTitleText.setText(c);
            DescText.setText(d);
            PopulateLists(FeatList, f);
        } catch (Exception e) {
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
