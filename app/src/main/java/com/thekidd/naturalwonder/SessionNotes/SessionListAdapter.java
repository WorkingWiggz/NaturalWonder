package com.thekidd.naturalwonder.SessionNotes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.thekidd.naturalwonder.R;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class SessionListAdapter extends ArrayAdapter {

    private final ArrayList<File> Dirs;
    private final Activity context;
    private final ArrayList<String> Titles;
    private final ArrayList<String> Blurbs;
    private final ArrayList<String> Dates;

    public SessionListAdapter(Activity context, ArrayList<String> queryTitles, ArrayList<String> queryBlurbs, ArrayList<String> queryDates, ArrayList<File> Dirs) {
        super(context, R.layout.session_rows, queryTitles);
        this.context = context;
        this.Titles = queryTitles;
        this.Blurbs = queryBlurbs;
        this.Dates = queryDates;
        this.Dirs = Dirs;
    }


    public View getView(final int pos, final View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        final View rowView = inflater.inflate(R.layout.session_rows, null, true);
        TextView Title = rowView.findViewById(R.id.TitleText);
        TextView Blurb = rowView.findViewById(R.id.BlurbText);
        TextView Date = rowView.findViewById(R.id.DateText);

        Title.setText(Titles.get(pos));
        Blurb.setText(Blurbs.get(pos));
        Date.setText(Dates.get(pos));
        final int color;
        if (R.style.MainAppTheme_Light == getContext().getSharedPreferences("NWSharedPrefs", MODE_PRIVATE).getInt("theme", R.style.MainAppTheme_Light)) {
            color = R.color.LightMode_Back;
        } else {
            color = R.color.DarkMode_Back;
        }
        Button DeleteButt = rowView.findViewById(R.id.DeleButt);
        DeleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder SaveDiagB = new AlertDialog.Builder(rowView.getContext());
                SaveDiagB.setMessage("Are you sure want to delete this session? This cannot be undone.");
                SaveDiagB.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DeleteFile(Dirs.get(pos), pos);
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

    private void DeleteFile(File f, int pos) {
        boolean deleted = f.delete();
        Titles.remove(pos);
        Blurbs.remove(pos);
        Dates.remove(pos);
    }


}
