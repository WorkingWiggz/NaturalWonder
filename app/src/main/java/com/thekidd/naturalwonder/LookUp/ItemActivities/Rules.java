package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Rules extends BasicItemActivity {

    TextView Title, desc;
    ListView RulesSections;
    Button Back, Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Title = findViewById(R.id.titleText);
        desc = findViewById(R.id.DescText);
        RulesSections = findViewById(R.id.RulesSubsectionList);
        Back = findViewById(R.id.backButt);
        Menu = findViewById(R.id.menuButt);
        SortBackButt(Back);
        MenuButtonHandle(Menu);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        Title.setText(ItemData.getString("name"));
        desc.setText(ItemData.getString("desc"));
        JSONArray subsections = ItemData.getJSONArray("subsections");
        PopulateLists(RulesSections, subsections);
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