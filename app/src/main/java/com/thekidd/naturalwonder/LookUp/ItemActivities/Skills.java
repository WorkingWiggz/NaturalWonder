package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Skills extends BasicItemActivity {

    TextView Title, Desc;
    ListView Effected;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        Title = findViewById(R.id.TitleText);
        Desc = findViewById(R.id.DescText);
        Effected = findViewById(R.id.EffectedStatList);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            String b = "";
            JSONArray DescJA = ItemData.getJSONArray("desc");
            for (int i = 0; i < DescJA.length(); i++) {
                if (i == 0) {
                    b = b + DescJA.getString(i);
                } else {
                    b = b + System.lineSeparator() + DescJA.getString(i);
                }
            }
            Title.setText(a);
            Desc.setText(b);
            JSONObject c = ItemData.getJSONObject("ability_score");
            JSONArray d = new JSONArray();
            d.put(c);
            PopulateLists(Effected, d);
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
