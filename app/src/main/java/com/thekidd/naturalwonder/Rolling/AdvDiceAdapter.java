package com.thekidd.naturalwonder.Rolling;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class AdvDiceAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<String> DiceRolling;

    public AdvDiceAdapter(Activity context, ArrayList<String> DiceRolling){
        super(context, R.layout.simpledice_row);
        this.context = context;
        this.DiceRolling = DiceRolling;
    }

    public View getView(int pos,View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.simpledice_row,null,true);
        final TextView Roll = rowView.findViewById(R.id.DiceRollText);
        Button butt = rowView.findViewById(R.id.RollButt);
        Roll.setText(DiceRolling.get(pos));
        final EditText ResTB = rowView.findViewById(R.id.ResultTB);
        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Roll(ResTB,Roll);
            }
        });

        return rowView;
    }

    private void Roll(EditText resTB, TextView RollParams) {
        String a = RollParams.getText().toString();
        String[] b = a.split("d");
        int result =0;
        for(int i=0;i<Integer.parseInt(b[0]);i++){
            int c = GetRandInt(1,Integer.parseInt(b[1]));
            result = result + c;
        }
        resTB.setText(String.valueOf(result));
    }

    private int GetRandInt(int Low, int High) {
        if(Low >=High) {
            throw new IllegalArgumentException("High end of range must be higher than lower end");
        }
        return ThreadLocalRandom.current().nextInt((High - Low)+1)+Low;
    }

    void AddtoList(String c) {
        this.DiceRolling.add(c);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return DiceRolling.size();
    }

    public void DeleteItem(int pos){
        DiceRolling.remove(pos);
        notifyDataSetChanged();
    }
}
