package com.sonogong.studynugu;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class TimerFragment extends Fragment {

    ViewGroup viewGroup;
    ImageButton play_pause;
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
        String easy_outTime = String.format("%02d:%02d:%02d", outTime/1000 / 60, (outTime/1000)%60,(outTime%1000)/10);
        return easy_outTime;

    }

}