package com.thekidd.naturalwonder.SessionNotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SessionEditor extends BaseNWActivity {

    TextView BackButt,SaveButt;
    Boolean Saved = false;
    EditText TitleText, MainBodyText;
    File LoadedFile = null;

    boolean tFirst = true;
    boolean mFirst = true;
    boolean Loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_editor);
        BackButt = findViewById(R.id.BackButt);
        SaveButt = findViewById(R.id.SaveButt);
        TitleText = findViewById(R.id.SetTitle);
        MainBodyText = findViewById(R.id.MainMulti);
        final String BaseTitle = TitleText.getText().toString();
        final String BaseMain = MainBodyText.getText().toString();
        if((getIntent().getStringExtra("Title") != null && getIntent().getStringExtra("Text")!= null )){
            String titlehold=getIntent().getStringExtra("Title");
            String Texthold = getIntent().getStringExtra("Text");
            TitleText.setText(titlehold);
            MainBodyText.setText(Texthold);
            if(getIntent().getStringExtra("FilePath") != null) {
                LoadedFile = new File(getIntent().getStringExtra("FilePath"));
            }
            Loaded = true;

        }


        TitleText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Saved = false;
                if(tFirst){
                    tFirst = false;
                    String a = TitleText.getText().toString();
                    String b =  a.replace(BaseTitle,"");
                    TitleText.setText(b.toUpperCase());
                    TitleText.setSelection(TitleText.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        MainBodyText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Saved = false;
                if(mFirst){
                     mFirst = false;
                    String a = MainBodyText.getText().toString();
                    String b = a.replace(BaseMain,"");
                    MainBodyText.setText(b.toUpperCase());
                    MainBodyText.setSelection(MainBodyText.getText().length());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BackToEditor();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        SaveButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SaveSession();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void BackToEditor() throws IOException {
        Intent i = new Intent(this, NotesActivity.class);
        if(Saved){
            startActivity(i);
        } else {
           Boolean hold = NotSavedDialog();
           if(hold){
                SaveSession();
                startActivity(i);
           } else{
               startActivity(i);
           }
        }
    }

    private boolean NotSavedDialog() {
        final boolean[] hold = {false};
        AlertDialog.Builder SaveDiagB = new AlertDialog.Builder(this);
        SaveDiagB.setMessage("Do you want to save this session?");
        SaveDiagB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hold[0] = true;
            }
        });
        SaveDiagB.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hold[0] = false;
            }
        });
        AlertDialog SD = SaveDiagB.create();
        SD.show();
        return hold[0];
    }

    public void SaveSession() throws IOException {
        boolean a = TitleText.getText().toString().isEmpty();
        boolean b = MainBodyText.getText().toString().isEmpty();


       if (Loaded) {
         if(LoadedFile.exists()){
             FileWriter fw = new FileWriter(LoadedFile);
             fw.write(MainBodyText.getText().toString());
             fw.flush();
             fw.close();
         }
        } else {
           if (!a && !b) { //If title and main body are not empty.
               try {
                   File sdcard = getApplicationContext().getExternalFilesDir(null);
                   File dir = new File(sdcard + "/NaturalWonder/SessionNotes/");

                   if (!dir.exists()) {
                       dir.mkdirs();
                   }

                   Calendar calendar = Calendar.getInstance();
                   SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                   String date = dateFormat.format(calendar.getTime());
                   String title = TitleText.getText().toString();
                   String core = title + "_" + date;
                   String name = core + ".txt";
                   File f = new File(dir, name);
                   boolean pop = false;
                   if (!f.exists()) {
                       pop = f.createNewFile();
                       FileWriter fw = new FileWriter(f);
                       fw.write(MainBodyText.getText().toString());
                       fw.flush();
                       fw.close();
                   }
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }
        Saved = true;
       BackToEditor();
    }

}
