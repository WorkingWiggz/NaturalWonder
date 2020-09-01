package com.thekidd.naturalwonder.Rolling;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;
import com.thekidd.naturalwonder.RolingBaseNW;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;


public class SimpleRolling extends RollFragBase {
    private ArrayList<String> DiceTypes = new ArrayList<>();
    private AdvDiceAdapter ADA;

    public SimpleRolling() {
        // Required empty public constructor
    }


    public static SimpleRolling newInstance() {
        SimpleRolling fragment = new SimpleRolling();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_simple_rolling,container,false);
        ListView L = rootView.findViewById(R.id.DiceList);
        TextView TotalView = rootView.findViewById(R.id.TotalText);
        ADA = new AdvDiceAdapter(getActivity(),DiceTypes,TotalView);
        L.setAdapter(ADA);
        Button AddDice = rootView.findViewById(R.id.AddStuffButt);
        boolean ThemeMode = ((RolingBaseNW)getContext()).ThemeMode;
        final int color;
        if(ThemeMode){
            color = R.color.LightMode_Back;
        } else {
            color = R.color.DarkMode_Back;
        }
        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder ConfirmDialog = new AlertDialog.Builder(getContext());
                ConfirmDialog.setMessage(String.format("Are you sure you want to delete this dice set? (%s)",ADA.DiceRolling.get(position)));
                ConfirmDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ADA.DeleteItem(position,view);
                    }
                });
                ConfirmDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog CD = ConfirmDialog.create();
                CD.getWindow().setBackgroundDrawableResource(color);
                CD.show();
            }
        });

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add Dice Quantity and Dice Type");
        final View customLayout = inflater.inflate(R.layout.customdialog,null);
        builder.setView(customLayout);
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText DQTB = customLayout.findViewById(R.id.DQTB);
                EditText DTTB = customLayout.findViewById(R.id.DTTB);
                String a = DQTB.getText().toString();
                String b = DTTB.getText().toString();
                String c = a+"d"+b;
                ADA.AddtoList(c);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(color);

        AddDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();

            }
        });
        return rootView;
    }


}
