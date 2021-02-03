package com.thekidd.naturalwonder.CharacterSheets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class CharacterSheetsEditor extends BaseNWActivity {

    TextView Name, Class, Race, Initative, Health, Speed, HitDice, AC, Prof, STR, DEX, CON, INT, WIS, CHA, STRBO, DEXBO, CONBO, INTBO, WISBO, CHABO,
            WISSAV, STRSAV, DEXSAV, CONSAV, INTSAV, CHASAV, ATH, ACRO, SOH, STEA, ARCA, HIST, INVES, NATUR, RELIG, ANHA, INSIG, MEDI, PERC, DECE, INTIM, PERF, PERS,
            SpeTitle, SpecialAtt, ProfBo, StrengBO, CLevel, SpellAtt, SpellDC, Equipment, Plat, Gold, Silver, Copper, Alignment, SURV, LifestyleText, FeatsText,
            SpellsText, SpellSlotsText;
    Button BackButt;
    FabSpeedDial FabButt;
    boolean StrSave = false, DexSave = false, ConSave = false, IntSave = false, WisSave = false, ChaSave = false;
    CharacterDataRepo DataRepo;
    ArrayList<EditText> ToSaveViewsPC = new ArrayList<>();
    ArrayList<EditText> ToSaveViewsO = new ArrayList<>();
    int color;
    JSONObject CharFile = new JSONObject();
    EditText HairTextBox, SkinTextBox, EyesTextBox, HeightTextBox, WeightTextBox, AgeTextBox, GenderTextbox, OrgTextBox, AlliesTextBox, EnemiesTextBox, BackStoryTextBox, OtherTextBox;
    int CurrentLevel = 0;
    boolean initialised = false;
    String FeatsString = "";
    ArrayList<Integer> ScoreData = new ArrayList<>();
    private TextView WeaponStrBo;
    private String SpellSlotsString;
    private String UnableToLevelUpString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataRepo = new CharacterDataRepo(getApplicationContext());
        setContentView(R.layout.activity_character_sheets);
        HairTextBox = findViewById(R.id.editTextHair);
        SkinTextBox = findViewById(R.id.editTextSkin);
        EyesTextBox = findViewById(R.id.editTextEyes);
        HeightTextBox = findViewById(R.id.editTextHeight);
        WeightTextBox = findViewById(R.id.editTextWeight);
        AgeTextBox = findViewById(R.id.editTextAge);
        GenderTextbox = findViewById(R.id.editTextGender);
        ToSaveViewsPC.add(HairTextBox);
        ToSaveViewsPC.add(SkinTextBox);
        ToSaveViewsPC.add(EyesTextBox);
        ToSaveViewsPC.add(HeightTextBox);
        ToSaveViewsPC.add(WeightTextBox);
        ToSaveViewsPC.add(AgeTextBox);
        ToSaveViewsPC.add(GenderTextbox);


        FeatsText = findViewById(R.id.FeatsText);
        OrgTextBox = findViewById(R.id.editTextOrg);
        AlliesTextBox = findViewById(R.id.editTextAllies);
        EnemiesTextBox = findViewById(R.id.editTextEnemies);
        BackStoryTextBox = findViewById(R.id.editTextBackstory);
        OtherTextBox = findViewById(R.id.editTextOther);
        ToSaveViewsO.add(OrgTextBox);
        ToSaveViewsO.add(AlliesTextBox);
        ToSaveViewsO.add(EnemiesTextBox);
        ToSaveViewsO.add(BackStoryTextBox);
        ToSaveViewsO.add(OtherTextBox);

        Name = findViewById(R.id.CharacterName);
        Class = findViewById(R.id.ClassText);
        Race = findViewById(R.id.RaceText);

        Initative = findViewById(R.id.InitText);
        Health = findViewById(R.id.HealthText);
        Speed = findViewById(R.id.SpeedText);
        HitDice = findViewById(R.id.HitDiceText);
        AC = findViewById(R.id.ACText);
        Prof = findViewById(R.id.ProfText);

        SpellsText = findViewById(R.id.SpellsText);
        SpellSlotsText = findViewById(R.id.SpellSlotsText);

        STR = findViewById(R.id.STRTEXT);
        DEX = findViewById(R.id.DEXTEXT);
        CON = findViewById(R.id.CONTEXT);
        INT = findViewById(R.id.INTTEXT);
        WIS = findViewById(R.id.WISTEXT);
        CHA = findViewById(R.id.CHATEXT);

        STRBO = findViewById(R.id.STRBO);
        DEXBO = findViewById(R.id.DEXBO);
        CONBO = findViewById(R.id.CONBO);
        INTBO = findViewById(R.id.INTBO);
        WISBO = findViewById(R.id.WISBO);
        CHABO = findViewById(R.id.CHABO);

        STRSAV = findViewById(R.id.STRSAV);
        DEXSAV = findViewById(R.id.DEXSAV);
        CONSAV = findViewById(R.id.CONSAV);
        INTSAV = findViewById(R.id.INTSAV);
        WISSAV = findViewById(R.id.WISSAV);
        CHASAV = findViewById(R.id.CHASAV);

        ATH = findViewById(R.id.AthText);
        ACRO = findViewById(R.id.ArcoText);
        SOH = findViewById(R.id.SOHText);
        STEA = findViewById(R.id.StealthText);
        ARCA = findViewById(R.id.ArcText);
        HIST = findViewById(R.id.HisText);
        INVES = findViewById(R.id.InvsText);
        NATUR = findViewById(R.id.NatureText);
        RELIG = findViewById(R.id.ReligText);
        ANHA = findViewById(R.id.AnHaText);
        INSIG = findViewById(R.id.InsigText);
        MEDI = findViewById(R.id.MediText);
        PERC = findViewById(R.id.PercText);
        SURV = findViewById(R.id.SurvText);
        DECE = findViewById(R.id.DecepText);
        INTIM = findViewById(R.id.IntimText);
        PERF = findViewById(R.id.PerfText);
        PERS = findViewById(R.id.PersuaText);

        WeaponStrBo = findViewById(R.id.StrengthBo);
        SpeTitle = findViewById(R.id.SpecialAttTitle);
        SpecialAtt = findViewById(R.id.SpecialAttack);
        ProfBo = findViewById(R.id.ProfBoText);
        StrengBO = findViewById(R.id.StrengthBo);
        CLevel = findViewById(R.id.CLevelText);
        SpellAtt = findViewById(R.id.SAText);
        SpellDC = findViewById(R.id.SDCText);
        Equipment = findViewById(R.id.EquipText);
        Plat = findViewById(R.id.PlatP);
        Gold = findViewById(R.id.GoldP);
        Silver = findViewById(R.id.SilverP);
        Copper = findViewById(R.id.CopperP);
        Alignment = findViewById(R.id.AlignmentText);
        LifestyleText = findViewById(R.id.LifestyleText);


        if (ThemeMode) {
            color = R.color.LightMode_Back;
        } else {
            color = R.color.DarkMode_Back;
        }

        FabButt = findViewById(R.id.AddStuffButt);
        FabButt.setMenuListener(new SimpleMenuListenerAdapter() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                try {
                    String title = menuItem.getTitle().toString();
                    switch (title) {
                        case "Add Equipment":
                            TryAddEquipment();
                            break;
                        case "Add Weapon":
                            TryAddWeapon();
                            break;
                        case "Add Magic":
                            TryAddMagic();
                            break;
                        case "Level up":
                            TryLevelUp();
                            break;


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, getApplicationContext());
                }
                return false;
            }
        });
        BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BackToSheets();
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                    ErrorHandle(e, getApplicationContext());
                }
            }
        });

        try {
            String filedir = getIntent().getStringExtra("FilePath");
            if (filedir != null) {
                File Char = new File(filedir);
                if (Char.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(Char));
                    JSONObject Data = new JSONObject(br.readLine());
                    CharFile = Data;
                    Name.setText(Data.getString("Name"));
                    SetClass();

                    Race.setText(Data.getString("Race"));
                    int Lvl = CalculateLVL();

                    String classnamefirst = Data.getJSONArray("ClassesData").getJSONObject(0).getString("name");
                    for (int i = 0; i < Data.getJSONArray("ClassesData").length(); i++) {
                        String ClassName = Data.getJSONArray("ClassesData").getJSONObject(i).getString("name");
                        int test = Data.getJSONArray("ClassesData").getJSONObject(i).getInt("level");
                        for (int j = 0; j < test; j++) {
                            AddLevel(DataRepo.getLevels().getClasses().get(DataRepo.getClassList().indexOf(ClassName)).get(j), true);
                        }
                    }


                    switch (classnamefirst) {
                        case "Barbarian":
                        case "Fighter":
                            StrSave = true;
                            ConSave = true;
                            break;
                        case "Bard":
                            DexSave = true;
                            ChaSave = true;
                            break;
                        case "Cleric":
                        case "Paladin":
                        case "Warlock":
                            WisSave = true;
                            ChaSave = true;
                            break;
                        case "Druid":
                        case "Wizard":
                            IntSave = true;
                            WisSave = true;
                            break;
                        case "Monk":
                        case "Ranger":
                            StrSave = true;
                            DexSave = true;
                            break;
                        case "Rogue":
                            DexSave = true;
                            IntSave = true;
                            break;
                        case "Sorcerer":
                            ConSave = true;
                            ChaSave = true;
                            break;
                    }


                    if (!Data.isNull("LifeStyle")) {
                        LifestyleText.setText(Data.getString("LifeStyle"));
                    }


                    int Prof = 0;
                    if (Lvl >= 17) {
                        Prof = 6;
                    } else if (Lvl >= 13) {
                        Prof = 5;
                    } else if (Lvl >= 9) {
                        Prof = 4;
                    } else if (Lvl >= 5) {
                        Prof = 3;
                    } else {
                        Prof = 2;
                    }
                    String ProfString = "+" + Prof;
                    this.Prof.setText(ProfString);
                    this.ProfBo.setText(ProfString);
                    WeaponStrBo.setText(ProfString);

                    Health.setText(Data.getString("MaxHealth"));
                    String a = Lvl + "d" + Data.getString("HitDice");
                    HitDice.setText(a);


                    ScoreData.add(Data.getInt("STR"));
                    ScoreData.add(Data.getInt("DEX"));
                    ScoreData.add(Data.getInt("CHA"));
                    ScoreData.add(Data.getInt("INT"));
                    ScoreData.add(Data.getInt("WIS"));
                    ScoreData.add(Data.getInt("CON"));

                    STR.setText(Integer.toString(ScoreData.get(0)));
                    DEX.setText(Integer.toString(ScoreData.get(1)));
                    CHA.setText(Integer.toString(ScoreData.get(2)));
                    INT.setText(Integer.toString(ScoreData.get(3)));
                    WIS.setText(Integer.toString(ScoreData.get(4)));
                    CON.setText(Integer.toString(ScoreData.get(5)));

                    ArrayList<Integer> BonusData = new ArrayList<>();
                    BonusData.add(CalculateStatBonus(ScoreData.get(0)));
                    BonusData.add(CalculateStatBonus(ScoreData.get(1)));
                    BonusData.add(CalculateStatBonus(ScoreData.get(2)));
                    BonusData.add(CalculateStatBonus(ScoreData.get(3)));
                    BonusData.add(CalculateStatBonus(ScoreData.get(4)));
                    BonusData.add(CalculateStatBonus(ScoreData.get(5)));

                    STRBO.setText(Integer.toString(BonusData.get(0)));
                    DEXBO.setText(Integer.toString(BonusData.get(1)));
                    CHABO.setText(Integer.toString(BonusData.get(2)));
                    INTBO.setText(Integer.toString(BonusData.get(3)));
                    WISBO.setText(Integer.toString(BonusData.get(4)));
                    CONBO.setText(Integer.toString(BonusData.get(5)));


                    ArrayList<Integer> SaveData = new ArrayList<>();
                    if (StrSave) {
                        SaveData.add(Prof + BonusData.get(0));
                    } else {
                        SaveData.add(BonusData.get(0));
                    }

                    if (DexSave) {
                        SaveData.add(Prof + BonusData.get(1));
                    } else {
                        SaveData.add(BonusData.get(1));
                    }

                    if (ChaSave) {
                        SaveData.add(Prof + BonusData.get(2));
                    } else {
                        SaveData.add(BonusData.get(2));
                    }

                    if (IntSave) {
                        SaveData.add(Prof + BonusData.get(3));
                    } else {
                        SaveData.add(BonusData.get(3));
                    }

                    if (WisSave) {
                        SaveData.add(Prof + BonusData.get(4));
                    } else {
                        SaveData.add(BonusData.get(4));
                    }

                    if (ConSave) {
                        SaveData.add(Prof + BonusData.get(5));
                    } else {
                        SaveData.add(BonusData.get(5));
                    }

                    STRSAV.setText(Integer.toString(SaveData.get(0)));
                    DEXSAV.setText(Integer.toString(SaveData.get(1)));
                    CHASAV.setText(Integer.toString(SaveData.get(2)));
                    INTSAV.setText(Integer.toString(SaveData.get(3)));
                    WISSAV.setText(Integer.toString(SaveData.get(4)));
                    CONSAV.setText(Integer.toString(SaveData.get(5)));

                    Alignment.setText(Data.getString("Alignment"));


                    if (Data.has("SavedEquipment")) {
                        String SE = Data.getString("SavedEquipment");
                        Equipment.setText(SE);
                    } else {
                        String NL = System.lineSeparator();
                        String b = Data.getString("Equips");
                        String c = Data.getString("ChosenEquips");

                        String d = c.split(",")[0];
                        String e = c.split(",")[1];
                        String f = d.substring(1);
                        String g = e.substring(1, e.length() - 1);
                        String h = b + f + NL + g;
                        Equipment.setText(h + System.lineSeparator());
                    }
                    int AthBo = 0, AcroBo = 0, SOHBo = 0, StealBo = 0, ArcaBo = 0, HistBo = 0, InvestBo = 0, NatureBo = 0,
                            ReligBo = 0, AnimalBo = 0, InsigBo = 0, MediBo = 0, PercepBo = 0, SurvivalBo = 0, DecepBo = 0,
                            IntimdBo = 0, PerforBo = 0, PersuaBo = 0;


                    String SkillsString = Data.getString("Skills");

                    JSONArray CheckedSkilldata = new JSONArray(SkillsString);


                    for (int i = 0; i < CheckedSkilldata.length(); i++) {
                        String o = CheckedSkilldata.getString(i);
                        switch (o) {
                            case "Athletics":
                                AthBo = BonusData.get(0) + Prof;
                                break;
                            case "Acrobatics":
                                AcroBo = BonusData.get(1) + Prof;
                                break;
                            case "Sleight of Hand":
                                SOHBo = BonusData.get(1) + Prof;
                                break;
                            case "Stealth":
                                StealBo = BonusData.get(1) + Prof;
                                break;
                            case "Arcana":
                                ArcaBo = BonusData.get(2) + Prof;
                                break;
                            case "History":
                                HistBo = BonusData.get(2) + Prof;
                                break;
                            case "Investigation":
                                InvestBo = BonusData.get(2) + Prof;
                                break;
                            case "Nature":
                                NatureBo = BonusData.get(2) + Prof;
                                break;
                            case "Religion":
                                ReligBo = BonusData.get(2) + Prof;
                                break;
                            case "Animal Handling":
                                AnimalBo = BonusData.get(3) + Prof;
                                break;
                            case "Insight":
                                InsigBo = BonusData.get(3) + Prof;
                                break;
                            case "Medicine":
                                MediBo = BonusData.get(3) + Prof;
                                break;
                            case "Perception":
                                PercepBo = BonusData.get(3) + Prof;
                                break;
                            case "Survival":
                                SurvivalBo = BonusData.get(3) + Prof;
                                break;
                            case "Deception":
                                DecepBo = BonusData.get(4) + Prof;
                                break;
                            case "Intimidation":
                                IntimdBo = BonusData.get(4) + Prof;
                                break;
                            case "Performance":
                                PerforBo = BonusData.get(4) + Prof;
                                break;
                            case "Persuasion":
                                PersuaBo = BonusData.get(4) + Prof;
                                break;
                        }
                    }

                    ArrayList<Integer> Skills = new ArrayList<>();
                    Skills.add(AthBo);
                    Skills.add(AcroBo);
                    Skills.add(SOHBo);
                    Skills.add(StealBo);
                    Skills.add(ArcaBo);
                    Skills.add(HistBo);
                    Skills.add(InvestBo);
                    Skills.add(NatureBo);
                    Skills.add(ReligBo);
                    Skills.add(AnimalBo);
                    Skills.add(InsigBo);
                    Skills.add(MediBo);
                    Skills.add(PercepBo);
                    Skills.add(SurvivalBo);
                    Skills.add(DecepBo);
                    Skills.add(IntimdBo);
                    Skills.add(PerforBo);
                    Skills.add(PersuaBo);

                    for (int i = 0; i < Skills.size(); i++) {
                        int hold = Skills.get(i);
                        if (hold == 0) {
                            switch (i) {
                                case 0:
                                    Skills.set(i, BonusData.get(0));
                                    break;

                                case 1:
                                case 2:
                                case 3:
                                    Skills.set(i, BonusData.get(1));
                                    break;

                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                    Skills.set(i, BonusData.get(2));
                                    break;

                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                    Skills.set(i, BonusData.get(3));
                                    break;

                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    Skills.set(i, BonusData.get(4));
                                    break;
                            }

                        }
                    }


                    //Data.getJSONObject("money")!=null
                    if (false) {
                        Plat.setText(Data.getJSONObject("money").getString("plat"));
                        Gold.setText(Data.getJSONObject("money").getString("gold"));
                        Silver.setText(Data.getJSONObject("money").getString("silv"));
                        Copper.setText(Data.getJSONObject("money").getString("copp"));
                    } else {
                        Plat.setText("0");
                        Gold.setText("0");
                        Silver.setText("0");
                        Copper.setText("0");
                    }


                    String aa = "Athletics: " + Skills.get(0) + " ";

                    String bb = "Acrobatics: " + Skills.get(1) + " ";
                    String cc = "Sleight of Hand: " + Skills.get(2) + " ";
                    String dd = "Stealth: " + Skills.get(3) + " ";

                    String ee = "Arcana: " + Skills.get(4) + " ";
                    String ff = "History: " + Skills.get(5) + " ";
                    String gg = "Investigation: " + Skills.get(6) + " ";
                    String hh = "Nature: " + Skills.get(7) + " ";
                    String ii = "Religion: " + Skills.get(8) + " ";

                    String qq = "Animal Handling: " + Skills.get(9) + " ";
                    String jj = "Insight: " + Skills.get(10) + " ";
                    String kk = "Medicine: " + Skills.get(11) + " ";
                    String ll = "Perception: " + Skills.get(12) + " ";
                    String rr = "Survival: " + Skills.get(13) + " ";

                    String mm = "Deception: " + Skills.get(14) + " ";
                    String nn = "Intimdation: " + Skills.get(15) + " ";
                    String oo = "Performance: " + Skills.get(16) + " ";
                    String pp = "Persuasion: " + Skills.get(17) + " ";


                    ATH.setText(aa);
                    ACRO.setText(bb);
                    SOH.setText(cc);
                    STEA.setText(dd);
                    ARCA.setText(ee);
                    HIST.setText(ff);
                    INVES.setText(gg);
                    NATUR.setText(hh);
                    RELIG.setText(ii);
                    ANHA.setText(qq);
                    INSIG.setText(jj);
                    MEDI.setText(kk);
                    PERC.setText(ll);
                    SURV.setText(rr);
                    DECE.setText(mm);
                    INTIM.setText(nn);
                    PERF.setText(oo);
                    PERS.setText(pp);

                    int spellDC = 8 + Prof;
                    int SA = 0;
                    switch (classnamefirst) {
                        case "Cleric":
                        case "Druid":
                        case "Ranger":
                            spellDC = spellDC + BonusData.get(4);
                            SA = Prof + BonusData.get(4);
                            break;

                        case "Bard":
                        case "Warlock":
                        case "Sorcerer":
                        case "Paladin":
                            spellDC = spellDC + BonusData.get(2);
                            SA = Prof + BonusData.get(2);
                            break;

                        case "Wizard":
                            spellDC = spellDC + BonusData.get(3);
                            SA = Prof + BonusData.get(3);
                            break;
                    }
                    SpellDC.setText(Integer.toString(spellDC));
                    CLevel.setText(Integer.toString(Lvl));
                    SpellAtt.setText("+" + (SA));

                    int AC = 10 + BonusData.get(1);
                    this.AC.setText(Integer.toString(AC));
                    this.Initative.setText(Integer.toString(BonusData.get(1)));
                    String Race = Data.getString("Race");
                    switch (Race) {
                        case "Dragonborn":
                        case "Elf":
                        case "Half-elf":
                        case "Half-orc":
                        case "Human":
                        case "Tiefling":
                            this.Speed.setText("30");
                            break;

                        case "Dwarf":
                        case "Gnome":
                        case "Halfling":
                            this.Speed.setText("25");
                            break;
                    }
                    SpeTitle.setText("Resource");
                    SpecialAtt.setText(Integer.toString(0));
                    LoadCharBackground();
                }
            } else {
                throw new Exception("CharSheet Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }
    }

    private void TryAddMagic() {
        AlertDialog.Builder MagicDialog = new AlertDialog.Builder(this);
        MagicDialog.setTitle("Add Spell to Character...");
        final View CustomDialogLayout = getLayoutInflater().inflate(R.layout.chardialogslayout, null);
        TextView t = CustomDialogLayout.findViewById(R.id.MeetSpecificsText);
        String s = "";
        for (int i = 0; i < DataRepo.getMagicList().get(0).getDesc().size(); i++) {
            s = s + DataRepo.getMagicList().get(0).getDesc().get(i) + " ";
        }
        t.setText(s);
        Spinner S = CustomDialogLayout.findViewById(R.id.CharSpinner);
        CustomMagicAdapter CWA = new CustomMagicAdapter(this, DataRepo.getMagicList());
        S.setAdapter(CWA);
        S.setPopupBackgroundResource(color);
        S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String s = "";
                for (int i = 0; i < DataRepo.getMagicList().get(position).getDesc().size(); i++) {
                    s = s + DataRepo.getMagicList().get(position).getDesc().get(i) + " ";
                }
                t.setText(s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        MagicDialog.setView(CustomDialogLayout);
        MagicDialog.setPositiveButton("Add to Char", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddMagic(DataRepo.getMagicList().get(S.getSelectedItemPosition()));
            }
        });
        MagicDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog Magic = MagicDialog.create();
        Magic.getWindow().setBackgroundDrawableResource(color);
        Magic.show();

    }

    private void TryAddWeapon() {
        AlertDialog.Builder WeaponDialog = new AlertDialog.Builder(this);
        WeaponDialog.setTitle("Add Weapon to Character...");
        final View CustomDialogLayout = getLayoutInflater().inflate(R.layout.chardialogslayout, null);
        TextView t = CustomDialogLayout.findViewById(R.id.MeetSpecificsText);
        if (DataRepo.getWeaponList().get(0).getStrMinimum() > 0) {
            t.setText("Strength Minimum: " + DataRepo.getWeaponList().get(0).getStrMinimum());
        } else {
            t.setText("Strength Minimum: N/a");
        }
        Spinner S = CustomDialogLayout.findViewById(R.id.CharSpinner);
        CustomWeaponAdapter CWA = new CustomWeaponAdapter(this, DataRepo.getWeaponList());
        S.setAdapter(CWA);
        S.setPopupBackgroundResource(color);
        S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (DataRepo.getWeaponList().get(position).getStrMinimum() > 0) {
                    t.setText("Strength Minimum: " + DataRepo.getWeaponList().get(position).getStrMinimum());
                } else {
                    t.setText("Strength Minimum: N/a");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        WeaponDialog.setView(CustomDialogLayout);
        WeaponDialog.setPositiveButton("Add to Char", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddWeapon(DataRepo.getWeaponList().get(S.getSelectedItemPosition()));
            }
        });
        WeaponDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog Weapon = WeaponDialog.create();
        Weapon.getWindow().setBackgroundDrawableResource(color);
        Weapon.show();
    }

    private void AddWeapon(Weapon weapon) {
        int count = 1;
        if (Equipment.getText().toString().contains(weapon.getTitle())) {
            char[] test = Equipment.getText().toString().toCharArray();
            String pop = "";
            for (int i = 0; i < test.length; i++) {
                if (test[i] == '\n') {
                    if (pop.contains(weapon.getTitle())) {
                        count = count + Integer.parseInt(pop.split(" x ")[0]);
                        String a = Equipment.getText().toString().replace(pop + test[i], "");
                        Equipment.setText(a);
                    } else {
                        pop = "";
                    }
                } else {
                    pop = pop + test[i];
                }
            }
        }

        Equipment.setText(Equipment.getText().toString() + count + " x " + weapon.getTitle() + System.lineSeparator());
    }

    private void AddEquipment(EquimentPiece Ep) {
        int count = 1;
        if (Equipment.getText().toString().contains(Ep.getTitle())) {
            char[] test = Equipment.getText().toString().toCharArray();
            String pop = "";
            for (int i = 0; i < test.length; i++) {
                if (test[i] == '\n') {
                    if (pop.contains(Ep.getTitle())) {
                        count = count + Integer.parseInt(pop.split(" x ")[0]);
                        String a = Equipment.getText().toString().replace(pop + test[i], "");
                        Equipment.setText(a);
                    } else {
                        pop = "";
                    }
                } else {
                    pop = pop + test[i];
                }
            }
        }

        Equipment.setText(Equipment.getText().toString() + count + " x " + Ep.getTitle() + System.lineSeparator());
    }

    private void AddMagic(Magic magic) {
        if (!SpellsText.getText().toString().contains(magic.getTitle())) {
            SpellsText.setText(SpellsText.getText() + magic.getTitle() + System.lineSeparator());
        } else {
            Toast t = Toast.makeText(getApplicationContext(), "You already know this spell.", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    private void AddLevel(Level level, boolean init) throws JSONException {
        if (level.getSpellSlots().isEmpty()) {
            this.SpellSlotsString = "This class doesn't have spells.";
        } else {
            this.SpellSlotsString = "";
            for (int i = 0; i < level.getSpellSlots().size(); i++) {
                if (i == level.getSpellSlots().size() - 1) {
                    this.SpellSlotsString = this.SpellSlotsString + "Level " + (i + 1) + " Spellslots: " + level.getSpellSlots().get(i);
                } else {
                    this.SpellSlotsString = this.SpellSlotsString + "Level " + (i + 1) + " Spellslots: " + level.getSpellSlots().get(i) + System.lineSeparator();
                }
            }
        }

        for (int i = 0; i < level.getFeatures().size(); i++) {
            this.FeatsString = this.FeatsString + level.getFeatures().get(i) + System.lineSeparator();
        }

        String a = "";
        for (int i = 0; i < DataRepo.getLevels().getClasses().size(); i++) {
            if (DataRepo.getLevels().getClasses().get(i).contains(level)) {
                a = DataRepo.getClassList().get(i);
            }
        }
        boolean DoesExist = false;
        for (int i = 0; i < CharFile.getJSONArray("ClassesData").length(); i++) {
            if (CharFile.getJSONArray("ClassesData").getJSONObject(i).getString("name").equals(a)) {
                if (!init) {
                    CharFile.getJSONArray("ClassesData").getJSONObject(i).put("level", CharFile.getJSONArray("ClassesData").getJSONObject(i).getInt("level") + 1);
                    DoesExist = true;
                    break;
                } else {
                    DoesExist = true;
                }
            }
        }

        if (!DoesExist) {
            JSONObject j = new JSONObject();
            j.put("name", a);
            j.put("level", 1);
            CharFile.getJSONArray("ClassesData").put(j);
        }


        SetClass();
        UpdateSpellSlots();
        UpdateFeatString();
    }


    private void UpdateSpellSlots() {
        SpellSlotsText.setText(SpellSlotsString);
    }

    private void UpdateFeatString() {
        FeatsText.setText(FeatsString);
    }

    private void TryAddEquipment() {
        AlertDialog.Builder EquipmentDialog = new AlertDialog.Builder(this);
        EquipmentDialog.setTitle("Add Equipment to Character...");
        final View CustomDialogLayout = getLayoutInflater().inflate(R.layout.chardialogslayout, null);
        TextView Req = CustomDialogLayout.findViewById(R.id.MeetSpecificsText);
        Req.setText("Item Type: " + DataRepo.getEquipmentList().get(0).getType());
        Spinner S = CustomDialogLayout.findViewById(R.id.CharSpinner);
        CustomEquipsAdatper CEA = new CustomEquipsAdatper(this, DataRepo.getEquipmentList());
        S.setAdapter(CEA);
        S.setPopupBackgroundResource(color);
        S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Req.setText("Item Type: " + DataRepo.getEquipmentList().get(position).getType());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        EquipmentDialog.setView(CustomDialogLayout);
        EquipmentDialog.setPositiveButton("Add to Char", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddEquipment(DataRepo.getEquipmentList().get(S.getSelectedItemPosition()));
            }
        });
        EquipmentDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog Equip = EquipmentDialog.create();
        Equip.getWindow().setBackgroundDrawableResource(color);
        Equip.show();
    }


    private void TryLevelUp() throws JSONException {
        AlertDialog.Builder LevelupDialog = new AlertDialog.Builder(this);
        LevelupDialog.setTitle("Select a class to level up...");
        final View CustomDialogLayout = getLayoutInflater().inflate(R.layout.chardialogslayout, null);
        TextView Req = CustomDialogLayout.findViewById(R.id.MeetSpecificsText);
        if (CharFile.getJSONArray("ClassesData").getJSONObject(0).getString("name").equals("Barbarian")) {
            Req.setText("You can level up your original class.");
        } else {
            Req.setText(DataRepo.getLevels().getBarbarian().get(0).getMinimumString());
        }

        Spinner S = CustomDialogLayout.findViewById(R.id.CharSpinner);
        CustomArrayAdapter CAA = new CustomArrayAdapter(this, DataRepo.getClassList());
        S.setAdapter(CAA);
        S.setPopupBackgroundResource(color);
        S.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (CharFile.getJSONArray("ClassesData").getJSONObject(0).getString("name").equals(DataRepo.getClassList().get(position))) {
                        Req.setText("You can level up your original class.");
                    } else {
                        Req.setText(DataRepo.getLevels().getClasses().get(position).get(0).getMinimumString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, getApplicationContext());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        LevelupDialog.setView(CustomDialogLayout);
        LevelupDialog.setPositiveButton("Add to Char", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Level l = DataRepo.getLevels().getClasses().get(S.getSelectedItemPosition()).get(CurrentLevel);
                    if (TestIfEligible(l)) {
                        AddLevel(l, false);
                    } else {
                        Toast t = Toast.makeText(getApplicationContext(), "You cannot multiclass as you do not have enough of respective ability score.", Toast.LENGTH_SHORT);
                        t.show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    ErrorHandle(e, getApplicationContext());
                }
            }
        });
        LevelupDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog Level = LevelupDialog.create();
        Level.getWindow().setBackgroundDrawableResource(color);
        if (CurrentLevel < 20) {
            Level.show();
        } else {
            Toast t = Toast.makeText(getApplicationContext(), "You are at the Max Level for D&D. You cannot level up further.", Toast.LENGTH_SHORT);
            t.show();
        }

    }

    private boolean TestIfEligible(Level l) throws JSONException {
        boolean b = false;
        if (l.getName().equals(CharFile.getJSONArray("ClassesData").getJSONObject(0).getString("name"))) {
            b = true;
        } else if (l.isBoth()) {
            int count = 0;
            int RelatedPos = 0;
            for (int i = 0; i < l.getABSMinmum().size(); i++) {
                switch (l.getABSMinmum().get(i)) {
                    case "Strength":
                        RelatedPos = 0;
                        break;
                    case "Dexterity":
                        RelatedPos = 1;
                        break;
                    case "Constitution":
                        RelatedPos = 2;
                        break;
                    case "Intelligence":
                        RelatedPos = 3;
                        break;
                    case "Charisma":
                        RelatedPos = 4;
                        break;
                    case "Wisdom":
                        RelatedPos = 5;
                        break;

                }
                if (ScoreData.get(RelatedPos) >= 13) {
                    count++;
                }
            }
            if (count == l.getABSMinmum().size()) {
                b = true;
            }
        } else {
            int count = 0;
            int RelatedPos = 0;
            for (int i = 0; i < l.getABSMinmum().size(); i++) {
                switch (l.getABSMinmum().get(i)) {
                    case "Strength":
                        RelatedPos = 0;
                        break;
                    case "Dexterity":
                        RelatedPos = 1;
                        break;
                    case "Constitution":
                        RelatedPos = 2;
                        break;
                    case "Intelligence":
                        RelatedPos = 3;
                        break;
                    case "Charisma":
                        RelatedPos = 4;
                        break;
                    case "Wisdom":
                        RelatedPos = 5;
                        break;

                }
                if (ScoreData.get(RelatedPos) >= 13) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }


    private int CalculateLVL() throws JSONException {
        int LVL = 0;
        for (int i = 0; i < CharFile.getJSONArray("ClassesData").length(); i++) {
            JSONObject Class = CharFile.getJSONArray("ClassesData").getJSONObject(i);
            LVL = +Class.getInt("level");
        }
        CurrentLevel = LVL;
        return LVL;
    }

    private void LoadCharBackground() throws JSONException {
        if (!CharFile.isNull("PhysicalCharacteristics")) {
            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Hair")) {
                HairTextBox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Hair"));
            }

            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Skin")) {
                SkinTextBox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Skin"));
            }

            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Eyes")) {
                EyesTextBox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Eyes"));
            }

            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Height")) {
                HeightTextBox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Height"));
            }

            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Weight")) {
                WeightTextBox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Weight"));
            }

            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Age")) {
                AgeTextBox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Age"));
            }

            if (!CharFile.getJSONObject("PhysicalCharacteristics").isNull("Gender")) {
                GenderTextbox.setText(CharFile.getJSONObject("PhysicalCharacteristics").getString("Gender"));
            }
        }

        if (!CharFile.isNull("OtherNotes")) {
            if (!CharFile.getJSONObject("OtherNotes").isNull("Org")) {
                OrgTextBox.setText(CharFile.getJSONObject("OtherNotes").getString("Org"));
            }

            if (!CharFile.getJSONObject("OtherNotes").isNull("Allies")) {
                AlliesTextBox.setText(CharFile.getJSONObject("OtherNotes").getString("Allies"));
            }

            if (!CharFile.getJSONObject("OtherNotes").isNull("Enemies")) {
                EnemiesTextBox.setText(CharFile.getJSONObject("OtherNotes").getString("Enemies"));
            }

            if (!CharFile.getJSONObject("OtherNotes").isNull("Backstory")) {
                BackStoryTextBox.setText(CharFile.getJSONObject("OtherNotes").getString("Backstory"));
            }

            if (!CharFile.getJSONObject("OtherNotes").isNull("Other")) {
                OtherTextBox.setText(CharFile.getJSONObject("OtherNotes").getString("Other"));
            }
        }
    }

    private void SetClass() throws JSONException {
        if (!CharFile.isNull("ClassesData")) {
            JSONArray a = CharFile.getJSONArray("ClassesData");
            String ClassLevels = "";
            for (int i = 0; i < a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                String c = b.getString("name") + " " + b.getInt("level");
                if (i == a.length() - 1) {
                    ClassLevels = ClassLevels + c;
                } else {
                    ClassLevels = ClassLevels + c + System.lineSeparator();
                }
            }
            Class.setText(ClassLevels);
        }
    }

    private void BackToSheets() throws JSONException, IOException {
        SaveChar();
        Intent i = new Intent(this, SheetsActivity.class);
        startActivity(i);
    }

    private void SaveChar() throws JSONException, IOException {
        JSONObject PhysicalCharateristics = new JSONObject();
        JSONObject Others = new JSONObject();
        JSONObject b = new JSONObject();
        for (int i = 0; i < ToSaveViewsPC.size(); i++) {
            String a = "";
            String nameoption = "";
            switch (i) {
                case 0:
                    nameoption = "Hair";
                    break;

                case 1:
                    nameoption = "Skin";
                    break;
                case 2:
                    nameoption = "Eyes";
                    break;

                case 3:
                    nameoption = "Height";
                    break;
                case 4:
                    nameoption = "Weight";
                    break;

                case 5:
                    nameoption = "Age";
                    break;
                case 6:
                    nameoption = "Gender";
                    break;

            }
            a = ToSaveViewsPC.get(i).getText().toString();
            b.put(nameoption, a);
        }
        CharFile.put("PhysicalCharacteristics", b);
        JSONObject d = new JSONObject();
        for (int i = 0; i < ToSaveViewsO.size(); i++) {
            String a = "";
            String nameoption = "";
            switch (i) {
                case 0:
                    nameoption = "Org";
                    break;

                case 1:
                    nameoption = "Allies";
                    break;
                case 2:
                    nameoption = "Enemies";
                    break;

                case 3:
                    nameoption = "Backstory";
                    break;
                case 4:
                    nameoption = "Other";
                    break;
            }
            a = ToSaveViewsO.get(i).getText().toString();
            d.put(nameoption, a);
        }
        CharFile.put("KnownSpells", SpellsText.getText().toString());
        CharFile.put("SavedEquipment", Equipment.getText().toString());
        CharFile.put("OtherNotes", d);
        String filedir = getIntent().getStringExtra("FilePath");
        if (filedir != null) {
            File Char = new File(filedir);
            if (Char.exists()) {
                FileWriter fw = new FileWriter(Char);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(CharFile.toString());
                bw.flush();
                bw.close();
            } else {
                Char.createNewFile();
                FileWriter fw = new FileWriter(Char);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(CharFile.toString());
                bw.flush();
                bw.close();
            }
        }
    }

    private int CalculateStatBonus(int Stat) {
        return (Stat - 10) / 2;
    }
}
