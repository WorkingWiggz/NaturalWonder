package com.thekidd.naturalwonder.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.R;

public class DeveloperHelp extends BaseNWActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_help);
        Button BackButt = findViewById(R.id.BackButt);
        BackButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });
        String HelpText = "The Developer Section is a Section where you learn about me, the developer of this app. You can tap buttons to see more of my work on my website, share this app with peeps or check out how you can support me to do more things like this.";
        TextView t = findViewById(R.id.DescText);
        t.setText(HelpText);
    }
}