package com.sonogong.studynugu;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.sonogong.studynugu.Dday.DdayDAO;
import com.sonogong.studynugu.Dday.DdayDatabase;
import com.sonogong.studynugu.Todo.TodoDAO;
import com.sonogong.studynugu.Todo.TodoDatabase;
import com.sonogong.studynugu.timer.Stopwatch;
import com.sonogong.studynugu.timer.StopwatchDAO;
import com.sonogong.studynugu.timer.StopwatchDatabase;

public class SettingPreferenceFragment extends PreferenceFragment {

    SharedPreferences pref;
    View v;
    ListPreference asmr;
    DdayDAO ddayDAO;
    TodoDAO todoDAO;
    StopwatchDAO stopwatchDAO;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

    }

    SharedPreferences.OnSharedPreferenceChangeListener prefListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if(key.equals("removeDB")){
                DdayDatabase dbDday = Room.databaseBuilder(getActivity(), DdayDatabase.class, "dday-db")
                        .allowMainThreadQueries().build();
                ddayDAO = dbDday.ddayDAO();
                ddayDAO.deleteAll();
                TodoDatabase dbTodo = Room.databaseBuilder(getActivity(), TodoDatabase.class, "todo-db")
                        .allowMainThreadQueries().build();
                todoDAO = dbTodo.todoDAO();
                todoDAO.deleteAll();
                StopwatchDatabase dbsw = Room.databaseBuilder(getActivity(), StopwatchDatabase.class, "sw-db")
                        .allowMainThreadQueries().build();
                stopwatchDAO = dbsw.stopwatchDAO();
                stopwatchDAO.deleteAll();
            }
        }
    };

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
