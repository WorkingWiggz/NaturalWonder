package com.thekidd.naturalwonder.CharacterSheets;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SetupCharacter extends BaseNWActivity {
    private final ArrayList<Integer> ChoiceCount = new ArrayList<>();
    private final ArrayList<TextView> ChoiceTexts = new ArrayList<>();
    ArrayList<CheckBox> SkillsList = new ArrayList<>();
    ArrayList<ArrayList<CheckBox>> EquipmentChoices = new ArrayList<>();
    Button RollStats, RollHP, FinalChar, Back;
    EditText NameText;
    int SkillLimit = 0;
    LinearLayout layout;
    int NumOfChoices = -1;
    int color;
    RadioGroup Races, Classes, Alignments, CharBacks, LifeStyle;
    EditText HPTB, STRTB, WISTB, CHATB, INTTB, CONTB, DEXTB;
    ArrayList<JSONObject> StartEquipData = new ArrayList<>();
    ArrayList<ArrayList<View>> LLS = new ArrayList<>();
    ArrayList<String> AvailableProfs = new ArrayList<>();
    ArrayAdapter ArrayAdapterSpinner;
    Thread LoadData;
    ArrayList<String> BackgroundList = new ArrayList<>();
    TextView CET;
    int HitDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_character);
        HitDice = 0;
        layout = findViewById(R.id.ListLayout);
        if (ThemeMode) {
            color = getResources().getColor(R.color.LightMode_Text);
        } else {
            color = getResources().getColor(R.color.DarkMode_Text);
        }
        LifeStyle = findViewById(R.id.LifeStyleGroup);
        LoadData = new Thread() {
            public void run() {
                Context c = SetupCharacter.this;
                StringRequest Barb, Bard, Cleric, Druid, Monk, Fighter, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard;
                Barb = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/barbarian", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Bard = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/bard", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Cleric = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/cleric", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Druid = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/druid", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Monk = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/fighter", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Fighter = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/monk", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Paladin = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/paladin", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Ranger = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/ranger", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Rogue = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/rogue", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Sorcerer = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/sorcerer", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Warlock = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/warlock", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                Wizard = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/wizard", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            StartEquipData.add(new JSONObject(response));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                try {
                    HandleRequest(Barb, queue);
                    HandleRequest(Bard, queue);
                    HandleRequest(Cleric, queue);
                    HandleRequest(Druid, queue);
                    HandleRequest(Monk, queue);
                    HandleRequest(Fighter, queue);
                    HandleRequest(Paladin, queue);
                    HandleRequest(Ranger, queue);
                    HandleRequest(Rogue, queue);
                    HandleRequest(Sorcerer, queue);
                    HandleRequest(Warlock, queue);
                    HandleRequest(Wizard, queue);
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, getApplicationContext());
                }
            }
        };
        LoadData.start();

        SkillsList.clear();
        for (int i = 3; i < 21; i++) {
            String a = "checkBox" + i;
            int RESID = getResources().getIdentifier(a, "id", getPackageName());
            CheckBox b = findViewById(RESID);
            SkillsList.add(b);
        }

        for (int i = 0; i < SkillsList.size(); i++) {
            SkillsList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean tmpa = ProfCheck();
                    if (!tmpa) {
                        CheckBox a = (CheckBox) v;
                        a.setChecked(false);
                    }
                }
            });
        }

        NameText = findViewById(R.id.DescText);
        RollStats = findViewById(R.id.RollStatsButt);
        RollHP = findViewById(R.id.HPRollButt);
        FinalChar = findViewById(R.id.FinalizeButt);
        Back = findViewById(R.id.BackToSheets);

        Races = findViewById(R.id.RaceGroup);
        Classes = findViewById(R.id.ClassGroup);
        Alignments = findViewById(R.id.AlignmentGroup);
        HPTB = findViewById(R.id.HitPointTextBox);
        STRTB = findViewById(R.id.StrTB);
        WISTB = findViewById(R.id.WisTB);
        CHATB = findViewById(R.id.ChaTB);
        INTTB = findViewById(R.id.IntTB);
        CONTB = findViewById(R.id.ConTB);
        DEXTB = findViewById(R.id.DexTB);
        CET = findViewById(R.id.ClassEquipText);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BacktoEditor();
            }
        });

        FinalChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FinalizeCharacter();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        });

        RollHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    RollHealth();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        RollStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandStats();
            }
        });

        Classes.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int a = 0;
                RadioButton g = findViewById(Classes.getCheckedRadioButtonId());
                String b = g.getText().toString();
                switch (b) {
                    case "Barbarian":
                        a = 0;
                        break;
                    case "Bard":
                        a = 1;
                        break;
                    case "Cleric":
                        a = 2;
                        break;
                    case "Druid":
                        a = 3;
                        break;
                    case "Monk":
                        a = 4;
                        break;
                    case "Fighter":
                        a = 5;
                        break;
                    case "Paladin":
                        a = 6;
                        break;
                    case "Ranger":
                        a = 7;
                        break;
                    case "Rogue":
                        a = 8;
                        break;
                    case "Sorcerer":
                        a = 9;
                        break;
                    case "Warlock":
                        a = 10;
                        break;
                    case "Wizard":
                        a = 11;
                        break;
                }
                try {
                    LoadStartEquipment(b);
                    LoadProficiencies(a);
                    SetHitDice(a);
                } catch (Exception e) {
                    ErrorHandle(e, group.getContext());
                }

            }
        });

        Races.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


            }
        });

    }

    private boolean ProfCheck() {
        boolean hold = false;
        Toast NoRaceSelected = Toast.makeText(getApplicationContext(), "No race selected. Please select a race above.", Toast.LENGTH_SHORT);
        Toast TooManyProfs = Toast.makeText(getApplicationContext(), "Too many proficiencies selected. Uncheck one to check this one.", Toast.LENGTH_SHORT);
        if (Races.getCheckedRadioButtonId() != -1) {
            int CheckedCount = 0;
            for (int i = 0; i < SkillsList.size(); i++) {
                if (SkillsList.get(i).isChecked()) {
                    CheckedCount++;
                }
            }
            if (CheckedCount <= SkillLimit) {
                hold = true;
            } else {
                TooManyProfs.show();
            }
        } else {
            NoRaceSelected.show();
        }
        return hold;
    }

    private void LoadProficiencies(int a) {
        AvailableProfs.clear();
        switch (a) {
            case 0:
                BarbarianProfs();
                break;
            case 1:
                BardProfs();
                break;
            case 2:
                ClericProfs();
                break;
            case 3:
                DruidProfs();
                break;
            case 4:
                FighterProfs();
                break;
            case 5:
                MonkProfs();
                break;
            case 6:
                PaladinProfs();
                break;
            case 7:
                RangerProfs();
                break;
            case 8:
                RogueProfs();
                break;
            case 9:
                SorcererProfs();
                break;
            case 10:
                WarlockProfs();
                break;
            case 11:
                WizardProfs();
                break;
        }
        if (!(AvailableProfs.get(0).equals("Bard"))) {
            for (int i = 0; i < SkillsList.size(); i++) {
                String b = (String) SkillsList.get(i).getText();
                boolean inlist = false;
                for (int j = 0; j < AvailableProfs.size(); j++) {
                    if (b.equals(AvailableProfs.get(j))) {
                        inlist = true;
                    }
                }
                if (inlist) {
                    SkillsList.get(i).setClickable(true);
                    SkillsList.get(i).setVisibility(View.VISIBLE);
                } else {
                    SkillsList.get(i).setClickable(false);
                    SkillsList.get(i).setVisibility(View.INVISIBLE);
                }
            }
        } else {
            for (int i = 0; i < SkillsList.size(); i++) {
                SkillsList.get(i).setClickable(true);
                SkillsList.get(i).setVisibility(View.VISIBLE);
            }

        }
    }

    private void WizardProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Arcana");
        AvailableProfs.add("History");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Investigation");
        AvailableProfs.add("Medicine");
        AvailableProfs.add("Religion");
    }

    private void WarlockProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Arcana");
        AvailableProfs.add("Deception");
        AvailableProfs.add("History");
        AvailableProfs.add("Intimidation");
        AvailableProfs.add("Investigation");
        AvailableProfs.add("Religion");
        AvailableProfs.add("Nature");

    }

    private void SorcererProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Arcana");
        AvailableProfs.add("Deception");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Intimidation");
        AvailableProfs.add("Persiasion");
        AvailableProfs.add("Religion");

    }

    private void RogueProfs() {
        SkillLimit = 4;
        AvailableProfs.add("Athletics");
        AvailableProfs.add("Deception");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Investigation");
        AvailableProfs.add("Performance");
        AvailableProfs.add("Perception");
        AvailableProfs.add("Persuasion");
        AvailableProfs.add("Sleight of Hand");
        AvailableProfs.add("Stealth");
    }

    private void RangerProfs() {
        SkillLimit = 3;
        AvailableProfs.add("Animal Handling");
        AvailableProfs.add("Athletics");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Investigation");
        AvailableProfs.add("Nature");
        AvailableProfs.add("Perception");
        AvailableProfs.add("Survival");
        AvailableProfs.add("Stealth");
    }

    private void PaladinProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Athletics");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Intimidation");
        AvailableProfs.add("Medicine");
        AvailableProfs.add("Persuasion");
        AvailableProfs.add("Religion");
    }

    private void MonkProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Acrobatics");
        AvailableProfs.add("Athletics");
        AvailableProfs.add("History");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Religion");
        AvailableProfs.add("Stealth");
    }

    private void FighterProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Acrobatics");
        AvailableProfs.add("Athletics");
        AvailableProfs.add("Animal Handling");
        AvailableProfs.add("Intimidation");
        AvailableProfs.add("Perception");
        AvailableProfs.add("Insight");
        AvailableProfs.add("History");
        AvailableProfs.add("Survival");
    }

    private void DruidProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Arcana");
        AvailableProfs.add("Animal Handling");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Nature");
        AvailableProfs.add("Perception");
        AvailableProfs.add("Religion");
        AvailableProfs.add("Survival");

    }

    private void ClericProfs() {
        AvailableProfs.add("History");
        AvailableProfs.add("Insight");
        AvailableProfs.add("Medicine");
        AvailableProfs.add("Persuasion");
        AvailableProfs.add("Religion");
        SkillLimit = 2;
    }

    private void BardProfs() {
        SkillLimit = 3;
        AvailableProfs.add("Bard");
    }

    private void BarbarianProfs() {
        SkillLimit = 2;
        AvailableProfs.add("Athletics");
        AvailableProfs.add("Animal Handling");
        AvailableProfs.add("Intimidation");
        AvailableProfs.add("Nature");
        AvailableProfs.add("Perception");
        AvailableProfs.add("Survival");
    }

    private void SetHitDice(int checkedRadioButtonId) {
        switch (checkedRadioButtonId) {
            case 0:
                HitDice = 12;
                break;

            case 1:
            case 2:
            case 3:
            case 5:
            case 8:
            case 10:
                HitDice = 8;
                break;

            case 4:
            case 6:
            case 7:
                HitDice = 10;
                break;

            case 9:
            case 11:
                HitDice = 6;
                break;

        }
        TextView text = findViewById(R.id.HitDiceType);
        String a = "d" + HitDice;
        text.setText(a);
    }

    private void RandStats() {
        ArrayList<Integer> rolls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            rolls.add(RollAbilityNum());
        }
        rolls = BubbleSort(rolls);
        Boolean STR = false, WIS = false, INT = false, CHA = false, CON = false, DEX = false;
        for (int i = 0; i < rolls.size(); i++) {
            int rand = GetRandomInt(1, 6);
            switch (rand) {
                case 1: //STR
                    if (!STR) {
                        STRTB.setText(String.valueOf(rolls.get(i)));
                        STR = true;
                    } else {
                        i--;
                    }
                    break;
                case 2: // DEX
                    if (!DEX) {
                        DEXTB.setText(String.valueOf(rolls.get(i)));
                        DEX = true;
                    } else {
                        i--;
                    }
                    break;
                case 3: // WIS
                    if (!WIS) {
                        WISTB.setText(String.valueOf(rolls.get(i)));
                        WIS = true;
                    } else {
                        i--;
                    }
                    break;
                case 4: // CON
                    if (!CON) {
                        CONTB.setText(String.valueOf(rolls.get(i)));
                        CON = true;
                    } else {
                        i--;
                    }
                    break;
                case 5: // INT
                    if (!INT) {
                        INTTB.setText(String.valueOf(rolls.get(i)));
                        INT = true;
                    } else {
                        i--;
                    }
                    break;
                case 6: // CHA
                    if (!CHA) {
                        CHATB.setText(String.valueOf(rolls.get(i)));
                        CHA = true;
                    } else {
                        i--;
                    }
                    break;
            }
        }
    }

    private Integer RollAbilityNum() {
        int AbilityNum = 0;
        ArrayList<Integer> Rolls = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Rolls.add(GetRandomInt(1, 6));
        }
        Rolls = BubbleSort(Rolls);
        Rolls.remove(0);
        int a = 0;
        for (int i = 0; i < Rolls.size(); i++) {
            a = a + Rolls.get(i);
        }
        AbilityNum = a;
        return AbilityNum;
    }

    private ArrayList<Integer> BubbleSort(ArrayList<Integer> rolls) {
        for (int i = 0; i < rolls.size(); i++) {
            for (int j = 0; j < rolls.size() - 1; j++) {
                if (rolls.get(j) > rolls.get(j + 1)) {
                    int tmp = rolls.get(j);
                    rolls.set(j, rolls.get(j + 1));
                    rolls.set(j + 1, tmp);
                }
            }
        }
        return rolls;
    }


    private void RollHealth() throws Exception {
        Toast UnsetError = Toast.makeText(getApplicationContext(), "Class not Chosen please select class.", Toast.LENGTH_SHORT);
        if (HitDice == 0 || Classes.getCheckedRadioButtonId() == -1) {
            UnsetError.show();
        } else if (HitDice == 6 || HitDice == 8 || HitDice == 10 || HitDice == 12) {
            int ConBo = (Integer.parseInt(CONTB.getText().toString()) - 10) / 2;
            int Random = GetRandomInt(1, HitDice);
            String full = Random + " (" + (Random + ConBo) + ")";
            HPTB.setText(full);
        } else {
            throw new Exception("Wrong hit dice type");
        }
    }

    private void BacktoEditor() {
        Intent i = new Intent(this, SheetsActivity.class);
        startActivity(i);
    }

    private void FinalizeCharacter() throws JSONException, IOException {
        boolean First, Second, Third, Fourth = false, Fifth, Sixth, Name;
        RadioGroup r = findViewById(R.id.RaceGroup);
        RadioGroup a = findViewById(R.id.ClassGroup);
        RadioGroup d = findViewById(R.id.AlignmentGroup);
        ArrayList<String> ChosenEquips = new ArrayList<>();

        LinearLayout lay = findViewById(R.id.ListLayout);

        //Name Section Check
        EditText nametext = findViewById(R.id.DescText);
        Name = !(nametext.getText().equals(""));

        //First Section Check
        First = r.getCheckedRadioButtonId() != -1;

        //Second Section Check
        Second = (a.getCheckedRadioButtonId() != -1) && (!HPTB.getText().toString().equals(""));

        //Third Section Check
        Third = (!STRTB.getText().toString().equals("")) && (!CHATB.getText().toString().equals("")) && (!INTTB.getText().toString().equals("")) && (!DEXTB.getText().toString().equals("")) && (!CONTB.getText().toString().equals("")) && (!WISTB.getText().toString().equals(""));

        //Fourth Section Check
        for (int i = 0; i < SkillsList.size(); i++) {
            CheckBox b = SkillsList.get(i);
            if (b.isChecked()) {
                Fourth = true;
            }
        }

        //Fifth Section Check
        Fifth = d.getCheckedRadioButtonId() != -1 && LifeStyle.getCheckedRadioButtonId() != -1;

        //Sixth Section Check
        int validatorcount = 0;
        for (int i = 0; i < EquipmentChoices.size(); i++) {
            boolean AnyChecked = false;
            for (int j = 0; j < EquipmentChoices.get(i).size(); j++) {
                CheckBox b = EquipmentChoices.get(i).get(j);
                if (b.isChecked()) {
                    AnyChecked = true;
                    ChosenEquips.add(b.getText().toString());
                    break;
                }
            }
            if (AnyChecked) {
                validatorcount++;
            }
        }
        int sum = 0;
        for (int i = 0; i < ChoiceCount.size(); i++) {
            sum += ChoiceCount.get(i);
        }
        Sixth = validatorcount == sum;

        if (First && Second && Third && Fourth && Fifth && Sixth && Name) {
            String name, Race, Class, Alignment, Equips, CharBack, LifeStyle;
            int LVL, HP, STR, DEX, CHA, CON, INT, WIS;
            JSONArray Skills = new JSONArray();
            JSONObject CharacterFile = new JSONObject();

            if (!NameText.getText().toString().equals("")) {
                name = NameText.getText().toString();
            } else {
                name = "Empty";
            }


            LVL = 1;


            if (!HPTB.getText().toString().equals("")) {
                HP = Integer.parseInt(HPTB.getText().toString().split(" ")[0]);
            } else {
                HP = 1;
            }

            if (!STRTB.getText().toString().equals("")) {
                STR = Integer.parseInt(STRTB.getText().toString());
            } else {
                STR = 10;
            }

            if (!DEXTB.getText().toString().equals("")) {
                DEX = Integer.parseInt(DEXTB.getText().toString());
            } else {
                DEX = 10;
            }

            if (!CHATB.getText().toString().equals("")) {
                CHA = Integer.parseInt(CHATB.getText().toString());
            } else {
                CHA = 10;
            }

            if (!CONTB.getText().toString().equals("")) {
                CON = Integer.parseInt(CONTB.getText().toString());
            } else {
                CON = 10;
            }

            if (!INTTB.getText().toString().equals("")) {
                INT = Integer.parseInt(INTTB.getText().toString());
            } else {
                INT = 10;
            }

            if (!WISTB.getText().toString().equals("")) {
                WIS = Integer.parseInt(WISTB.getText().toString());
            } else {
                WIS = 10;
            }

            for (int i = 0; i < SkillsList.size(); i++) {
                CheckBox b = SkillsList.get(i);
                if (b.isChecked()) {
                    Skills.put(b.getText().toString());
                }
            }

            if (Alignments.getCheckedRadioButtonId() != -1) {
                RadioButton b = findViewById(Alignments.getCheckedRadioButtonId());
                Alignment = b.getText().toString();
            } else {
                Alignment = "True Neutral";
            }

            if (!CET.getText().toString().equals("")) {
                Equips = CET.getText().toString();
            } else {
                Equips = "";
            }


            if (Races.getCheckedRadioButtonId() != -1) {
                RadioButton b = findViewById(Races.getCheckedRadioButtonId());
                Race = b.getText().toString();
            } else {
                Race = "Human";
            }

            if (Classes.getCheckedRadioButtonId() != -1) {
                RadioButton b = findViewById(Classes.getCheckedRadioButtonId());
                Class = b.getText().toString();
            } else {
                Class = "Fighter";
            }

            if (HitDice != 0) {
                CharacterFile.put("HitDice", HitDice);
            }


            if (this.LifeStyle.getCheckedRadioButtonId() != -1) {
                RadioButton b = findViewById(this.LifeStyle.getCheckedRadioButtonId());
                LifeStyle = b.getText().toString();
            } else {
                LifeStyle = "Wretched";
            }

            JSONArray Classes = new JSONArray();
            JSONObject ClassObject = new JSONObject();
            ClassObject.put("name", Class);
            ClassObject.put("level", LVL);
            Classes.put(ClassObject);


            CharacterFile.put("Name", name);
            CharacterFile.put("Race", Race);
            CharacterFile.put("MaxHealth", HP);
            CharacterFile.put("STR", STR);
            CharacterFile.put("DEX", DEX);
            CharacterFile.put("CHA", CHA);
            CharacterFile.put("CON", CON);
            CharacterFile.put("INT", INT);
            CharacterFile.put("WIS", WIS);
            CharacterFile.put("Alignment", Alignment);
            CharacterFile.put("Equips", Equips);
            CharacterFile.put("ChosenEquips", ChosenEquips);
            CharacterFile.put("Skills", Skills);
            CharacterFile.put("ClassesData", Classes);
            CharacterFile.put("LifeStyle", LifeStyle);
            CharacterFile.put("Init", false);

            File Dir = new File(getApplicationContext().getExternalFilesDir(null) + "/NaturalWonder/CharacterSheets/");

            if (!Dir.exists()) {
                Dir.mkdirs();
            }
            String fileName = name + ".json";
            File CharJSON = new File(Dir, fileName);
            if (!CharJSON.exists()) {
                boolean p = CharJSON.createNewFile();
                if (p) {
                    FileWriter fw = new FileWriter(CharJSON);
                    fw.write(CharacterFile.toString());
                    fw.flush();
                    fw.close();
                }
                Intent i = new Intent(this, CharacterSheetsEditor.class);
                i.putExtra("FilePath", CharJSON.getAbsolutePath());
                startActivity(i);
            } else {
                int xx = 1;
                while (true) {
                    fileName = fileName.split(".json")[0] + xx + ".json";
                    CharJSON = new File(Dir, fileName);
                    if (!CharJSON.exists()) {
                        boolean p = CharJSON.createNewFile();
                        if (p) {
                            FileWriter fw = new FileWriter(CharJSON);
                            fw.write(CharacterFile.toString());
                            fw.flush();
                            fw.close();
                        }
                        Intent i = new Intent(this, CharacterSheetsEditor.class);
                        i.putExtra("FilePath", CharJSON.getAbsolutePath());
                        startActivity(i);
                        break;
                    }
                }


            }

        } else {
            try {
                if (!Name) {
                    Toast toast = Toast.makeText(this, "Please name your character.", Toast.LENGTH_LONG);
                    toast.show();
                } else if (!First) {
                    Toast toast = Toast.makeText(this, "Please select your character race.", Toast.LENGTH_LONG);
                    toast.show();
                } else if (!Second) {
                    Toast toast = Toast.makeText(this, "Please select your character class.", Toast.LENGTH_LONG);
                    toast.show();
                } else if (!Third) {
                    Toast toast = Toast.makeText(this, "Please roll ability scores, the level and the HP of your character.", Toast.LENGTH_LONG);
                    toast.show();
                } else if (!Fourth) {
                    Toast toast = Toast.makeText(this, "Please select your character's proficient skills.", Toast.LENGTH_LONG);
                    toast.show();
                } else if (!Fifth) {
                    Toast toast = Toast.makeText(this, "Please select your character's alignment and lifestyle.", Toast.LENGTH_LONG);
                    toast.show();
                } else if (!Sixth) {
                    Toast toast = Toast.makeText(this, "Please select your character's equipment", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    throw new Exception("Error Within Char Finalization: Toast Section.");
                }
            } catch (Exception e) {
                ErrorHandle(e, this);
            }
        }
    }

    private void LoadStartEquipment(String ClassString) throws Exception {
        JSONObject Data = FetchStartEquipment(ClassString);
        if (Data.has("starting_equipment")) {
            JSONArray a = Data.getJSONArray("starting_equipment");
            String e = "";
            for (int i = 0; i < a.length(); i++) {
                String b = a.getJSONObject(i).getJSONObject("equipment").getString("name");
                int c = a.getJSONObject(i).getInt("quantity");
                String d = c + " x " + b;
                String NL = System.getProperty("line.separator");
                if (i == 0) {
                    e = d + NL;
                } else {
                    e = e + d + NL;
                }
                CET.setText(e);
            }
        }


        layout.removeAllViews();
        EquipmentChoices.clear();
        ChoiceCount.clear();
        LLS.clear();
        ChoiceTexts.clear();

        try {
            if (!Data.isNull("starting_equipment_options")) {
                JSONArray JA = Data.getJSONArray("starting_equipment_options");
                for (int i = 0; i < JA.length(); i++) {
                    String Choice = String.format("Choice %d: ", (i + 1));
                    ChoiceCount.add(JA.getJSONObject(i).getInt("choose"));
                    Choice = Choice + "(Choose: " + JA.getJSONObject(i).getInt("choose") + ")";
                    TextView t = new TextView(layout.getContext());
                    t.setText(Choice);
                    t.setTextSize(25f);
                    ChoiceTexts.add(t);
                    LLS.add(new ArrayList<View>());
                    LLS.get(i).add(t);
                    EquipmentChoices.add(new ArrayList<CheckBox>());
                    JSONObject TmpA = null;
                    JSONArray TmpB = new JSONArray();
                    try {
                        TmpB = JA.getJSONObject(i).getJSONArray("from");
                    } catch (Exception e) {
                        TmpA = JA.getJSONObject(i).getJSONObject("from");
                    }
                    if (TmpA != null) {
                        HandleAsEquipmentOption(TmpA, i);
                    } else {
                        for (int j = 0; j < TmpB.length(); j++) {
                            JSONArray TmpC;
                            try {
                                String x = TmpB.getJSONObject(j).names().getString(0);
                                boolean isNumber;
                                try {
                                    Integer.parseInt(x);
                                    isNumber = true;
                                } catch (Exception e) {
                                    isNumber = false;
                                }

                                if (x.equals("equipment")) {
                                    HandleAsEquipment(TmpB.getJSONObject(j), i);
                                } else if (isNumber) {
                                    String fullSEOname = "";
                                    for (int xx = 0; xx < TmpB.getJSONObject(j).length(); xx++) {
                                        JSONObject equip = TmpB.getJSONObject(j).getJSONObject(String.valueOf(xx));
                                        String equipname = equip.names().getString(0);
                                        if (equipname.equals("equipment")) {
                                            String SEOName = equip.getInt("quantity") + " x " + equip.getJSONObject("equipment").getString("name");
                                            if (xx == 0) {
                                                fullSEOname = fullSEOname + SEOName;
                                            } else {
                                                fullSEOname = fullSEOname + System.lineSeparator() + SEOName;
                                            }
                                            LLS.get(i).add(BuildCheckBox(fullSEOname, i));
                                        } else {
                                            JSONObject S = equip.getJSONObject("equipment_option").getJSONObject("from");
                                            HandleAsEquipmentOption(S, i);
                                        }
                                    }

                                } else {
                                    HandleAsEquipmentOption(TmpB.getJSONObject(j), i);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                TmpC = TmpB.getJSONArray(j);
                                HandleAsComboEquipment(TmpC, i);
                            }
                        }
                    }

                }

            }
        } catch (JSONException ex) {
            ex.printStackTrace();
            ErrorHandle(ex, this);
        }
    }

    private void SearchForCheckboxes() {
        EquipmentChoices.clear();
        int count = layout.getChildCount();
        ArrayList<CheckBox> tmp = new ArrayList();
        for (int i = 0; i < count; i++) {
            if (layout.getChildAt(i).getClass() == TextView.class) {
                if (tmp.size() != 0) {
                    EquipmentChoices.add(tmp);
                    tmp = new ArrayList<>();
                }
            } else if (layout.getChildAt(i).getClass() == CheckBox.class) {
                tmp.add((CheckBox) layout.getChildAt(i));
            } else if (i == count - 1) {
                if (layout.getChildAt(i).getClass() == CheckBox.class) {
                    tmp.add((CheckBox) layout.getChildAt(i));
                }
                EquipmentChoices.add(tmp);
            }

        }
    }

    private void HandleAsComboEquipment(JSONArray tmpC, int Choicepos) throws JSONException {
        String Fullname = "";
        for (int i = 0; i < tmpC.length(); i++) {
            JSONObject JO = tmpC.getJSONObject(i);
            int quantity;
            if (JO.has("quantity")) {
                quantity = JO.getInt("quantity");
            } else {
                quantity = 1;
            }
            String s = "";
            if (JO.has("equipment")) {
                s = quantity + " x " + JO.getJSONObject("equipment").getString("name");
            } else {
                HandleAsEquipmentOption(JO, Choicepos);
            }
            if (i == JO.length() - 1) {
                Fullname += s;
            } else {
                Fullname += s + "\n";
            }
        }
        LLS.get(Choicepos).add(BuildCheckBox(Fullname, Choicepos));
    }

    private void HandleAsEquipment(JSONObject jsonObject, int Choicepos) throws JSONException {
        int qauntity;
        if (jsonObject.has("quantity")) {
            qauntity = jsonObject.getInt("quantity");
        } else {
            qauntity = 1;
        }
        String s = qauntity + " x " + jsonObject.getJSONObject("equipment").getString("name");
        LLS.get(Choicepos).add(BuildCheckBox(s, Choicepos));
    }

    private void HandleAsEquipmentOption(JSONObject tmpA, int Choicepos) throws JSONException {
        JSONObject JO = new JSONObject();
        final int p = Choicepos;
        if (tmpA.has("equipment_option")) {
            if (tmpA.getJSONObject("equipment_option").getJSONObject("from").has("equipment_category")) {
                JO = tmpA.getJSONObject("equipment_option").getJSONObject("from").getJSONObject("equipment_category");
            } else {
                JO = tmpA.getJSONObject("equipment_option").getJSONObject("from");
            }
        } else if (tmpA.has("from")) {
            JO = tmpA.getJSONObject("from").getJSONObject("equipment_category");
        } else {
            JO = tmpA.getJSONObject("equipment_category");
        }
        StringRequest str = new StringRequest(Request.Method.GET, BaseURL + JO.getString("url"), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject hold = new JSONObject(response);
                    for (int i = 0; i < hold.getJSONArray("equipment").length(); i++) {
                        String Name = "1 x " + hold.getJSONArray("equipment").getJSONObject(i).optString("name");
                        LLS.get(p).add(BuildCheckBox(Name, p));
                    }
                    AddToLayout();
                    SearchForCheckboxes();
                } catch (Exception e) {
                    e.printStackTrace();
                    ErrorHandle(e, layout.getContext());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        HandleRequest(str, queue);
    }

    private CheckBox BuildCheckBox(String name, int pos) {
        CheckBox tmp = new CheckBox(layout.getContext());
        tmp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hold = EquipmentCheck((CheckBox) v);
                if (!hold) {
                    CheckBox a = (CheckBox) v;
                    a.setChecked(false);
                }
            }
        });
        tmp.setText(name);
        tmp.setTextSize(18f);
        tmp.setTextColor(color);
        return tmp;
    }

    private void AddToLayout() {
        for (int i = 0; i < LLS.size(); i++) {
            for (int k = 0; k < LLS.get(i).size(); k++) {
                boolean Doesntexist = true;
                for (int x = 0; x < layout.getChildCount(); x++) {
                    if (layout.getChildAt(x) == LLS.get(i).get(k)) {
                        Doesntexist = false;
                    }
                }
                if (Doesntexist) {
                    layout.addView(LLS.get(i).get(k));
                }

            }
            Space s = new Space(layout.getContext());
            s.setMinimumHeight(16);
            layout.addView(s);
        }
    }

    private boolean EquipmentCheck(CheckBox v) {
        boolean AbleToChoose = true;
        for (int ec = 0; ec < EquipmentChoices.size(); ec++) {
            for (int i = 0; i < EquipmentChoices.get(ec).size(); i++) {
                if (v == EquipmentChoices.get(ec).get(i)) {
                    int counter = 0;
                    ArrayList List = EquipmentChoices.get(ec);
                    for (int ecL = 0; ecL < List.size(); ecL++) {
                        CheckBox tmpCheckbox = (CheckBox) List.get(ecL);
                        if (tmpCheckbox.isChecked()) {
                            counter++;
                        }
                    }
                    if (counter > ChoiceCount.get(ec)) {
                        Toast TooMuchEquipment = Toast.makeText(getApplicationContext(), String.format("Too many pieces of equipment. You are limited to %d selection for this choice.", ChoiceCount.get(ec)), Toast.LENGTH_SHORT);
                        AbleToChoose = false;
                        TooMuchEquipment.show();
                    }
                }
            }
        }
        return AbleToChoose;
    }


    private void SortChoice(CheckBox r) {
        LinearLayout c = findViewById(R.id.ListLayout);
        ArrayList<View> a = getViewsByTag(c, r.getTag().toString());
        for (int i = 0; i < a.size(); i++) {
            CheckBox b = (CheckBox) a.get(i);
            if (!(r == b)) {
                b.setChecked(false);
            }
        }
    }


    private JSONObject FetchStartEquipment(String ClassString) throws Exception {
        JSONObject hold = new JSONObject();
        for (int i = 0; i < StartEquipData.size(); i++) {
            String a = StartEquipData.get(i).getJSONObject("class").getString("name");
            if (a.equals(ClassString)) {
                hold = StartEquipData.get(i);
            }
        }
        return hold;
    }


}
