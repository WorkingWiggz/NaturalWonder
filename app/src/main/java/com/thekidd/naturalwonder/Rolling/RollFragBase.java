package com.thekidd.naturalwonder.Rolling;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.thekidd.naturalwonder.R;

import static android.content.Context.MODE_PRIVATE;

public class RollFragBase extends Fragment {

    protected Boolean ThemeMode;
    int Theme;

    public RollFragBase() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences a = getContext().getSharedPreferences("NWSharedPrefs", MODE_PRIVATE);
        Theme = a.getInt("theme", R.style.MainAppTheme_Light);
        if (Theme == R.style.MainAppTheme_Light) {
            getContext().getTheme().applyStyle(R.style.MainAppTheme_Light, true);
            ThemeMode = true;
        } else if (Theme == R.style.MainAppTheme_Dark) {
            getContext().getTheme().applyStyle(R.style.MainAppTheme_Dark, true);
            ThemeMode = false;
        }
        getContext().setTheme(Theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_simple_rolling, container, false);
        return rootView;
    }
}
