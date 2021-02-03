package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Spells extends BasicItemActivity {
    TextView NameText, DescText, HigherLevel, RangeText, CompText, MatsText, RitText,
            DurText, ConcText, CastText, LevelText;
    ListView SchoolList, ClassesList, SubClassesList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spells);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        NameText = findViewById(R.id.NameText);
        DescText = findViewById(R.id.DescText);
        HigherLevel = findViewById(R.id.HigherLevel);
        RangeText = findViewById(R.id.RangeText);
        CompText = findViewById(R.id.CompText);
        MatsText = findViewById(R.id.MatsText);
        RitText = findViewById(R.id.RitText);
        DurText = findViewById(R.id.DurText);
        ConcText = findViewById(R.id.ConcText);
        CastText = findViewById(R.id.CastText);
        LevelText = findViewById(R.id.LevelText);
        SchoolList = findViewById(R.id.SchoolList);
        ClassesList = findViewById(R.id.ClassesList);
        SubClassesList = findViewById(R.id.SubclassList);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            String b = ItemData.getJSONArray("desc").getString(0);
            String c;
            if (ItemData.getJSONArray("higher_level").length() > 0) {
                c = ItemData.getJSONArray("higher_level").getString(0);
            } else {
                c = "N/a";
            }

            String d;
            if (!ItemData.isNull("range")) {
                d = ItemData.getString("range");
            } else {
                d = "N/a";
            }
            String e = "";
            if (!ItemData.isNull("components")) {
                JSONArray ee = ItemData.getJSONArray("components");

                for (int ii = 0; ii < ee.length(); ii++) {
                    if (ii != ee.length() - 1) {
                        e = e + ee.getString(ii);
                    } else {
                        e = e + ee.getString(ii) + " , ";
                    }
                }
            }

            String f;
            if (!ItemData.isNull("material")) {
                f = ItemData.getString("material");
            } else {
                f = "N/a";
            }

            String g;
            if (!ItemData.isNull("ritual")) {
                g = ItemData.getString("ritual");
            } else {
                g = "N/a";
            }

            String h;
            if (!ItemData.isNull("duration")) {
                h = ItemData.getString("duration");
            } else {
                h = "N/a";
            }
            String i;
            if (!ItemData.isNull("casting_time")) {
                i = ItemData.getString("casting_time");
            } else {
                i = "";
            }
            String z;
            if (!ItemData.isNull("concentration")) {
                z = ItemData.getString("concentration");
            } else {
                z = "N/a";
            }
            String j = "Required Level: " + ItemData.getString("level");


            JSONObject kk = ItemData.getJSONObject("school");
            JSONArray k = new JSONArray();
            k.put(kk);
            JSONArray l = ItemData.getJSONArray("classes");
            JSONArray m = ItemData.getJSONArray("subclasses");


            NameText.setText(a);
            DescText.setText(b);
            HigherLevel.setText(c);
            RangeText.setText(d);
            CompText.setText(e);
            MatsText.setText(f);
            RitText.setText(g);
            DurText.setText(h);
            CastText.setText(i);
            LevelText.setText(j);
            PopulateLists(SchoolList, k);
            PopulateLists(ClassesList, l);
            PopulateLists(SubClassesList, m);
            ConcText.setText(z);
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
