package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONException;

public class DamageTypes extends BasicItemActivity {

    TextView DMGText, DescText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_damage_types);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        DMGText = findViewById(R.id.DMGText);
        DescText = findViewById(R.id.DescText);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String name = ItemData.getString("name") + " Damage";
            DMGText.setText(name);
            String a = "";
            String NL = System.getProperty("line.separator");
            for (int i = 0; i < ItemData.getJSONArray("desc").length(); i++) {
                String b = ItemData.getJSONArray("desc").getString(i);
                if (i > 0) {
                    a = a + NL + NL + b;
                } else {
                    a = b;
                }
            }
            DescText.setText(a);
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
