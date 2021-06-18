package com.sonogong.studynugu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TodoFragment Todo;
    DdayFragment Dday;
    TimerFragment Timer;
    SettingFragment Setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Todo = new TodoFragment();
        Dday = new DdayFragment();
        Timer = new TimerFragment();
        Setting = new SettingFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, Todo).commit();
        getSupportActionBar().setTitle("To do");

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.TodoF: {
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, Todo).commit();
                                getSupportActionBar().setTitle("To-do");

                                return true;
                            }
                            case R.id.DdayF: {
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, Dday).commit();
                                getSupportActionBar().setTitle("D-day");

                                return true;
                            }
                            case R.id.TimerF: {
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, Timer).commit();
                                getSupportActionBar().setTitle("Timer");

                                return true;
                            }
                            case R.id.SettingF:{
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, Setting).commit();
                                getSupportActionBar().setTitle("Setting");

                                return true;
                            }
                        }
                        return false;
                    }
                }
        );
    }

}