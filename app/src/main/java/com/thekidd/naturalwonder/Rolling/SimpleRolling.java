package com.thekidd.naturalwonder.Rolling;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.thekidd.naturalwonder.R;

import java.util.ArrayList;
import java.util.Objects;


public class SimpleRolling extends Fragment {
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
        ADA = new AdvDiceAdapter(getActivity(),DiceTypes);
        L.setAdapter(ADA);
        Button AddDice = rootView.findViewById(R.id.AddStuffButt);
        L.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ADA.DeleteItem(position);
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        builder.setTitle("Add Quantity and Dice Type");
        final View customLayout = getLayoutInflater().inflate(R.layout.customdialog,null);
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

        AddDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    dialog.show();
            }
        });
        return rootView;
    }


}
