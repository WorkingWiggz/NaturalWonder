package com.thekidd.naturalwonder.LookUp.ItemActivities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.thekidd.naturalwonder.LookUp.MyListView;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Levels extends BasicItemActivity {

    ArrayList<JSONObject> LevelsData = new ArrayList<>();
    TextView ClassName;
    EditText LevelNum;
    ConstraintLayout LevelLayout;
    Button MenuButt, BackButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        LevelNum = findViewById(R.id.ClassLevelEditText);
        ClassName = findViewById(R.id.ClassNameText);
        MenuButt = findViewById(R.id.MenuButt);
        BackButt = findViewById(R.id.BackButt);
        LevelLayout = findViewById(R.id.LevelLayout);

        MenuButtonHandle(MenuButt);
        SortBackButt(BackButt);
        LevelNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {

                    int a = Integer.parseInt(s.toString());
                    try {
                        if (a > 0 && a < 21) {
                            LoadLevelData(a);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        ErrorHandle(e, getApplicationContext());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    protected void SortDataToItems() throws Exception {
        SortToArrayList();
        String a = LevelsData.get(0).getJSONObject("class").getString("name");
        ClassName.setText(a);

        String b = String.valueOf(LevelsData.get(0).getInt("level"));
        LevelNum.setText(b, TextView.BufferType.EDITABLE);


    }

    private void LoadLevelData(int i) throws JSONException {
        LevelLayout.removeAllViews();
        ConstraintSet set = new ConstraintSet();
        JSONObject temp = LevelsData.get(i - 1);
        ArrayList<View> LayoutChildren = new ArrayList<>();

        String ProfBonus = "Proficiencies Bonus: " + temp.getInt("prof_bonus");
        TextView proftext = new TextView(this);
        proftext.setText(ProfBonus);
        proftext.setTextSize(24f);
        proftext.setId(View.generateViewId());
        LevelLayout.addView(proftext);
        set.clone(LevelLayout);
        set.connect(proftext.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        set.connect(proftext.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
        set.applyTo(LevelLayout);
        LayoutChildren.add(proftext);

        String AbilityScoreBonus = "Ability Score Bonus: " + temp.getString("ability_score_bonuses");
        TextView ASB = new TextView(this);
        ASB.setText(AbilityScoreBonus);
        ASB.setTextSize(24f);
        ASB.setId(View.generateViewId());
        LevelLayout.addView(ASB);
        set.clone(LevelLayout);
        set.connect(ASB.getId(), ConstraintSet.TOP, proftext.getId(), ConstraintSet.BOTTOM, 16);
        set.connect(ASB.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        set.connect(ASB.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
        set.applyTo(LevelLayout);
        LayoutChildren.add(ASB);

        // Spellcasting
        String Spellcasting = "Spellcasting";
        TextView SpellCast = new TextView(this);
        SpellCast.setId(View.generateViewId());
        SpellCast.setText(Spellcasting);
        SpellCast.setTextSize(28f);
        LevelLayout.addView(SpellCast);
        set.clone(LevelLayout);
        set.connect(SpellCast.getId(), ConstraintSet.TOP, ASB.getId(), ConstraintSet.BOTTOM, 16);
        set.connect(SpellCast.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        set.connect(SpellCast.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
        set.applyTo(LevelLayout);
        LayoutChildren.add(SpellCast);
        if (!temp.isNull("spellcasting")) {
            JSONArray spellcastingJA = temp.getJSONObject("spellcasting").names();
            ArrayList<String> Sorted = SortClassSpecifics(spellcastingJA);
            for (int x = 0; x < Sorted.size(); x++) {
                if (!temp.getJSONObject("spellcasting").isNull(spellcastingJA.getString(x))) {
                    TextView Spelltemp = new TextView(this);
                    String SpellStringTemp = Sorted.get(x) + ": " + temp.getJSONObject("spellcasting").getInt(spellcastingJA.getString(x));
                    Spelltemp.setText(SpellStringTemp);
                    Spelltemp.setTextSize(24f);
                    Spelltemp.setId(View.generateViewId());
                    LevelLayout.addView(Spelltemp);
                    set.clone(LevelLayout);
                    if (x == 0) {
                        set.connect(Spelltemp.getId(), ConstraintSet.TOP, SpellCast.getId(), ConstraintSet.BOTTOM, 16);
                    } else {
                        set.connect(Spelltemp.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
                    }
                    set.connect(Spelltemp.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
                    set.connect(Spelltemp.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
                    set.applyTo(LevelLayout);
                    LayoutChildren.add(Spelltemp);
                }
            }
        }


        /// Class Specifics
        TextView ClassSpecficLBL = new TextView(this);
        ClassSpecficLBL.setText("Class Specifics");
        ClassSpecficLBL.setId(View.generateViewId());
        ClassSpecficLBL.setTextSize(28f);
        LevelLayout.addView(ClassSpecficLBL);
        set.clone(LevelLayout);
        set.connect(ClassSpecficLBL.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
        set.connect(ClassSpecficLBL.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        set.connect(ClassSpecficLBL.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
        set.applyTo(LevelLayout);
        LayoutChildren.add(ClassSpecficLBL);


        JSONArray c = temp.getJSONObject("class_specific").names();
        ArrayList<String> e = SortClassSpecifics(c);
        for (int j = 0; j < c.length(); j++) {
            if (!temp.getJSONObject("class_specific").isNull(c.getString(j))) {
                TextView t = new TextView(this);
                String a = e.get(j) + ": " + temp.getJSONObject("class_specific").getString(c.getString(j));
                t.setText(a);
                t.setTextSize(24f);
                t.setId(View.generateViewId());
                LevelLayout.addView(t);
                set.clone(LevelLayout);
                if (j == 0) {
                    set.connect(t.getId(), ConstraintSet.TOP, ClassSpecficLBL.getId(), ConstraintSet.BOTTOM, 16);
                } else {
                    set.connect(t.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
                }
                set.connect(t.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
                set.connect(t.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
                set.applyTo(LevelLayout);
                LayoutChildren.add(t);
            }
        }

        if (!temp.isNull("subclass")) {
            TextView s = new TextView(this);
            String subclasslbl = "Possible Subclass";
            s.setText(subclasslbl);
            s.setTextSize(24f);
            s.setId(View.generateViewId());
            LevelLayout.addView(s);
            set.clone(LevelLayout);
            set.connect(s.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
            set.connect(s.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
            set.connect(s.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
            set.applyTo(LevelLayout);
            LayoutChildren.add(s);

            TextView t = new TextView(this);
            String subclasstext = temp.getJSONObject("subclass").getString("name");
            t.setTextSize(24f);
            t.setText(subclasstext);
            t.setId(View.generateViewId());
            t.setOnClickListener(v -> {
                try {
                    GetItem(temp.getJSONObject("subclass").getString("url"));
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                    ErrorHandle(jsonException, getApplicationContext());
                }
            });
            LevelLayout.addView(t);
            set.clone(LevelLayout);
            set.connect(t.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
            set.connect(t.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
            set.connect(t.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
            set.applyTo(LevelLayout);
            LayoutChildren.add(t);
        }

        if (!temp.isNull("subclass_specific")) {
            TextView s = new TextView(this);
            String subclassspecficlbl = "Subclass Specifics";
            s.setText(subclassspecficlbl);
            s.setTextSize(24f);
            s.setId(View.generateViewId());
            LevelLayout.addView(s);
            set.clone(LevelLayout);
            set.connect(s.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
            set.connect(s.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
            set.connect(s.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
            set.applyTo(LevelLayout);
            LayoutChildren.add(s);

            JSONArray tempSubsepc = temp.getJSONObject("subclass_specific").names();
            ArrayList<String> subclassStrings = SortClassSpecifics(tempSubsepc);
            for (int y = 0; y < tempSubsepc.length(); y++) {
                if (!temp.getJSONObject("subclass_specific").isNull(tempSubsepc.getString(y))) {
                    TextView SCS = new TextView(this);
                    String tmpname = subclassStrings.get(y) + ": " + temp.getJSONObject("subclass_specific").isNull(tempSubsepc.getString(y));
                    SCS.setText(tmpname);
                    SCS.setTextSize(24f);
                    SCS.setId(View.generateViewId());
                    LevelLayout.addView(SCS);
                    set.clone(LevelLayout);
                    set.connect(SCS.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
                    set.connect(SCS.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
                    set.connect(SCS.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
                    set.applyTo(LevelLayout);
                    LayoutChildren.add(SCS);
                }
            }
        }


        //Features
        TextView FeatsLBL = new TextView(this);
        String FeatureText = "Features";
        FeatsLBL.setId(View.generateViewId());
        FeatsLBL.setTextSize(28f);
        FeatsLBL.setText(FeatureText);
        LevelLayout.addView(FeatsLBL);
        set.clone(LevelLayout);
        set.connect(FeatsLBL.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 8);
        set.connect(FeatsLBL.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
        set.connect(FeatsLBL.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
        set.applyTo(LevelLayout);
        LayoutChildren.add(FeatsLBL);


        if (temp.getJSONArray("features").length() > 0) {
            MyListView Features = new MyListView(this);
            PopulateLists(Features, temp.getJSONArray("features"));
            Features.setId(View.generateViewId());
            LevelLayout.addView(Features);
            set.clone(LevelLayout);
            set.connect(Features.getId(), ConstraintSet.TOP, FeatsLBL.getId(), ConstraintSet.BOTTOM, 16);
            set.connect(Features.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
            set.connect(Features.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
            set.applyTo(LevelLayout);
            LayoutChildren.add(Features);
        }

        // Feature Choices
        if (temp.getJSONArray("feature_choices").length() > 0) {
            MyListView FeatChoices = new MyListView(this);
            PopulateLists(FeatChoices, temp.getJSONArray("feature_choices"));
            FeatChoices.setId(View.generateViewId());
            LevelLayout.addView(FeatChoices);
            set.clone(LevelLayout);
            set.connect(FeatChoices.getId(), ConstraintSet.TOP, LayoutChildren.get(LayoutChildren.size() - 1).getId(), ConstraintSet.BOTTOM, 16);
            set.connect(FeatChoices.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START, 16);
            set.connect(FeatChoices.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END, 16);
            set.applyTo(LevelLayout);
            LayoutChildren.add(FeatChoices);
        }

        View topview = LayoutChildren.get(0);
        set.clone(LevelLayout);
        set.connect(topview.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        set.applyTo(LevelLayout);

        View endview = LayoutChildren.get(LayoutChildren.size() - 1);
        set.clone(LevelLayout);
        set.connect(endview.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16);
        set.applyTo(LevelLayout);
    }

    private ArrayList<String> SortClassSpecifics(JSONArray c) throws JSONException {
        ArrayList<String> a = new ArrayList<>();
        for (int i = 0; i < c.length(); i++) {
            String b = c.getString(i);
            char[] d = b.toCharArray();
            String e = "";
            for (int j = 0; j < b.length(); j++) {
                if (j == 0) {
                    e = e + Character.toUpperCase(d[j]);
                } else {
                    if (d[j] == '_') {
                        e = e + " ";
                    } else if (d[j - 1] == '_') {
                        e = e + Character.toUpperCase(d[j]);
                    } else {
                        e = e + d[j];
                    }
                }
            }
            a.add(e);
        }
        return a;
    }

    private void SortToArrayList() throws JSONException {
        for (int i = 0; i < LevelData.length(); i++) {
            String a = LevelData.getJSONObject(i).getString("index");
            String b = a.split("-")[0];
            if (b.equals(LevelData.getJSONObject(i).getJSONObject("class").getString("name").toLowerCase())) {
                LevelsData.add(LevelData.getJSONObject(i));
            }
        }

        for (int i = 0; i < LevelsData.size(); i++) {
            for (int j = 0; j < LevelsData.size() - i - 1; j++) {
                int first = LevelsData.get(j).getInt("level");
                int second = LevelsData.get(j + 1).getInt("level");
                if (first > second) {
                    JSONObject temp = LevelsData.get(j);
                    LevelsData.set(j, LevelsData.get(j + 1));
                    LevelsData.set(j + 1, temp);
                }
            }
        }


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
                        if (LevelData != null) {
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