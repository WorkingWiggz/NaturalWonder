package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Languages extends BasicItemActivity {
    TextView TitleText, TypeText, TypicalSpeakerText, ScriptText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
        Button MenuButt = findViewById(R.id.MenuButt);
        MenuButtonHandle(MenuButt);
        TitleText = findViewById(R.id.TitleText);
        TypeText = findViewById(R.id.TypeText);
        TypicalSpeakerText = findViewById(R.id.TypicalSpeakerText);
        ScriptText = findViewById(R.id.ScriptText);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String a = ItemData.getString("name");
            String b = "Type: " + ItemData.getString("type");
            String c = ItemData.getString("script");

            TitleText.setText(a);
            TypeText.setText(b);
            ScriptText.setText(c);
            JSONArray x = ItemData.getJSONArray("typical_speakers");
            String d = "";
            String NL = System.lineSeparator();
            for (int i = 0; i < x.length(); i++) {
                String g = x.getString(i);
                d = d + g + NL;
            }
            TypicalSpeakerText.setText(d);
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
