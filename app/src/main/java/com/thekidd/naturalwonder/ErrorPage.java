package com.thekidd.naturalwonder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ErrorPage extends Activity {
    Button BackButt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_page);

        try{BackButt = findViewById(R.id.BackButt);} catch (NullPointerException e){e.printStackTrace();}
        if(BackButt!=null){
            BackButt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ErrorPage.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
    }
}
