package com.thekidd.naturalwonder.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

public class SettingsHelp extends BaseNWActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_help);
        Button BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });
        String HelpText = "The Setting Section is a section dedicated to allowing you to change key settings such as switching between light and dark mode, managing save data and learning about how to use the different sections of the app.";
        TextView t = findViewById(R.id.DescText);
        t.setText(HelpText);
    }
}