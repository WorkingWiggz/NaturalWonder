package com.thekidd.naturalwonder.LookUp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;
    private final ArrayList<String> queryTitles;


    public CustomListAdapter(Activity context, ArrayList<String> queryTitles) {
        super(context, R.layout.listview_row, queryTitles);
        this.context = context;
        this.queryTitles = queryTitles;
    }

    public View getView(int pos, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);
        TextView TitleText = rowView.findViewById(R.id.queryTitle);

        TitleText.setText(queryTitles.get(pos));
        return rowView;
    }


}
