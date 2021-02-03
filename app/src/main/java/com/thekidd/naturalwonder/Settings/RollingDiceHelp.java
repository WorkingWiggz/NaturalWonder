package com.thekidd.naturalwonder.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

public class RollingDiceHelp extends BaseNWActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rolling_dice_help);
        Button BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });
        String HelpText = "The Rolling Dice Section is a section for you to create dice to roll during your D&D sessions. You can either create an select assortment of dice that can be rolled with the press of a button or you can create custom text based dice for more precise rolling. Each Section will even tell you the combine total of these rolls. ";
        TextView t = findViewById(R.id.DescText);
        t.setText(HelpText);
    }
}