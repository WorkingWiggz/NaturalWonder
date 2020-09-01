package com.thekidd.naturalwonder.Settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import java.io.File;
import java.util.HashMap;

public class SettingsActivity extends BaseNWActivity {
    Button MenuButt,PickTheme,EraseData,EraseLocalButt,UpdateLocalButt;
    Switch LightMode,DarkMode;
    int color;
    HashMap<String,Integer> colors = new HashMap();
    final int[] Red = new int[1];
    final int[] Blue = new int[1];
    final int[] Green = new int[1];
    int ColorChangeDiff = 40;
    String SelectedColor,LightColor,DarkColor;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        EraseData = findViewById(R.id.EraseDataButt);
        LightMode = findViewById(R.id.LightModeSwitch);
        DarkMode = findViewById(R.id.DarkModeSwitch);
        EraseLocalButt = findViewById(R.id.DeleteLocalButt);
        UpdateLocalButt = findViewById(R.id.UpdateButt);
        //PickTheme = findViewById(R.id.SetThemeButt);

        if(ThemeMode){
            LightMode.setChecked(true);
            DarkMode.setChecked(false);
            color = R.color.LightMode_Back;
        } else {
            LightMode.setChecked(false);
            DarkMode.setChecked(true);
            color = R.color.DarkMode_Back;
        }

        LightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(LightMode.isChecked()){
                    SwitchToLight(savedInstanceState);
                } else {
                    SwitchToDark(savedInstanceState);
                }
            }
        });

        DarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(DarkMode.isChecked()){
                    SwitchToDark(savedInstanceState);
                } else {
                    SwitchToLight(savedInstanceState);
                }
            }
        });

        EraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder SaveDiagB = new AlertDialog.Builder(v.getContext());
                SaveDiagB.setMessage("Are you sure want to delete all App Data? This includes session notes and character sheets and cannot be undone.");
                SaveDiagB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EraseFilesAndData();
                    }
                });
                SaveDiagB.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog SD = SaveDiagB.create();
                SD.getWindow().setBackgroundDrawableResource(color);
                SD.show();
            }
        });

        MenuButt = findViewById(R.id.MenuButt);
        final Intent MenuBack = new Intent(this, MainActivity.class);
        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });
        EraseLocalButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder SaveDiagB = new AlertDialog.Builder(v.getContext());
                SaveDiagB.setMessage("Are you sure want to delete the data stored locally?");
                SaveDiagB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EraseLocalData();
                    }
                });
                SaveDiagB.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog SD = SaveDiagB.create();
                SD.getWindow().setBackgroundDrawableResource(color);
                SD.show();
            }
        });

        UpdateLocalButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder SaveDiagB = new AlertDialog.Builder(v.getContext());
                SaveDiagB.setMessage("Are you sure you want to update your local backup. This may take a while.");
                SaveDiagB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UpdateLocalData();
                    }
                });
                SaveDiagB.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog SD = SaveDiagB.create();
                SD.getWindow().setBackgroundDrawableResource(color);
                SD.show();
            }
        });

        /* PickTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Set Theme Color");
                final View DialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.customdialog_themechanger,null);
                builder.setView(DialogView);
                final SeekBar RBar,BBar,GBar;
                final TextView RText,BText,GText,SelectedColorText,LightColorText,DarkColorText;
                final Button LightModeButt,DarkModeButt,SelectedModeButt;

                RBar = DialogView.findViewById(R.id.RedSeekBar);
                BBar = DialogView.findViewById(R.id.BlueSeekBar);
                GBar = DialogView.findViewById(R.id.GreenSeekBar);



                RText = DialogView.findViewById(R.id.RedText);
                GText = DialogView.findViewById(R.id.GreenText);
                BText = DialogView.findViewById(R.id.BlueText);

                SelectedColorText = DialogView.findViewById(R.id.SelectedColorText);
                LightColorText = DialogView.findViewById(R.id.LightColorText);
                DarkColorText = DialogView.findViewById(R.id.DarkColorText);

                LightModeButt = DialogView.findViewById(R.id.LightModeButt);
                DarkModeButt = DialogView.findViewById(R.id.DarkModeButt);
                SelectedModeButt = DialogView.findViewById(R.id.SelectedColorButt);

                RBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        if(progress > 0){
                            Red[0] = progress;
                        } else {
                            Red[0] = 0;
                        }
                        RText.setText(""+Red[0]);
                        colors.put("red",Red[0]);
                        BText.setText(""+Blue[0]);
                        colors.put("blue",Blue[0]);
                        GText.setText(""+Green[0]);
                        colors.put("green",Green[0]);
                        String a = ReturnHex();
                        String b = Lighten();
                        String c = Darken();
                        SelectedColorText.setText(a);
                        LightColorText.setText(b);
                        DarkColorText.setText(c);
                        SelectedColor = a;
                        LightColor = b;
                        DarkColor = c;
                        SelectedModeButt.setBackgroundColor(Color.parseColor(a));
                        LightModeButt.setBackgroundColor(Color.parseColor(b));
                        DarkModeButt.setBackgroundColor(Color.parseColor(c));
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                GBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(progress > 0){
                            Green[0] = progress;
                        } else {
                            Green[0] = 0;
                        }

                        RText.setText(""+Red[0]);
                        colors.put("red",Red[0]);
                        BText.setText(""+Blue[0]);
                        colors.put("blue",Blue[0]);
                        GText.setText(""+Green[0]);
                        colors.put("green",Green[0]);
                        String a = ReturnHex();
                        String b = Lighten();
                        String c = Darken();
                        SelectedColorText.setText(a);
                        LightColorText.setText(b);
                        DarkColorText.setText(c);
                        SelectedModeButt.setBackgroundColor(Color.parseColor(a));
                        LightModeButt.setBackgroundColor(Color.parseColor(b));
                        DarkModeButt.setBackgroundColor(Color.parseColor(c));
                        SelectedColor = a;
                        LightColor = b;
                        DarkColor = c;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                BBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(progress > 0){
                            Blue[0] = progress;
                        } else {
                            Blue[0] = 0;
                        }
                        RText.setText(""+Red[0]);
                        colors.put("red",Red[0]);
                        BText.setText(""+Blue[0]);
                        colors.put("blue",Blue[0]);
                        GText.setText(""+Green[0]);
                        colors.put("green",Green[0]);
                        String a = ReturnHex();
                        String b = Lighten();
                        String c = Darken();
                        SelectedColorText.setText(a);
                        LightColorText.setText(b);
                        DarkColorText.setText(c);
                        SelectedModeButt.setBackgroundColor(Color.parseColor(a));
                        LightModeButt.setBackgroundColor(Color.parseColor(b));
                        DarkModeButt.setBackgroundColor(Color.parseColor(c));
                        SelectedColor = a;
                        LightColor = b;
                        DarkColor = c;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                builder.setPositiveButton("Set Theme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        Context c = DialogView.getContext();
                        Toast toast = Toast.makeText(c,"New Theme Set.",Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.getWindow().setBackgroundDrawableResource(color);
                dialog.show();
            }

        });*/
    }
    

    private String Darken() {
        String b="#";
        if((colors.get("red") - ColorChangeDiff) >= 0){
            if(colors.get("red")<16 || (colors.get("red") - ColorChangeDiff) < 16){
                b += "0"+Integer.toHexString((colors.get("red")-ColorChangeDiff));
            } else {
                b += Integer.toHexString((colors.get("red")-ColorChangeDiff));
            }
        } else {
            b += "00";
        }
        if((colors.get("green")-ColorChangeDiff) >= 0){
            if(colors.get("green")<16 || (colors.get("green") - ColorChangeDiff) < 16){
                b += "0"+Integer.toHexString((colors.get("green")-ColorChangeDiff));
            } else {
                b += Integer.toHexString((colors.get("green")-ColorChangeDiff));
            }
        } else {
            b += "00";
        }
        if((colors.get("blue")-ColorChangeDiff) >= 0){
            if(colors.get("blue")<16 || (colors.get("blue") - ColorChangeDiff) < 16){
                b += "0"+Integer.toHexString((colors.get("blue")-ColorChangeDiff));
            } else {
                b += Integer.toHexString((colors.get("blue")-ColorChangeDiff));
            }
        }else {
            b += "00";
        }
        return b;
    }

    private String Lighten(){
        String b="#";
        if((colors.get("red")+ColorChangeDiff) <= 255){
            if((colors.get("red")+ColorChangeDiff) < 16){
                b += "0"+Integer.toHexString((colors.get("red")+ColorChangeDiff));
            } else {
                b += Integer.toHexString((colors.get("red")+ColorChangeDiff));
            }
        } else {
            b += "FF";
        }
        if((colors.get("green")+ColorChangeDiff) <= 255){
            if((colors.get("red")+ColorChangeDiff) < 16){
                b += "0"+Integer.toHexString((colors.get("green")+ColorChangeDiff));
            } else {
                b += Integer.toHexString((colors.get("green")+ColorChangeDiff));
            }
        } else {
            b += "FF";
        }
        if((colors.get("blue")+ColorChangeDiff) <= 255){
            if((colors.get("red")+ColorChangeDiff) < 16){
                b += "0"+Integer.toHexString((colors.get("blue")+ColorChangeDiff));
            } else {
                b += Integer.toHexString((colors.get("blue")+ColorChangeDiff));
            }
        } else {
            b += "FF";
        }
        return b;
    }


    private String ReturnHex() {
        String hold = "#";
        if((colors.get("red") < 16)){
           hold += "0"+Integer.toHexString((colors.get("red")));
        } else {
            hold += Integer.toHexString((colors.get("red")));
        }

        if((colors.get("green") < 16)){
            hold += "0"+Integer.toHexString((colors.get("green")));
        } else {
            hold += Integer.toHexString((colors.get("green")));
        }

        if((colors.get("blue") < 16)){
            hold += "0"+Integer.toHexString((colors.get("blue")));
        } else {
            hold += Integer.toHexString((colors.get("blue")));
        }

        return hold;
    }


    private void UpdateLocalData() {
        Context c = this;
        Toast toast = Toast.makeText(c,"Updating Local Backup.",Toast.LENGTH_LONG);
        toast.show();



    }

    private void EraseLocalData() {
        File BackupFile = new File(getApplicationContext().getExternalFilesDir(null)+"NaturalWonder/BackupData.JSON");
        if(BackupFile.exists()){
            BackupFile.delete();
        }
        Context c = this;
        Toast toast = Toast.makeText(c,"Local backup data erased.",Toast.LENGTH_LONG);
        toast.show();
    }

    private void EraseFilesAndData() {
        File CS = new File(getApplicationContext().getExternalFilesDir(null)+"/NaturalWonder/CharacterSheets/");
        File SN = new File(getApplicationContext().getExternalFilesDir(null)+"/NaturalWonder/SessionNotes/");
        File BackupFile = new File(getApplicationContext().getExternalFilesDir(null)+"NaturalWonder/BackupData.JSON");

        if(CS.exists() || SN.exists() || BackupFile.exists()){
            String [] CSEntries = CS.list();
            String [] SNEntries = SN.list();
            if(CSEntries !=null){
                for (String csEntry : CSEntries) {
                    File Curr = new File(CS.getPath(), csEntry);
                    Curr.delete();
                }
            }

            if(SNEntries !=null){
                for (String snEntry : SNEntries) {
                    File Curr = new File(SN.getPath(), snEntry);
                    Curr.delete();
                }
            }

            if(BackupFile.exists()){
                BackupFile.delete();
            }
        }

        Context c = this;
        Toast toast = Toast.makeText(c,"All Data Erased.",Toast.LENGTH_LONG);
        toast.show();
    }

    public void SwitchToDark (Bundle savedInstanceState){
        DarkMode.setChecked(true);
        LightMode.setChecked(false);
        SharedPreferences a = getSharedPreferences("NWSharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor myEditor = a.edit();
        myEditor.putInt("theme",R.style.MainAppTheme_Dark);
        myEditor.apply();
        recreate();
    }

    public void SwitchToLight(Bundle savedInstanceState){
        DarkMode.setChecked(false);
        LightMode.setChecked(true);
        SharedPreferences a = getSharedPreferences("NWSharedPrefs",MODE_PRIVATE);
        SharedPreferences.Editor myEditor = a.edit();
        myEditor.putInt("theme",R.style.MainAppTheme_Light);
        myEditor.apply();
        recreate();
    }

    private void UpdateAPIValues(){
        for(int i =0;i<UniqueRequests.size();i++){
            HandleRequest(UniqueRequests.get(i));
        }
    }
}
