package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Proficiencies extends BasicItemActivity {
    TextView TitleText, TypeText, RacesText, ClassesText;
    ListView ClassList, RaceList, RefList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proficiencies);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleText = findViewById(R.id.TitleText);
        TypeText = findViewById(R.id.TypeText);
        RacesText = findViewById(R.id.RacesText);
        ClassesText = findViewById(R.id.ClassText);
        ClassList = findViewById(R.id.ClassList);
        RaceList = findViewById(R.id.RaceList);
        BackButt = findViewById(R.id.BackButt);
        RefList = findViewById(R.id.RefList);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            TitleText.setText(a);

            String b = ItemData.getString("type");
            TypeText.setText("Type: " + b);

            JSONArray c = ItemData.getJSONArray("classes");
            String NL = System.getProperty("line.separator");
            if (c.length() == 0) {
                String e = "N/a";
                ClassesText.setText(e);
            } else {
                ClassesText.setText("");
                PopulateLists(ClassList, c);
            }

            JSONArray d = ItemData.getJSONArray("races");
            if (d.length() == 0) {
                String e = "N/a";
                RacesText.setText(e);
            } else {
                RacesText.setText("");
                PopulateLists(RaceList, d);
            }

            JSONArray f = ItemData.getJSONArray("references");
            PopulateLists(RefList, f);
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
