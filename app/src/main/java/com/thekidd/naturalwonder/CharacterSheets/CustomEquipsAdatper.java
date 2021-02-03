package com.thekidd.naturalwonder.CharacterSheets;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.thekidd.naturalwonder.R;

import java.util.ArrayList;

public class CustomEquipsAdatper extends ArrayAdapter<EquimentPiece> {
    ArrayList<EquimentPiece> classList;
    Activity context;

    public CustomEquipsAdatper(Activity applicationContext, ArrayList<EquimentPiece> classList) {
        super(applicationContext, 0, classList);
        this.classList = classList;
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinneritem, parent, false);
        }

        TextView textViewName = convertView.findViewById(R.id.textView88);
        textViewName.setText(classList.get(position).getTitle());
        return convertView;
    }
}
