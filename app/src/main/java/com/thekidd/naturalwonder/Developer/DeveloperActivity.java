package com.thekidd.naturalwonder.Developer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

public class DeveloperActivity extends BaseNWActivity {
    TextView BrandLink, VersionText;
    String VersionNumber;
    Button MenuButt, SendFeedbackButt, VisitMEButt, ShareButt, DonateButt;
    String DonateLink = "https://workingwiggz.com/supporting-me/";
    String VisitLink = "https://workingwiggz.com/";
    ShareQuotes SQ = new ShareQuotes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        BrandLink = findViewById(R.id.BrandText);
        BrandLink.setMovementMethod(LinkMovementMethod.getInstance());
        MenuButt = findViewById(R.id.MenuButt);
        VersionText = findViewById(R.id.VersionNum);
        SendFeedbackButt = findViewById(R.id.SendFeedBackButt);
        VisitMEButt = findViewById(R.id.VisitMEButt);
        ShareButt = findViewById(R.id.ShareButt);
        DonateButt = findViewById(R.id.DonateButt);
        VisitButtSort();
        ShareButtSort();
        DonateButtSort();
        VersionNumber = "0.3";
        VersionText.setText(VersionNumber);
        final Intent MenuBack = new Intent(this, MainActivity.class);

        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });


        if (SendFeedbackButt != null) {
            SendFeedbackButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandleFeedBack(v.getContext());
                }
            });
        }
    }

    private void DonateButtSort() {
        DonateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(DonateLink);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }

    private void ShareButtSort() {
        ShareButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ShareQuote = SQ.GetRandomQuote();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, ShareQuote);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        });
    }


    private void VisitButtSort() {
        VisitMEButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse(VisitLink);
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });

    }
}
