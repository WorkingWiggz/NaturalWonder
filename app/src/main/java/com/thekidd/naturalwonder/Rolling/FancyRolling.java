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
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenu;
import com.thekidd.naturalwonder.R;

import java.util.concurrent.ThreadLocalRandom;

import io.github.yavski.fabspeeddial.FabSpeedDial;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FancyRolling#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FancyRolling extends Fragment {

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
        final LinearLayout DicePlace = rootView.findViewById(R.id.DiceGroup);
        FabSpeedDial FAB = rootView.findViewById(R.id.AddStuffButt);

        RollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RollDice(DicePlace);
            }
        });
        FAB.setMenuListener(new FabSpeedDial.MenuListener() {
            @Override
            public boolean onPrepareMenu(NavigationMenu navigationMenu) {
                return true;
            }

            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int count = DicePlace.getChildCount();
            if(count<5){
                switch(menuItem.getItemId()){
                    case R.id.action_DiceHundred:
                        AddD00(DicePlace);
                        break;
                    case R.id.action_DiceTwenty:
                        AddD20(DicePlace);
                        break;
                    case R.id.action_DiceTwelve:
                        AddD12(DicePlace);
                        break;
                    case R.id.action_DiceTen:
                        AddD10(DicePlace);
                        break;
                    case R.id.action_DiceEight:
                        AddD8(DicePlace);
                        break;
                    case R.id.action_DiceSix:
                        AddD6(DicePlace);
                        break;
                    case R.id.action_DiceFour:
                        AddD4(DicePlace);
                        break;

                }
            } else {
                Context c = getContext();
                Toast toast = Toast.makeText(c,"Limit is five dice, please delete dice to add more.",Toast.LENGTH_LONG);
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

    private void RollDice(LinearLayout dicePlace) {
        int count = dicePlace.getChildCount();
        for(int i =0;i<count;i++){
            Button b = (Button) dicePlace.getChildAt(i);
                if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d00,null).getConstantState()){
                    String a = (getRandInt(10, 1) - 1) +"0";
                    b.setText(a);
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d20,null).getConstantState()){
                    b.setText(String.valueOf(getRandInt(20,1)));
                } else  if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d12,null).getConstantState()){
                    b.setText(String.valueOf(getRandInt(12,1)));
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d10,null).getConstantState()){
                    b.setText(String.valueOf(getRandInt(10,1)));
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d8,null).getConstantState()){
                    b.setText(String.valueOf(getRandInt(8,1)));
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d6,null).getConstantState()){
                    b.setText(String.valueOf(getRandInt(6,1)));
                } else if(b.getBackground().getConstantState() == getResources().getDrawable(R.drawable.d4,null).getConstantState()){
                    b.setText(String.valueOf(getRandInt(4,1)));
                }
        }
    }

    private int getRandInt(int High,int Low) {
        if(Low >=High) {
            throw new IllegalArgumentException("High end of range must be higher than lower end");
        }
        return ThreadLocalRandom.current().nextInt((High - Low)+1)+Low;
    }

    private void DeleteDice(View v) {
        LinearLayout LL = (LinearLayout) v.getParent();
        LL.removeView(v);
    }

    private void AddD00(LinearLayout rootLayout) {
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

        rootLayout.addView(newD);
    }

    private void AddD20(LinearLayout rootLayout) {
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
        rootLayout.addView(newD);
    }

    private void AddD12(LinearLayout rootLayout) {
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
        rootLayout.addView(newD);
    }

    private void AddD10(LinearLayout rootLayout) {
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
        rootLayout.addView(newD);
    }

    private void AddD8(LinearLayout rootLayout) {
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
        rootLayout.addView(newD);
    }

    private void AddD6(LinearLayout rootLayout) {
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
        rootLayout.addView(newD);
    }

    private void AddD4(LinearLayout rootLayout) {
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
        rootLayout.addView(newD);
    }

}
