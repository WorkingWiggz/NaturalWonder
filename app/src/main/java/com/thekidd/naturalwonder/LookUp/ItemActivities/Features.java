package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONException;

public class Features extends BasicItemActivity {
    TextView Fname, Cname, Desc, RLvl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_features);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        Fname = findViewById(R.id.FeatName);
        Cname = findViewById(R.id.ClassName);
        Desc = findViewById(R.id.DescText);
        RLvl = findViewById(R.id.ReqLevel);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            Fname.setText(ItemData.getString("name"));
            Cname.setText("Class: " + ItemData.getJSONObject("class").getString("name"));
            String c = "Required Level: " + ItemData.getString("level");
            RLvl.setText(c);
            String a = "";
            String NL = System.getProperty("line.separator");
            for (int i = 0; i < ItemData.getJSONArray("desc").length(); i++) {
                String b = ItemData.getJSONArray("desc").getString(i);
                if (i > 0) {
                    a = a + NL + b;
                } else {
                    a = b;
                }
            }
            Desc.setText(a);
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
