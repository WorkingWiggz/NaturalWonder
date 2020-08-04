package com.thekidd.naturalwonder.Rolling;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenu;
import com.thekidd.naturalwonder.R;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

import io.github.yavski.fabspeeddial.FabSpeedDial;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FancyRolling#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FancyRolling extends RollFragBase {
    int poscounter =0;
    int DiceTotal =0;
    TextView TotalText;
    private ArrayList<Integer> summaryDice = new ArrayList<>();

    public FancyRolling() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FancyRolling newInstance() {
        FancyRolling fragment = new FancyRolling();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       View rootView = inflater.inflate(R.layout.fragment_three_d_rolling,container,false);
        Button RollButton = rootView.findViewById(R.id.RollButt);
        final ArrayList<LinearLayout> DiceGroups = new ArrayList<LinearLayout>();
        final FlowLayout DiceGroupCenter = rootView.findViewById(R.id.DiceGroupCenter);
        FabSpeedDial FAB = rootView.findViewById(R.id.AddStuffButt);
        TotalText = rootView.findViewById(R.id.TotalText);


        RollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice(DiceGroupCenter);
            }
        });
        FAB.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                boolean hold = true;
            if(hold){
                switch(menuItem.getItemId()){
                    case R.id.action_DiceHundred:
                        AddD00(DiceGroupCenter);
                        poscounter++;
                        break;
                    case R.id.action_DiceTwenty:
                        AddD20(DiceGroupCenter);
                        poscounter++;
                        break;
                    case R.id.action_DiceTwelve:
                        AddD12(DiceGroupCenter);
                        poscounter++;
                        break;
                    case R.id.action_DiceTen:
                        AddD10(DiceGroupCenter);
                        poscounter++;
                        break;
                    case R.id.action_DiceEight:
                        AddD8(DiceGroupCenter);
                        poscounter++;
                        break;
                    case R.id.action_DiceSix:
                        AddD6(DiceGroupCenter);
                        poscounter++;
                        break;
                    case R.id.action_DiceFour:
                        AddD4(DiceGroupCenter);
                        poscounter++;
                        break;
                }
                if(poscounter>=DiceGroups.size()){
                    poscounter=0;
                }
            } else {
                Context c = getContext();
                Toast toast = Toast.makeText(c,"Limit reached, please delete dice to add more.",Toast.LENGTH_LONG);
                toast.show();
            }
                return false;
            }

            @Override
            public void onMenuClosed() {

            }
        });
        return rootView;
    }

    private void RollDice(FlowLayout dicePlace) {
        int count = dicePlace.getChildCount();
        int totalDice=0;
        ArrayList<Integer> SummaryDice = new ArrayList<Integer>();
        for(int i =0;i<count;i++){
            Button b = (Button) dicePlace.getChildAt(i);
            int randInt=0;
                if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d00,null).getConstantState()){
                    randInt = getRandInt(100, 1);
                    String a;
                    if(randInt == 100){
                        a = String.valueOf(00);
                    } else if (randInt<10){
                        a = "0"+randInt;
                    } else {
                        a = String.valueOf(randInt);
                    }
                    b.setText(a);
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d20,null).getConstantState()){
                    randInt = getRandInt(20,1);
                    String a = String.valueOf(randInt);
                    b.setText(a);
                } else  if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d12,null).getConstantState()){
                    randInt = getRandInt(12,1);
                    String a = String.valueOf(randInt);
                    b.setText(a);
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d10,null).getConstantState()){
                    randInt = getRandInt(10, 1);
                    String a = String.valueOf(randInt);
                    b.setText(a);
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d8,null).getConstantState()){
                    randInt = getRandInt(8, 1);
                    String a = String.valueOf(randInt);
                    b.setText(a);
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d6,null).getConstantState()){
                    randInt = getRandInt(6, 1);
                    String a = String.valueOf(randInt);
                    b.setText(a);
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d4,null).getConstantState()){
                    randInt = getRandInt(4, 1);
                    String a = String.valueOf(randInt);
                    b.setText(a);
                }
            totalDice = totalDice + randInt;
            SummaryDice.add(randInt);
            UpdateTotal(totalDice,SummaryDice);
        }
    }

    private void UpdateTotal(int totalDice, ArrayList<Integer> summaryDice) {
       String a = "Total: ";
       DiceTotal = totalDice;
       this.summaryDice = summaryDice;
       a = a + totalDice + " ";
       String b ="(";
       for(int i =0;i<summaryDice.size();i++){
           String c = summaryDice.get(i).toString();
           b = b + c;
           if(i<summaryDice.size()-1){
               b = b + ", ";
           } else if (i == summaryDice.size()-1){
               b = b + ")";
           }
       }
       a = a + b;
       TotalText.setText(a);
    }

    private void UpdateTotal(int NewDice) {
        String a = "Total: ";
        DiceTotal = DiceTotal + NewDice;
        a = a + DiceTotal + " ";
        summaryDice.add(NewDice);
        String b ="(";
        for(int i =0;i<summaryDice.size();i++){
            String c = summaryDice.get(i).toString();
            b = b + c;
            if(i<summaryDice.size()-1){
                b = b + ", ";
            } else if (i == summaryDice.size()-1){
                b = b + ")";
            }
        }
        a = a + b;
        TotalText.setText(a);
    }

    private void UpdateTotal() {
        String a = "Total: ";
        a = a + DiceTotal + " ";
        String b ="(";
        for(int i =0;i<summaryDice.size();i++){
            String c = summaryDice.get(i).toString();
            b = b + c;
            if(i<summaryDice.size()-1){
                b = b + ", ";
            } else if (i == summaryDice.size()-1){
                b = b + ")";
            }
        }
        if(summaryDice.size() !=0){
            a = a + b;
        }
        TotalText.setText(a);
    }

    private int getRandInt(int High,int Low) {
        if(Low >=High) {
            throw new IllegalArgumentException("High end of range must be higher than lower end");
        }
        return ThreadLocalRandom.current().nextInt((High - Low)+1)+Low;
    }

    private void DeleteDice(View v) {
        Button a = (Button) v;
        String b = (String) a.getText();
        int c = Integer.parseInt(b);
        DiceTotal = DiceTotal - c;
        ViewGroup d = (ViewGroup)v.getParent();
        summaryDice.remove(d.indexOfChild(v));
        FlowLayout LL = (FlowLayout) v.getParent();
        LL.removeView(v);
        UpdateTotal();
    }

    private void AddD00(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d00);
        newD.setText("00");
        newD.setTextSize(20f);
        newD.setTextColor(Color.parseColor("#FDFAFD"));
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(100);
    }

    private void AddD20(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d20);
        newD.setText("20");
        newD.setTextSize(20f);
        newD.setTextColor(Color.parseColor("#FDFAFD"));
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(20);
    }

    private void AddD12(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d12);
        newD.setText("12");
        newD.setTextSize(20f);
        newD.setTextColor(Color.parseColor("#FDFAFD"));
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(12);
    }

    private void AddD10(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d10);
        newD.setText("10");
        newD.setTextSize(20f);
        newD.setTextColor(Color.parseColor("#000000"));
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(10);
    }

    private void AddD8(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d8);
        newD.setText("8");
        newD.setTextSize(20f);
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(8);
    }

    private void AddD6(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d6);
        newD.setText("6");
        newD.setTextSize(20f);
        newD.setTextColor(Color.parseColor("#FDFAFD"));
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(6);
    }

    private void AddD4(FlowLayout rootLayout) {
        Button newD = new Button(rootLayout.getContext());
        newD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteDice(v);
            }
        });
        newD.setBackgroundResource(R.drawable.d4);
        newD.setText("4");
        newD.setTextSize(20f);
        newD.setTextColor(Color.parseColor("#FDFAFD"));
        rootLayout.addView(newD,  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        UpdateTotal(4);
    }

}
