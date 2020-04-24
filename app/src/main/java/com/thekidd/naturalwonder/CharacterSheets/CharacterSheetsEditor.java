package com.thekidd.naturalwonder.CharacterSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CharacterSheetsEditor extends BaseNWActivity {

    TextView Name,Level,Class,Race,Initative,Health,Speed,HitDice,AC,Prof,
            STR,DEX,CON,INT,WIS,CHA,STRBO,DEXBO,CONBO,INTBO,WISBO,CHABO,
            WISSAV,STRSAV,DEXSAV,CONSAV,INTSAV,CHASAV,
            ATH,ACRO,SOH,STEA,ARCA,HIST,INVES,NATUR,RELIG,ANHA,INSIG,MEDI,PERC,DECE,INTIM,PERF,PERS,
            SpeTitle,SpecialAtt,ProfBo,StrengBO,
            CLevel,SpellAtt,SpellDC,
            Equipment,Plat,Gold,Silver,Copper,Alignment,SURV;
    Button BackButt;
    View FabButt;
    boolean StrSave = false,DexSave = false,ConSave = false,IntSave = false,WisSave = false,ChaSave = false;
    private TextView WeaponStrBo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheets);

        Name = findViewById(R.id.CharacterName);
        Level = findViewById(R.id.LVLText);
        Class = findViewById(R.id.ClassText);
        Race = findViewById(R.id.RaceText);

        Initative = findViewById(R.id.InitText);
        Health = findViewById(R.id.HealthText);
        Speed = findViewById(R.id.SpeedText);
        HitDice = findViewById(R.id.HitDiceText);
        AC = findViewById(R.id.ACText);
        Prof = findViewById(R.id.ProfText);

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
        HIST  = findViewById(R.id.HisText);
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

        FabButt = findViewById(R.id.AddStuffButt);
        BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToSheets();
            }
        });

        try {
            String filedir  = getIntent().getStringExtra("FilePath");
            if(filedir != null){
                File Char = new File(filedir);
                if(Char.exists()){
                    BufferedReader br = new BufferedReader(new FileReader(Char));
                    JSONObject Data = new JSONObject(br.readLine());
                    Name.setText(Data.getString("Name"));
                    Level.setText(Data.getString("Level"));
                    Class.setText(Data.getString("Class"));
                    Race.setText(Data.getString("Race"));
                    int Lvl = Data.getInt("Level");

                    if(Data.getString("Class") == "Barbarian"){
                        StrSave = true;
                        ConSave = true;
                    } else if (Data.getString("Class") == "Bard"){
                        DexSave = true;
                        ChaSave = true;
                    } else if (Data.getString("Class") == "Cleric"){
                        WisSave = true;
                        ChaSave = true;
                    } else if (Data.getString("Class") == "Druid"){
                        IntSave = true;
                        WisSave = true;
                    } else if (Data.getString("Class") == "Fighter"){
                        StrSave = true;
                        ConSave = true;
                    } else if (Data.getString("Class") == "Monk"){
                        StrSave = true;
                        DexSave = true;
                    } else if (Data.getString("Class") == "Paladin"){
                        WisSave = true;
                        ChaSave = true;
                    } else if (Data.getString("Class") == "Ranger"){
                        StrSave = true;
                        DexSave = true;
                    } else if (Data.getString("Class") == "Rogue"){
                        DexSave = true;
                        IntSave = true;
                    } else if (Data.getString("Class") == "Sorcerer"){
                        ConSave = true;
                        ChaSave = true;
                    } else if (Data.getString("Class") == "Warlock"){
                        WisSave = true;
                        ChaSave = true;
                    } else if (Data.getString("Class") == "Wizard"){
                        IntSave = true;
                        WisSave = true;
                    }



                    int Prof = 0;
                    if(Lvl>=17){
                        Prof = 6;
                    }
                    else if(Lvl>=13){
                        Prof = 5;
                    } else if (Lvl>=9){
                        Prof = 4;
                    } else if (Lvl>=5){
                        Prof = 3;
                    } else {
                        Prof = 2;
                    }
                    String ProfString ="+" + Prof;
                    this.Prof.setText(ProfString);
                    this.ProfBo.setText(ProfString);
                    WeaponStrBo.setText(ProfString);

                    Health.setText(Data.getString("MaxHealth"));
                    String a = Data.getString("Level") +"d"+ Data.getString("HitDice");
                    HitDice.setText(a);

                    ArrayList<Integer> ScoreData = new ArrayList<>();
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
                    if(StrSave){
                        SaveData.add(Prof+BonusData.get(0));
                    } else {
                        SaveData.add(BonusData.get(0));
                    }

                    if(DexSave){
                        SaveData.add(Prof+BonusData.get(1));
                    } else {
                        SaveData.add(BonusData.get(1));
                    }

                    if(ChaSave){
                        SaveData.add(Prof+BonusData.get(2));
                    } else {
                        SaveData.add(BonusData.get(2));
                    }

                    if(IntSave){
                        SaveData.add(Prof+BonusData.get(3));
                    } else {
                        SaveData.add(BonusData.get(3));
                    }

                    if(WisSave){
                        SaveData.add(Prof+BonusData.get(4));
                    } else {
                        SaveData.add(BonusData.get(4));
                    }

                    if(ConSave){
                        SaveData.add(Prof+BonusData.get(5));
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
                    String b = Data.getString("Equips")+Data.getString("ChosenEquips");
                    Equipment.setText(b);

                    int AthBo=0,AcroBo=0,SOHBo=0,StealBo=0,ArcaBo=0,HistBo=0,InvestBo=0,NatureBo=0,
                            ReligBo=0,AnimalBo=0,InsigBo=0,MediBo=0,PercepBo=0,SurvivalBo=0,DecepBo=0,
                            IntimdBo=0,PerforBo=0,PersuaBo=0;



                    String SkillsString  =Data.getString("Skills");

                    JSONArray CheckedSkilldata =  new JSONArray(SkillsString);



                    for(int i =0;i<CheckedSkilldata.length();i++){
                        String o = CheckedSkilldata.getString(i);
                        switch (o){
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
                    Skills.add(AthBo);Skills.add(AcroBo);Skills.add(SOHBo);
                    Skills.add(StealBo);Skills.add(ArcaBo);Skills.add(HistBo);
                    Skills.add(InvestBo);Skills.add(NatureBo);Skills.add(ReligBo);
                    Skills.add(AnimalBo);Skills.add(InsigBo);Skills.add(MediBo);
                    Skills.add(PercepBo);Skills.add(SurvivalBo);Skills.add(DecepBo);
                    Skills.add(IntimdBo);Skills.add(PerforBo);Skills.add(PersuaBo);

                    for(int i =0;i<Skills.size();i++){
                        int hold = Skills.get(i);
                        if(hold == 0){
                            switch (i){
                                case 0:
                                    Skills.set(i,BonusData.get(0));
                                break;

                                case 1:
                                case 2:
                                case 3:
                                    Skills.set(i,BonusData.get(1));
                                break;

                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                    Skills.set(i,BonusData.get(2));
                                break;

                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                    Skills.set(i,BonusData.get(3));
                                break;

                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    Skills.set(i,BonusData.get(4));
                                break;
                            }

                        }
                    }






                    String aa = "Athletics: "+Skills.get(0);

                    String bb = "Acrobatics: "+Skills.get(1);
                    String cc = "Sleight of Hand: " + Skills.get(2);
                    String dd ="Stealth: "+Skills.get(3);

                    String ee ="Arcana: "+Skills.get(4);
                    String ff = "History: "+Skills.get(5);
                    String gg = "Investigation: "+Skills.get(6);
                    String hh = "Nature: "+Skills.get(7);
                    String ii = "Religion: "+Skills.get(8);

                    String qq = "Animal Handling: "+ Skills.get(9);
                    String jj = "Insight: "+Skills.get(10);
                    String kk = "Medicine: "+Skills.get(11);
                    String ll = "Perception: "+Skills.get(12);
                    String rr = "Survival: " + Skills.get(13);

                    String mm = "Deception: "+Skills.get(14);
                    String nn = "Intimdation: "+Skills.get(15);
                    String oo = "Performance: "+Skills.get(16);
                    String pp = "Persuasion: "+Skills.get(17);


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

                    int spellDC = 8 +Prof;
                    int SA = 0;
                    switch(Data.getString("Class")){
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
                    SpellAtt.setText("+"+(SA));

                    int AC = 10+ BonusData.get(1);
                    this.AC.setText(Integer.toString(AC));
                    this.Initative.setText(Integer.toString(BonusData.get(1)));
                    String Race = Data.getString("Race");
                    switch (Race){
                        case"Dragonborn":
                        case"Elf":
                        case"Half-elf":
                        case"Half-orc":
                        case"Human":
                        case"Tiefling":
                            this.Speed.setText("30");
                        break;

                        case"Dwarf":
                        case"Gnome":
                        case"Halfling":
                            this.Speed.setText("25");
                        break;
                    }
                    SpeTitle.setText("Resource");
                    SpecialAtt.setText(Integer.toString(0));
                }
            } else {
                throw new Exception("CharSheet Error");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ErrorHandle(e,this);
        }
    }

    private void BackToSheets() {
        Intent i = new Intent(this,SheetsActivity.class);
        startActivity(i);
    }

    private int CalculateStatBonus(int Stat){
        return (Stat -10)/2;
    }
}
