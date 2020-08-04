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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class AdvDiceAdapter extends ArrayAdapter {
    private Activity context;
    private ArrayList<String> DiceRolling;
    Map<EditText,Integer> Rows = new HashMap<>();
    TextView Total;


    public AdvDiceAdapter(Activity context, ArrayList<String> DiceRolling,TextView TotalTextView){
        super(context, R.layout.simpledice_row);
        this.context = context;
        this.DiceRolling = DiceRolling;
        this.Total = TotalTextView;
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
        String d = "";
        for(int i=0;i<Integer.parseInt(b[0]);i++){
            int c = GetRandInt(1,Integer.parseInt(b[1]));
            d = d + c;
            if(i < Integer.parseInt(b[0])-1){
                d=d+", ";
            }
            result = result + c;
        }
        if(!Rows.containsKey(resTB)){
            Rows.put(resTB,result);
        }else {
            Rows.replace(resTB,result);
        }
        String e = result+" ("+d+")";
        resTB.setText(e);
        UpdateTotal();
    }

    private void UpdateTotal(){
        String hold;
        int d=0;
        for(Integer Num : Rows.values()){
            d = d + Num;
        }
        hold = "" + d;
        String f = "Total: " + hold;
        Total.setText(f);
    }

    private int GetRandInt(int Low, int High) {
        if(Low >=High) {
            throw new IllegalArgumentException("High end of range must be higher than lower end");
        }
        return ThreadLocalRandom.current().nextInt((High - Low)+1)+Low;
    }

    void AddtoList(String c) {
        this.DiceRolling.add(c);
        ResetTotal();
        notifyDataSetChanged();
    }

    private void ResetTotal() {
        Rows.clear();
        UpdateTotal();
    }


    @Override
    public int getCount() {
        return DiceRolling.size();
    }

    public void DeleteItem(int pos,View view){
        EditText ToDelete = view.findViewById(R.id.ResultTB);
        Rows.remove(ToDelete);
        DiceRolling.remove(pos);
        notifyDataSetChanged();
        UpdateTotal();
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        ResetTotal();
    }
}
