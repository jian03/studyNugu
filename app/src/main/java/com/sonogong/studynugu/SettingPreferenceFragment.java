package com.sonogong.studynugu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.room.Room;

import com.sonogong.studynugu.Dday.DdayDAO;
import com.sonogong.studynugu.Dday.DdayDatabase;
import com.sonogong.studynugu.Todo.TodoDAO;
import com.sonogong.studynugu.Todo.TodoDatabase;
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

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if(preference.getKey().equals("removeDB")){
            show();
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
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

    void show()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.DatePickerStyle);
        builder.setTitle("데이터 초기화");
        builder.setMessage("데이터를 초기화하시겠습니까?");
        builder.setPositiveButton("예",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
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
                        Toast.makeText(getActivity(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("아니오",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.show();
    }



}
