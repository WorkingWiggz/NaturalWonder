package com.thekidd.naturalwonder.CharacterSheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.thekidd.naturalwonder.BaseNWActivity;
import com.thekidd.naturalwonder.MainActivity;
import com.thekidd.naturalwonder.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class SheetsActivity extends BaseNWActivity {

    Button MenuButt, NewSessButt;
    ListView CharSheets;
    ArrayList<File> A = new ArrayList<>();
    ArrayList<String> Names = new ArrayList<>();
    ArrayList<String> Levels = new ArrayList<>();
    ArrayList<String> Charnames = new ArrayList<>();
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheets);

        if (ThemeMode) {
            color = R.color.LightMode_Back;
        } else {
            color = R.color.DarkMode_Back;
        }

        MenuButt = findViewById(R.id.MenuButt);
        NewSessButt = findViewById(R.id.NewCharButt);
        CharSheets = findViewById(R.id.CharacterSheetsList);


        final Intent MenuBack = new Intent(this, MainActivity.class);
        final Intent NewSheet = new Intent(this, SetupCharacter.class);

        MenuButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MenuBack);
            }
        });

        NewSessButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NewSheet);
            }
        });

        try {
            fetchSheets();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            ErrorHandle(e, getApplicationContext());
        }
        CharSheets.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoadSheet(position);
            }
        });

    }

    private void LoadSheet(int position) {
        File Dir = new File(getApplicationContext().getExternalFilesDir(null) + "/NaturalWonder/CharacterSheets/");
        File file = Objects.requireNonNull(Dir.listFiles())[position];
        Intent i = new Intent(this, CharacterSheetsEditor.class);
        i.putExtra("FilePath", file.getAbsolutePath());
        startActivity(i);
    }

    private void fetchSheets() throws IOException, JSONException {
        File file = new File(getExternalFilesDir(null) + "/NaturalWonder/CharacterSheets/");
        File[] Files = file.listFiles();

        ArrayList<String> Names = new ArrayList<>();
        ArrayList<String> Classes = new ArrayList<>();
        ArrayList<Integer> Levels = new ArrayList<>();
        ArrayList<String> Paths = new ArrayList<>();

        if (Files != null) {
            A.addAll(Arrays.asList(Files));
            for (int i = 0; i < A.size(); i++) {
                File tmp = A.get(i);
                FileReader fileReader = new FileReader(tmp);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                if (tmp.exists()) {
                    String a = "";
                    a = bufferedReader.readLine();

                    JSONObject c = new JSONObject(a);
                    if (c.has("Name")) {
                        Names.add(c.getString("Name"));
                        int level = 0;
                        String ClassesString = "";
                        for (int j = 0; j < c.getJSONArray("ClassesData").length(); j++) {
                            if (j == c.getJSONArray("ClassesData").length() - 1) {
                                ClassesString = ClassesString + c.getJSONArray("ClassesData").getJSONObject(j).getString("name");
                            } else {
                                ClassesString = ClassesString + c.getJSONArray("ClassesData").getJSONObject(j).getString("name") + System.lineSeparator();
                            }
                            level = +c.getJSONArray("ClassesData").getJSONObject(j).getInt("level");

                        }
                        Classes.add(ClassesString);
                        Levels.add(level);
                        Paths.add(tmp.getAbsolutePath());
                    }

                }
            }
        }
        CharSheetsAdapter CSA = new CharSheetsAdapter(this, Names, Levels, Classes, Paths, color);
        CharSheets.setAdapter(CSA);
    }
}
