package com.sonogong.studynugu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DdayFragment extends Fragment implements View.OnClickListener {

    ViewGroup viewGroup;
    FloatingActionButton write;
    private DdayDAO ddayDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dday, container, false);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics ();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;

        //dp별 layout 별도 적용
        Log.d("Device dp","dpHeight : : "+dpHeight+"  dpWidth : "+dpWidth+"  density : "+density);

        write = v.findViewById(R.id.floatingActionButton);
        write.setOnClickListener(this);

        //db에서 data를 arraylist로 넘겨줌
        ArrayList<String> titleList, dateList;
        DdayDatabase db = Room.databaseBuilder(getActivity(), DdayDatabase.class, "dday-db")
                .allowMainThreadQueries().build();
        ddayDAO = db.ddayDAO();
        titleList = (ArrayList<String>) ddayDAO.findTitle();
        dateList = (ArrayList<String>) ddayDAO.findDate();

        //dday 계산한 결과값을 넣어줌
        ArrayList<String> temp = new ArrayList<String>();
        temp = ddayCal(dateList);

        RecyclerView recyclerView = v.findViewById(R.id.DdayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        DdayAdapter adapter = new DdayAdapter(titleList, temp);
        recyclerView.setAdapter(adapter);

        return v;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButton:
                DdayAddDialog dialog = new DdayAddDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "tag");
                break;
        }
    }

    public ArrayList<String> ddayCal(ArrayList<String> ddayList){
        ArrayList<String> cal = new ArrayList<String>();
        for(int i = 0; i < ddayList.size(); i++) {
            //dday 계산
            String dday = "";
            Calendar calendar = Calendar.getInstance();
            dday += calendar.get(Calendar.YEAR);
            if ((calendar.get(Calendar.MONTH)+1) < 10)
                dday += "0" + (calendar.get(Calendar.MONTH)+1);
            else
                dday += (calendar.get(Calendar.MONTH)+1);
            if (calendar.get(Calendar.DATE) < 10)
                dday += "0" + calendar.get(Calendar.DATE);
            else
                dday += calendar.get(Calendar.DATE);
            int temp = Integer.parseInt(ddayList.get(i)) - Integer.parseInt(dday);
            cal.add(i, "D-" + temp);
        }
        return cal;
    }
}