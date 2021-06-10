package com.sonogong.studynugu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
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
        ArrayList<String> temp = new ArrayList<>();
        try {
            temp = ddayCal(dateList);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = v.findViewById(R.id.DdayList);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        DdayAdapter adapter = new DdayAdapter(titleList, temp);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener(new DdayAdapter.OnItemLongClickListener(){
            @Override
            public void onItemLongClick(View v, int pos) {
                Toast.makeText(getActivity(), "삭제 만들 예정", Toast.LENGTH_SHORT).show();
            }
        });

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

    public ArrayList<String> ddayCal(ArrayList<String> ddayList) throws ParseException {
        ArrayList<String> cal = new ArrayList<String>();
        for(int i = 0; i < ddayList.size(); i++) {
            //dday 계산
            Calendar todayCal = Calendar.getInstance();
            Calendar ddayCal = Calendar.getInstance();
            int year = Integer.parseInt(ddayList.get(i).substring(0, 4));
            int month = Integer.parseInt(ddayList.get(i).substring(4, 6))-1;
            int day = Integer.parseInt(ddayList.get(i).substring(6, 8));
            ddayCal.set(year, month, day);

            long today = todayCal.getTimeInMillis()/86400000;
            long dday = ddayCal.getTimeInMillis()/86400000;
            long count = dday - today;

            if(count == 0)
                cal.add(i, "Today!");
            else if(count < 0)
                cal.add(i, "D+" + Math.abs(count));
            else
                cal.add(i, "D-" + count);
        }
        return cal;
    }
}