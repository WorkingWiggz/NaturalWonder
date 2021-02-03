package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.thekidd.naturalwonder.LookUp.CustomListAdapter;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import static java.lang.Character.toUpperCase;

public class Classes extends BasicItemActivity {
    TextView ClassText, HitDice, ProfChoicesText, ProfClassText, SavingThrowsText, StartEquipText, SubclassesText, SpellCastText;
    ListView ProChoices, ProClassList, SubclassList, ClassLevelList, SavingThrowList, SpellCastingList;
    Boolean HasSpells = false;
    Button StartEquipButton, SpellsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
        ClassText = findViewById(R.id.classText);
        HitDice = findViewById(R.id.hitDice);
        ProfClassText = findViewById(R.id.profClassText);
        ProfChoicesText = findViewById(R.id.profChoicesText);
        SavingThrowsText = findViewById(R.id.savingThrowsText);
        StartEquipText = findViewById(R.id.startEquipText);
        SpellCastingList = findViewById(R.id.SpellCastingList);
        SubclassesText = findViewById(R.id.subclassesText);
        SpellCastText = findViewById(R.id.spellcastText);
        ProChoices = findViewById(R.id.ProChoices);
        ProClassList = findViewById(R.id.ProClassList);
        SubclassList = findViewById(R.id.SubclassList);
        SavingThrowList = findViewById(R.id.SavingThrowList);
        Button MenuButt = findViewById(R.id.MenuButt);
        StartEquipButton = findViewById(R.id.StartEquipButton);
        MenuButtonHandle(MenuButt);
        BackButt = findViewById(R.id.BackButt);
        SortBackButt(BackButt);
    }

    @Override
    protected void SortDataToItems() throws JSONException {
        try {
            String[] tmp = ItemData.getString("starting_equipment").split("/api/starting-equipment/");
            tmp[1] = toUpperCase(tmp[1].toCharArray()[0]) + tmp[1].substring(1);
            String FullText = tmp[1] + " Starting Equipment";
            StartEquipText.setText(FullText);
            StartEquipButton.setOnClickListener(v -> {
                try {
                    GetItem(ItemData.getString("starting_equipment"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, v.getContext());
                }
            });
            String d = ItemData.getString("name");
            ClassText.setText(d);
            String a = "1d" + ItemData.getString("hit_die") + " per level";
            HitDice.setText(a);
            String b = "Base Proficiencies";
            ProfClassText.setText(b);
            String c = "Selectable Proficiencies";
            ProfChoicesText.setText(c);

            String j = "Good Saving Throws";
            SavingThrowsText.setText(j);

            String k = tmp[1] + " Class Spells";
            SpellCastText.setText(k);

            String Sub = "Possible Subclasses";
            SubclassesText.setText(Sub);

            JSONArray La = ItemData.getJSONArray("proficiencies");
            PopulateLists(ProClassList, La);

            JSONArray Lb = ItemData.getJSONArray("saving_throws");
            PopulateLists(SavingThrowList, Lb);

            JSONArray Lc = ItemData.getJSONArray("subclasses");
            PopulateLists(SubclassList, Lc);

            JSONArray Ld = ItemData.getJSONArray("proficiency_choices");
            ArrayList<String> Arr = new ArrayList<String>();
            JSONArray Jb = null;
            JSONObject Ja;

            for (int i = 0; i < Ld.length(); i++) {
                Ja = Ld.getJSONObject(i);
                Jb = Ja.getJSONArray("from");
                for (int F = 0; F < Jb.length(); F++) {
                    JSONObject x = Jb.getJSONObject(F);
                    String y = x.getString("name");
                    Arr.add(y);
                }
            }
            CustomListAdapter ALC = new CustomListAdapter(this, Arr);
            ProChoices.setAdapter(ALC);
            final JSONArray finalJb = Jb;
            ProChoices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    OnClickHandleList(finalJb, position);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }

    }

    @Override
    protected void PreloadData() {
        synchronized (this) {
            while (true) {
                if (ItemData != null) {
                    break;
                }
            }
            try {
                FetchSpecificURL(ItemData.getString("spells"), queue, true);
            } catch (JSONException | UnsupportedEncodingException e) {
                e.printStackTrace();
                ErrorHandle(e, this);
            }
        }

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
                                    } catch (JSONException e) {
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

    protected void StartAssocaiedFunction(int pos) {
        runOnUiThread(() -> {
            switch (pos) {
                case 0:
                    LoadSpells(FetchedData.get(pos));
                    break;
            }
        });
    }

    private void LoadSpells(JSONObject SpellsData) {
        try {
            JSONArray ja = null;
            if (SpellsData.isNull("results")) {
                ArrayList<String> AL = new ArrayList<>();
                AL.add("This Class Has No Spells");
                CustomListAdapter adap = new CustomListAdapter(this, AL);
                SpellCastingList.setAdapter(adap);
                SpellCastingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast t = Toast.makeText(view.getContext(), "There are no spells for this class go check out some others.", Toast.LENGTH_SHORT);
                        t.show();
                    }
                });
            } else {
                ja = SpellsData.getJSONArray("results");
                PopulateLists(SpellCastingList, ja);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }
    }
}

