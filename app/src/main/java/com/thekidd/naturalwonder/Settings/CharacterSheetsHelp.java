package com.thekidd.naturalwonder.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

public class CharacterSheetsHelp extends BaseNWActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_sheets_help);
        Button BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });
        String HelpText = "The Character Sheets Section is a section where you can create your own character and manage your character sheet within it. Here you can select all the details you would need to create your character including being able to roll tour ability scores. Once your character is created you can level it up, add equipment, weapons and spells.";
        TextView t = findViewById(R.id.DescText);
        t.setText(HelpText);

    }
}