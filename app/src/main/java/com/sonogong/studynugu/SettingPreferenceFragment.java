package com.sonogong.studynugu;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.prefs.PreferenceChangeEvent;

public class SettingPreferenceFragment extends PreferenceFragment {
    SharedPreferences pref;
    View v;

    private ListPreference asmrPreference;
    private SwitchPreference darkPreference;
    private SwitchPreference timePreference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
        SwitchPreference onTimeNoti = (SwitchPreference) findPreference(this.getResources().getString(R.string.TimeNoti));

        //시간 알리미 onClickListener
        onTimeNoti.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object time) {
                if (onTimeNoti.isChecked()) {
                    Toast.makeText(onTimeNoti.getContext(), "시간 알리미 OFF", Toast.LENGTH_SHORT).show();
                    onTimeNoti.setChecked(false);
                } else {
                    Toast.makeText(onTimeNoti.getContext(), "시간 알리미 ON", Toast.LENGTH_SHORT).show();
                    onTimeNoti.setChecked(true);
                }
                return false;
            }
        });

    }



    public static class SettingsFragment extends PreferenceFragment {
        public void onCreatePreferences(Bundle saveInstanceState, String rootKey) {
            SwitchPreference onDark = (SwitchPreference) findPreference(this.getResources().getString(R.string.Dark));
            SwitchPreference darkPreference = (SwitchPreference) findPreference("@string/Dark");
            assert darkPreference != null;
            darkPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    if (darkPreference.isChecked() && AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                    return false;
                }
            });

        }
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


