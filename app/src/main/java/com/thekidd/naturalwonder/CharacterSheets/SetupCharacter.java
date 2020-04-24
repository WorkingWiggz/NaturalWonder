 package com.thekidd.naturalwonder.CharacterSheets;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

 public class SetupCharacter extends BaseNWActivity {
    ArrayList<CheckBox> SkillsList = new ArrayList<>();
    Button RollStats,RandLv,RollHP,FinalChar,Back;
    EditText NameText;
    RadioGroup Races,Classes, Alignments;
    EditText LVLTB,HPTB,STRTB,WISTB,CHATB,INTTB,CONTB,DEXTB;
    ArrayList<JSONObject> StartEquipData = new ArrayList<>();
    ArrayList<Integer> RadioPosList = new ArrayList<>();
    HashMap<String,Integer> statBonuses = new HashMap<>();
    ArrayList<RadioGroup> idList;
    Thread LoadData;
    TextView CET;
    int HitDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_character);
        HitDice = 0;

        LoadData = new Thread(){
                public void run(){
                        Context c = SetupCharacter.this;
                        RequestQueue v = Volley.newRequestQueue(c);
                        StringRequest Barb,Bard,Cleric,Druid,Monk,Fighter,Paladin,Ranger,Rogue,Sorcerer,Warlock,Wizard;
                        Barb = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/1", new Response.Listener<String>() {
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

                        Bard = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/2", new Response.Listener<String>() {
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

                        Cleric = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/3", new Response.Listener<String>() {
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

                        Druid = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/4", new Response.Listener<String>() {
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

                        Monk = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/5", new Response.Listener<String>() {
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

                        Fighter = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/6", new Response.Listener<String>() {
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

                        Paladin = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/7", new Response.Listener<String>() {
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

                        Ranger = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/8", new Response.Listener<String>() {
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

                        Rogue = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/9", new Response.Listener<String>() {
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

                        Sorcerer = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/10", new Response.Listener<String>() {
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

                        Warlock = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/11", new Response.Listener<String>() {
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

                        Wizard = new StringRequest(Request.Method.GET, "https://www.dnd5eapi.co/api/starting-equipment/12", new Response.Listener<String>() {
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

                        v.add(Barb);
                        v.add(Bard);
                        v.add(Cleric);
                        v.add(Druid);
                        v.add(Monk);
                        v.add(Fighter);
                        v.add(Paladin);
                        v.add(Ranger);
                        v.add(Rogue);
                        v.add(Sorcerer);
                        v.add(Warlock);
                        v.add(Wizard);
                    }
           };
        LoadData.start();

        SkillsList.clear();
        for(int i =3;i<21;i++){
            String a = "checkBox"+i;
            int RESID = getResources().getIdentifier(a,"id",getPackageName());
            CheckBox b = findViewById(RESID);
            SkillsList.add(b);
        }

        NameText = findViewById(R.id.DescText);
        RollStats = findViewById(R.id.RollStatsButt);
        RandLv = findViewById(R.id.RandomLvlButt);
        RollHP = findViewById(R.id.HPRollButt);
        FinalChar = findViewById(R.id.FinalizeButt);
        Back = findViewById(R.id.BackToSheets);
        
        Races  = findViewById(R.id.RaceGroup);
        Classes = findViewById(R.id.ClassGroup);
        Alignments = findViewById(R.id.AlignmentGroup);


        LVLTB = findViewById(R.id.LevelTextBox);
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
        
        RandLv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RandomLV();
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
                switch (g.getText().toString()){
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
                    LoadStartEquipment(a);
                    SetHitDice(a);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        Races.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });

    }

     private void SetHitDice(int checkedRadioButtonId) {
        switch (checkedRadioButtonId){
            case 0:
            HitDice =12;
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
        String a = "d"+HitDice;
        text.setText(a);
     }

     private void RandStats() {
        ArrayList<Integer> rolls = new ArrayList<>();
        for(int i=0; i<8;i++){
            rolls.add(GetRandomInt(1,20));
        }

         rolls = BubbleSort(rolls);

        rolls.remove(0);
        rolls.remove(0);
        Boolean STR=false,WIS=false,INT=false,CHA=false,CON=false,DEX=false;
        for(int i = 0;i<rolls.size();i++){
            int rand  = GetRandomInt(1,6);
            switch(rand){
                case 1: //STR
                    if(!STR){STRTB.setText(String.valueOf(rolls.get(i)));STR = true;} else{i--;}
                break;
                case 2: // DEX
                    if(!DEX){DEXTB.setText(String.valueOf(rolls.get(i)));DEX = true;} else{i--;}
                    break;
                case 3: // WIS
                    if(!WIS){WISTB.setText(String.valueOf(rolls.get(i)));WIS = true;} else{i--;}
                    break;
                case 4: // CON
                    if(!CON){CONTB.setText(String.valueOf(rolls.get(i)));CON = true;} else{i--;}
                    break;
                case 5: // INT
                    if(!INT){INTTB.setText(String.valueOf(rolls.get(i)));INT = true;} else{i--;}
                    break;
                case 6: // CHA
                    if(!CHA){CHATB.setText(String.valueOf(rolls.get(i)));CHA = true;} else{i--;}
                    break;
            }
        }
    }

     private ArrayList<Integer> BubbleSort(ArrayList<Integer> rolls) {
            for(int i = 0; i<rolls.size();i++){
                for(int j =0;j<rolls.size()-1;j++){
                 if(rolls.get(j)>rolls.get(j+1)){
                     int tmp = rolls.get(j);
                     rolls.set(j,rolls.get(j+1));
                     rolls.set(j+1,tmp);
                 }
                }
            }
        return rolls;
     }

     private void RandomLV() {
        LVLTB.setText(String.valueOf( GetRandomInt(1,20)));
     }

    private void RollHealth() throws Exception {
        Toast UnsetError = new Toast(this);
        UnsetError.makeText(this,"Class not Chosen please select class.",Toast.LENGTH_SHORT);
        if(HitDice == 0) {
            UnsetError.show();
        } else if (HitDice == 6 ||HitDice == 8 || HitDice == 10||HitDice == 12) {
            int ConBo = (Integer.parseInt(CONTB.getText().toString()) - 10) / 2;
            if (!LVLTB.getText().toString().equals("")) {
                int total = 0;
                for (int i = 0; i < Integer.parseInt(LVLTB.getText().toString()); i++) {
                    total = total + GetRandomInt(1, HitDice) + ConBo;
                }
                String s = "" + total;
                HPTB.setText(s);
            } else {
                HPTB.setText(String.valueOf(GetRandomInt(1, HitDice) + ConBo));
            }
        } else {
            throw new Exception("Wrong hit dice type");
        }
    }

    private void BacktoEditor() {
        Intent i = new Intent(this, SheetsActivity.class);
        startActivity(i);
    }

    private void FinalizeCharacter() throws JSONException, IOException {
        //TODO Check all details have been filled out and completed. Then Save as a JSON file and past this data to the Sheets activity.
        boolean First,Second,Third,Fourth = false,Fifth,Sixth=true;
        RadioGroup r = findViewById(R.id.RaceGroup);
        RadioGroup a = findViewById(R.id.ClassGroup);
        RadioGroup d = findViewById(R.id.AlignmentGroup);

        LinearLayout lay = findViewById(R.id.ListLayout);
        //First Section Check
        if(r.getCheckedRadioButtonId() != -1){
            First = true;
        } else {
            First = false;
        }

        //Second Section Check
        if((a.getCheckedRadioButtonId() !=-1) && (!LVLTB.getText().toString().equals(""))&&(!HPTB.getText().toString().equals(""))){
           Second = true;
        } else {
           Second = false;
        }

        //Third Section Check
        if((!STRTB.getText().toString().equals(""))&&(!CHATB.getText().toString().equals(""))&&(!INTTB.getText().toString().equals(""))&&(!DEXTB.getText().toString().equals(""))&&(!CONTB.getText().toString().equals(""))&&(!WISTB.getText().toString().equals(""))){
            Third = true;
        } else {
            Third = false;
        }

        //Fourth Section Check
        for(int i =0;i<SkillsList.size();i++){
            CheckBox b = SkillsList.get(i);
            if(b.isChecked()){
                Fourth = true;
            }
        }

        //Fifth Section Check
        if(d.getCheckedRadioButtonId() !=-1){
            Fifth = true;
        } else {
            Fifth = false;
        }

        //Sixth Section Check
        for(int i =0;i<RadioPosList.size();i++){
            RadioGroup rg = (RadioGroup) lay.getChildAt(RadioPosList.get(i));
            if(rg.getCheckedRadioButtonId() ==-1){
                Sixth = false;
            }
        }

        if(First&&Second&&Third&&Fourth&&Fifth&&Sixth){
            String name,Race,Class,Alignment,Equips;
            int LVL,HP,STR,DEX,CHA,CON,INT,WIS;
            JSONArray Skills = new JSONArray();
            ArrayList<String> ChosenEquips = new ArrayList<>();
            JSONObject CharacterFile = new JSONObject();

            if(!NameText.getText().toString().equals("")){
                name =NameText.getText().toString();
            } else {
                name = "Empty";
            }

            if(!LVLTB.getText().toString().equals("")){
                LVL = Integer.parseInt(LVLTB.getText().toString());
            } else{
                LVL = 1;
            }

            if(!HPTB.getText().toString().equals("")){
                HP = Integer.parseInt(HPTB.getText().toString());
            } else {
                HP = 1;
            }

            if(!STRTB.getText().toString().equals("")){
                STR = Integer.parseInt(STRTB.getText().toString());
            } else {
                STR = 10;
            }

            if(!DEXTB.getText().toString().equals("")){
                DEX = Integer.parseInt(DEXTB.getText().toString());
            } else {
                DEX = 10;
            }

            if(!CHATB.getText().toString().equals("")){
                CHA = Integer.parseInt(CHATB.getText().toString());
            } else {
                CHA = 10;
            }

            if(!CONTB.getText().toString().equals("")){
                CON = Integer.parseInt(CONTB.getText().toString());
            } else {
                CON = 10;
            }

            if(!INTTB.getText().toString().equals("")){
                INT = Integer.parseInt(INTTB.getText().toString());
            } else {
                INT = 10;
            }

            if(!WISTB.getText().toString().equals("")){
                WIS = Integer.parseInt(WISTB.getText().toString());
            } else {
                WIS = 10;
            }

            for(int i =0;i<SkillsList.size();i++){
                CheckBox b = SkillsList.get(i);
                if(b.isChecked()){
                    Skills.put(b.getText().toString());
                }
            }

            if(Alignments.getCheckedRadioButtonId() != -1){
                RadioButton b = findViewById(Alignments.getCheckedRadioButtonId());
                Alignment = b.getText().toString();
            } else {
                Alignment = "True Neutral";
            }

            if(!CET.getText().toString().equals("")){
                Equips = CET.getText().toString();
            } else {
                Equips = "";
            }

            if(!idList.isEmpty()){
                for(int i=0;i<idList.size();i++){
                    if(idList.get(i).getCheckedRadioButtonId() !=-1){
                        RadioButton b = findViewById(idList.get(i).getCheckedRadioButtonId());
                        ChosenEquips.add(b.getText().toString());
                    }
                }
            }

            if(Races.getCheckedRadioButtonId() != -1){
                RadioButton b = findViewById(Races.getCheckedRadioButtonId());
                Race = b.getText().toString();
            } else {
                Race = "Human";
            }

            if(Classes.getCheckedRadioButtonId() != -1){
                RadioButton b = findViewById(Classes.getCheckedRadioButtonId());
                Class = b.getText().toString();
            } else {
                Class = "Fighter";
            }

            if(HitDice !=0){
                CharacterFile.put("HitDice",HitDice);
            }

            CharacterFile.put("Name",name);
            CharacterFile.put("Level",LVL);
            CharacterFile.put("Race",Race);
            CharacterFile.put("MaxHealth",HP);
            CharacterFile.put("STR",STR);
            CharacterFile.put("DEX",DEX);
            CharacterFile.put("CHA",CHA);
            CharacterFile.put("CON",CON);
            CharacterFile.put("INT",INT);
            CharacterFile.put("WIS",WIS);
            CharacterFile.put("Alignment",Alignment);
            CharacterFile.put("Equips",Equips);
            CharacterFile.put("ChosenEquips",ChosenEquips);
            CharacterFile.put("Skills",Skills);
            CharacterFile.put("Class",Class);

            File Dir = new File( getApplicationContext().getExternalFilesDir(null)+"/NaturalWonder/CharacterSheets/");

            if(!Dir.exists()){
                Dir.mkdirs();
            }
            String fileName = name+".JSON";
            File CharJSON = new File(Dir,fileName);
            if(!CharJSON.exists()){
                boolean p = CharJSON.createNewFile();
                if(p){
                    FileWriter fw = new FileWriter(CharJSON);
                    fw.write(CharacterFile.toString());
                    fw.flush();
                    fw.close();
                }

            }
            Intent i = new Intent(this,CharacterSheetsEditor.class);
            i.putExtra("FilePath",CharJSON.getAbsolutePath());
            startActivity(i);
        } else {
            Toast toast = Toast.makeText(this,"",Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void LoadStartEquipment(int checkedRadioButtonId) throws JSONException {
        //TODO Load the Start Equipment for the selected class

        JSONObject Data = FetchStartEquipment(checkedRadioButtonId);
        JSONArray a = Data.getJSONArray("starting_equipment");
        String e = "";
        for(int i = 0; i<a.length();i++){
            String b = a.getJSONObject(i).getJSONObject("item").getString("name");
            int c = a.getJSONObject(i).getInt("quantity");
            String d = c + "x " + b;
            String NL = System.getProperty("line.separator");
            if(i==0){
                e = d + NL;
            }else {
                e = e + d + NL;
            }
            CET.setText(e);
        }
        final LinearLayout layout = findViewById(R.id.ListLayout);
        layout.removeAllViews();
        int x = 0;
        try {
            x = Data.getInt("choices_to_make");
            for(int i = 1; i<x+1; i++){
                String s = "choice_" +i;
                TextView ChoiceText = new TextView(this);
                String tmp ="Choice "+i+":";
                ChoiceText.setText(tmp);
                ChoiceText.setTextSize(25);
                layout.addView(ChoiceText);
                idList = new ArrayList<>();
                JSONArray ChoiceData = Data.getJSONArray(s);
                for(int j =0;j<ChoiceData.length();j++){
                    final RadioGroup gr = new RadioGroup(this);
                    layout.addView(gr);
                    int count = ChoiceData.getJSONObject(j).getInt("choose");
                    JSONArray FromData = ChoiceData.getJSONObject(j).getJSONArray("from");
                    for(int g = 0;g<FromData.length();g++) {
                          String ChoiceString = "";
                          JSONObject itemData =FromData.getJSONObject(g);
                          for(int h = 0;h<count;h++) {
                              String str = "";
                              String name  = itemData.getJSONObject("item").getString("name");
                              int Quant = FromData.getJSONObject(h).getInt("quantity");
                              String full = Quant +"x "+name;
                              if(h == count-1){
                                  ChoiceString = ChoiceString + full;
                              } else if(h ==0){
                                  ChoiceString = full + " , ";
                              }  else {
                                  ChoiceString = ChoiceString + full + " , ";
                              }
                          }
                          final RadioButton butt = new RadioButton(this);

                          gr.addView(butt);
                          butt.setText(ChoiceString);
                          RadioGroup group = (RadioGroup) butt.getParent();
                          if(!(idList.contains(group))){
                                idList.add(group);
                          }
                          butt.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                UncheckButton((RadioGroup) butt.getParent(),idList);
                            }
                            });
                    }
                    if(j != ChoiceData.length()-1){
                        Space spa = new Space(this);
                        Space spa2= new Space(this);
                        spa.setMinimumHeight(8);
                        spa2.setMinimumHeight(8);
                        TextView Text = new TextView(this);
                        Text.setText("Or");
                        Text.setTextSize(20);
                        Text.setGravity(Gravity.CENTER);
                        layout.addView(spa);
                        layout.addView(Text);
                        layout.addView(spa2);
                    }
                }

            }
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
 }

     private void UncheckButton(RadioGroup parent, ArrayList<RadioGroup> idList){
        for(int i =0;i<idList.size();i++){
            if(parent != idList.get(i)){
                idList.get(i).clearCheck();
            }
        }
     }

     private JSONObject FetchStartEquipment(int checkedRadioButtonId) {
        JSONObject hold;
        hold = StartEquipData.get(checkedRadioButtonId);
        return hold;
    }



     public int GetRandomInt(int Low, int High){
            if(Low >=High) {
                throw new IllegalArgumentException("High end of range must be higher than lower end");
            }
            return ThreadLocalRandom.current().nextInt((High - Low)+1)+Low;
    }

}
