package com.sonogong.studynugu;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatDelegate;

public class ModeActivity {
    public static final String LIGHT_MODE = "light";
    public static final String DARK_MODE = "dark";

    public static final String TAG = "Theme";

    public static void applyTheme(String theme) {
        switch (theme) {
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Log.d(TAG, "라이트 모드 적용");
                break;
            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Log.d(TAG, "다크 모드 적용");
                break;
        }
    }
    
    //마지막으로 설정한 모드 저장
    public static void modeSave(Context context, String select_mode) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("mode", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("mode", select_mode);
        editor.commit();
    }

    public static String modeLoad(Context context) {
        SharedPreferences sp;
        sp = context.getSharedPreferences("mode", context.MODE_PRIVATE);
        String load_mode = sp.getString("mode", "light");
        return load_mode;
    }
}
