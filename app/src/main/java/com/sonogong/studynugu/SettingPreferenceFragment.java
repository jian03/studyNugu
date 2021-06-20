package com.sonogong.studynugu;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences pref;
    View v;
    ListPreference asmr;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(v!=null){
            ViewGroup parent = (ViewGroup)v.getParent();
            if(parent!=null){
                parent.removeView(v);
            }
        }

    }
}
