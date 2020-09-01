package com.thekidd.naturalwonder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ErrorPage extends BaseNWActivity {
    Button BackButt,SendFeedbackButt;
    TextView DebugText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);
        try{
            BackButt = findViewById(R.id.BackButt);
            DebugText = findViewById(R.id.DebugText);
            DebugText.setText(getIntent().getStringExtra("ErrorMSG"));
            SendFeedbackButt = findViewById(R.id.SendFeedBackButt);
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        if(BackButt!=null){
            BackButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ErrorPage.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }

        if(SendFeedbackButt != null){
            SendFeedbackButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HandleFeedBack(v.getContext(),getIntent().getStringExtra("ErrorMSG"),getIntent().getStringExtra("ErrorPage"));
                }
            });
        }
    }
}
