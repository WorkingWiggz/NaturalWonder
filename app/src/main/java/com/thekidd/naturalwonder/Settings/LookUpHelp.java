package com.thekidd.naturalwonder.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

public class LookUpHelp extends BaseNWActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_up_help);
        Button BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });
        String HelpText = "The Lookup Section is a section where you can look for specific information on items, classes, and other things key to know during any D&D session. This is sorted into items and subsections so that you can leisurely browse for key tidbits that you may need to know about during play.";
        TextView t = findViewById(R.id.DescText);
        t.setText(HelpText);
    }
}