package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class MagicItems extends BasicItemActivity {
    TextView TitleText, DescText, EquipCatText;
    Button MenuButt, BackButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magic_items);
        TitleText = findViewById(R.id.TitleText);
        DescText = findViewById(R.id.DescText);
        EquipCatText = findViewById(R.id.EquipCatText);
        MenuButt = findViewById(R.id.MenuButt);
        BackButt = findViewById(R.id.BackButt);
        MenuButtonHandle(MenuButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws Exception {
        String Title = ItemData.getString("name");
        TitleText.setText(Title);

        JSONArray DescStrings = ItemData.getJSONArray("desc");
        String Desc = "";
        for (int i = 0; i < DescStrings.length(); i++) {
            String temp = DescStrings.getString(i);
            String NL = System.lineSeparator();
            if (i == DescStrings.length() - 1) {
                Desc = Desc + temp;
            } else {
                Desc = Desc + temp + NL;
            }
        }
        DescText.setText(Desc);

        String EC = ItemData.getJSONObject("equipment_category").getString("name");
        EquipCatText.setText(EC);
        EquipCatText.setOnClickListener(v -> {
            try {
                GetItem(ItemData.getJSONObject("equipment_category").getString("url"));
            } catch (JSONException e) {
                e.printStackTrace();
                ErrorHandle(e, getApplicationContext());
            }
        });

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
                                runOnUiThread(() -> {
                                    try {
                                        SortDataToItems();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        ErrorHandle(e, getApplicationContext());
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