package com.thekidd.naturalwonder.LookUp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import java.util.ArrayList;

public class NameDescArrayAdapter extends ArrayAdapter {

    private Activity context;
    private ArrayList<String> queryNames,queryDescs;


    public NameDescArrayAdapter(Activity context, ArrayList<String> queryNames,ArrayList<String> queryDescs){
        super(context, R.layout.namedesc, queryNames);
        this.context = context;
        this.queryNames = queryNames;
        this.queryDescs = queryDescs;
    }

    public View getView(int pos, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.namedesc,null,true);
        TextView NameText = rowView.findViewById(R.id.NameText);
        TextView DescText = rowView.findViewById(R.id.DescText);
        NameText.setText(queryNames.get(pos));
        DescText.setText(queryDescs.get(pos));

        return rowView;
    }
}
