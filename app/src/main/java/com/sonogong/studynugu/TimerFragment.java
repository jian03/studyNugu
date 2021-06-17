package com.sonogong.studynugu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sonogong.studynugu.Dday.DdayDatabase;
import com.sonogong.studynugu.timer.Stopwatch;
import com.sonogong.studynugu.timer.StopwatchDAO;
import com.sonogong.studynugu.timer.StopwatchDatabase;

public class TimerFragment extends Fragment {

    ViewGroup viewGroup;
    ImageButton play_pause;
    EditText saveTitle;
    TextView stopwatch;
    boolean i = true;

    final static int Init = 0;
    final static int Run = 1;
    final static int Pause = 2;

    int cur_Status = Init;
    long myBaseTime;
    long myPauseTime;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_timer, container, false);

        play_pause = v.findViewById(R.id.play_pause);
        stopwatch = v.findViewById(R.id.stopwatch);
        saveTitle = v.findViewById(R.id.saveTitle);
        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (cur_Status){
                    case Init: {
                        myBaseTime = SystemClock.elapsedRealtime();
                        System.out.println(myBaseTime);
                        myTimer.sendEmptyMessage(0);
                        cur_Status = Run;
                        break;
                    }
                    case Run:{
                        myTimer.removeMessages(0);
                        myPauseTime = SystemClock.elapsedRealtime();
                        String title = saveTitle.getText().toString();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("일시정지");
                        builder.setMessage("타이머를 초기화하고 " + KoreanUtil.getComleteWordByJongsung(title, "을", "를") + " 저장하시겠습니까?");
                        builder.setPositiveButton("예",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        saveTime(title, stopwatch.getText().toString());
                                        stopwatch.setText("00:00:00");
                                        cur_Status = Init;
                                        return;
                                    }
                                });
                        builder.setNegativeButton("아니오",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                        builder.show();
                        cur_Status = Pause;
                        break;
                    }
                    case Pause:{
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += (now-myPauseTime);
                        cur_Status = Run;
                        break;
                    }
                }
                if(i == true){
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_pause, null);
                    play_pause.setImageDrawable(drawable);
                    i = false;
                }else{
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_play, null);
                    play_pause.setImageDrawable(drawable);
                    i = true;
                }
            }
        });

        return v;
    }

    Handler myTimer = new Handler(){
        public void handleMessage(Message msg){
            stopwatch.setText(getTimeOut());

            //sendEmptyMessage 는 비어있는 메세지를 Handler 에게 전송하는겁니다.
            myTimer.sendEmptyMessage(0);
        }
    };
    //현재시간을 계속 구해서 출력하는 메소드
    String getTimeOut(){
        long now = SystemClock.elapsedRealtime(); //애플리케이션이 실행되고나서 실제로 경과된 시간(??)^^;
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d:%02d", (outTime/1000/60/60), (outTime/1000/60)%60, (outTime/1000)%60);
        return easy_outTime;

    }

    private StopwatchDAO stopwatchDAO;

    public void saveTime(String title, String time){
        StopwatchDatabase db = Room.databaseBuilder(getActivity(), StopwatchDatabase.class, "sw-db")
                .allowMainThreadQueries().build();
        stopwatchDAO = db.stopwatchDAO();

        Stopwatch sw = new Stopwatch(title, time);
        stopwatchDAO.insert(sw);
    }

    public static class KoreanUtil {
        public static final String getComleteWordByJongsung(String name, String firstValue, String secondValue) {

            char lastName = name.charAt(name.length() - 1);

            // 한글의 제일 처음과 끝의 범위밖일 경우는 오류
            if (lastName < 0xAC00 || lastName > 0xD7A3) {
                return name;
            }

            String seletedValue = (lastName - 0xAC00) % 28 > 0 ? firstValue : secondValue;

            return name+seletedValue;
        }

    }

}