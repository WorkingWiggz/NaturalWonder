package com.thekidd.naturalwonder.CharacterSheets;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class CharSheetsAdapter extends ArrayAdapter {

    ArrayList<String> NameList;
    ArrayList<Integer> LevelList;
    ArrayList<String> ClassList;
    ArrayList<String> CharSheet;
    Activity context;
    int color;


    public CharSheetsAdapter(Activity context, ArrayList<String> NameList, ArrayList<Integer> LevelList, ArrayList<String> ClassList, ArrayList<String> CharSheet,int color){
        super(context, R.layout.charsheet_row,NameList);
            this.context = context;
            this.NameList = NameList;
            this.LevelList = LevelList;
            this.CharSheet = CharSheet;
            this.ClassList = ClassList;
            this.color = color;
    }

    public View getView(final int pos, final View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.charsheet_row,null,true);
        TextView ClassText = rowView.findViewById(R.id.ClassText);
        TextView LevelText = rowView.findViewById(R.id.LevelText);
        TextView NameText = rowView.findViewById(R.id.CharNameText);

        ClassText.setText(ClassList.get(pos));
        LevelText.setText(LevelList.get(pos).toString());
        NameText.setText(NameList.get(pos));

        Button DeleteButt = rowView.findViewById(R.id.DeleteButt);
        DeleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder SaveDiagB = new AlertDialog.Builder(context);
                SaveDiagB.setMessage("Are you sure want to delete this character sheet? This cannot be undone.");
                SaveDiagB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteSheet(CharSheet.get(pos),pos);
                        notifyDataSetChanged();
                    }
                });
                SaveDiagB.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog SD = SaveDiagB.create();
                SD.getWindow().setBackgroundDrawableResource(color);
                SD.show();
            }
        });

        return rowView;
    }

    private void DeleteSheet(String a, int pos) {
        File f = new File(a);
        boolean deleted = false;
        if(f.exists()){
            deleted = f.delete();
        }
        if(deleted){
            NameList.remove(pos);
            LevelList.remove(pos);
            ClassList.remove(pos);
            CharSheet.remove(pos);
        }
    }
}
