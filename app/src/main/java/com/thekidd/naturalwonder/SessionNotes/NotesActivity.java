package com.thekidd.naturalwonder.SessionNotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class NotesActivity extends BaseNWActivity {

    Button MenuButt, AddButt;
    ListView Sessions;
    ArrayList<File> A = new ArrayList<>();
    File sdcard;
    File dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        sdcard = getApplicationContext().getExternalFilesDir(null);
        dir = new File(sdcard + "/NaturalWonder/SessionNotes/");

        MenuButt = findViewById(R.id.MenuButt);
        AddButt = findViewById(R.id.AddButt);
        Sessions = findViewById(R.id.SessionList);
        try {
            FetchSessions();
        } catch (IOException e) {
            e.printStackTrace();
            ErrorHandle(e, this);
        }

        final Intent MenuBack = new Intent(this, MainActivity.class);
        final Intent AddBack = new Intent(this, SessionEditor.class);

        Sessions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    LoadSession(position);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });
        AddButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddBack);
            }
        });
    }

    protected void FetchSessions() throws IOException {
        File dir = Getdir();
        File[] Files = dir.listFiles();
        ArrayList<String> Titles = new ArrayList<>();
        ArrayList<String> Blurbs = new ArrayList<>();
        ArrayList<String> Dates = new ArrayList<>();
        if (Files != null) {
            A.addAll(Arrays.asList(Files));
            for (int i = 0; i < A.size(); i++) {
                File tmp = A.get(i);
                String a = tmp.getName();
                String title = a.split("_")[0];
                String b = a.split("_")[1];
                String Date = b.replace(".txt", "");
                BufferedReader br = new BufferedReader(new FileReader(tmp));
                String c = br.readLine();
                char[] d = c.toCharArray();
                String e = "";
                for (int j = 0; j < d.length; j++) {
                    char f = d[j];
                    if (j >= 150 && f == ' ') {
                        if (d.length >= 100) {
                            e = e + "...";
                            break;
                        }
                        e = e + ".";
                        break;
                    }
                    e = e + f;
                }
                String blurb = e;
                Titles.add(title);
                Blurbs.add(blurb);
                Dates.add(Date);
            }

            SessionListAdapter SLA = new SessionListAdapter(this, Titles, Blurbs, Dates, A);
            Sessions.setAdapter(SLA);
        }


    }

    private File Getdir() {
        return dir;
    }

    private void Setdir(String newdir) {
        this.dir = new File(getApplicationContext().getExternalFilesDir(null) + newdir);
    }

    protected void LoadSession(int position) throws IOException {
        File dir = Getdir();
        File file = Objects.requireNonNull(dir.listFiles())[position];
        Intent i = new Intent(this, SessionEditor.class);
        String a = file.getName();
        String title = a.split("_")[0];
        String b = a.split("_")[1];
        String Date = b.replace(".txt", "");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String Text = br.readLine();
        String c = file.getAbsolutePath();
        i.putExtra("Title", title);
        i.putExtra("Text", Text);
        i.putExtra("FilePath", c);
        startActivity(i);
    }
}

