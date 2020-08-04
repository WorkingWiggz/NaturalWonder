package com.thekidd.naturalwonder.Developer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

public class DeveloperActivity extends BaseNWActivity {
    TextView BrandLink,VersionText;
    String VersionNumber;
    Button MenuButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        BrandLink = findViewById(R.id.BrandText);
        BrandLink.setMovementMethod(LinkMovementMethod.getInstance());
        MenuButt = findViewById(R.id.MenuButt);
        VersionText = findViewById(R.id.VersionNum);
        VersionNumber = "0.1";
        VersionText.setText(VersionNumber);
        final Intent MenuBack = new Intent(this, MainActivity.class);

        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });
    }
}
