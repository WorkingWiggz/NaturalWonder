package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StartingEquipment extends BasicItemActivity {
    ListView SE;
    TextView CN;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_equipment);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        CN = findViewById(R.id.CNText);
        SE = findViewById(R.id.SEList);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getJSONObject("class").getString("name");
            JSONArray b = ItemData.getJSONArray("starting_equipment");
            JSONArray d = new JSONArray();
            for (int i = 0; i < b.length(); i++) {
                String e = b.getJSONObject(i).getInt("quantity") + " x " + b.getJSONObject(i).getJSONObject("equipment").getString("name");
                String url = b.getJSONObject(i).getJSONObject("equipment").getString("url");
                JSONObject c = new JSONObject();
                c.put("name", e);
                c.put("url", url);
                d.put(c);
            }

            if (ItemData.getJSONArray("starting_equipment_options").length() > 0) {
                for (int ii = 0; ii < ItemData.getJSONArray("starting_equipment_options").length(); ii++) {
                    JSONObject aa = ItemData.getJSONArray("starting_equipment_options").getJSONObject(ii);
                    String choosenum = "Choose " + aa.getInt("choose");
                    JSONArray SEOJA = aa.getJSONArray("from");
                    for (int iii = 0; iii < SEOJA.length(); iii++) {
                        JSONObject bb = new JSONObject();
                        if (!SEOJA.getJSONObject(iii).isNull("quantity")) {
                            String name = SEOJA.getJSONObject(iii).getString("quantity") + " x " + SEOJA.getJSONObject(iii).getJSONObject("equipment").getString("name");
                            String url = SEOJA.getJSONObject(iii).getJSONObject("equipment").getString("url");
                            bb.put("name", name);
                            bb.put("url", url);
                            d.put(bb);
                        }
                    }
                }
            }

            CN.setText(a);
            PopulateLists(SE, d);
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
