package com.sonogong.studynugu.Dday;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.sonogong.studynugu.Dday.Dday;
import com.sonogong.studynugu.Dday.DdayDAO;
import com.sonogong.studynugu.Dday.DdayDatabase;
import com.sonogong.studynugu.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DdayAddDialog extends DialogFragment implements View.OnClickListener {

    private Fragment fragment;
    private TextView today;
    private EditText dday_Title;
    private Button okBtn, cancleBtn, dateBtn;
    Date mDate = new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월dd일");
    SimpleDateFormat sdf_today = new SimpleDateFormat("yyyyMMdd");
    String getDay = simpleDateFormat.format(mDate);
    private String dday = sdf_today.format(mDate);

    public DdayAddDialog(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.dday_add, container, false);
        today = v.findViewById(R.id.today);
        today.setText(getDay);

        dateBtn = (Button)v.findViewById(R.id.dateBtn);
        okBtn = (Button)v.findViewById(R.id.okButton);
        cancleBtn = (Button)v.findViewById(R.id.cancelButton);
        dday_Title = (EditText)v.findViewById(R.id.ddayTitle);

        dateBtn.setOnClickListener(this);
        okBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);


        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

//

        return v;
    }

    private DdayDAO ddayDAO;
    private int i = 0;


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dateBtn:{
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dday = "";
                        dday += year;
                        if (month+1 < 10)
                            dday += "0" + (month+1);
                        else
                            dday += (month+1);
                        if (dayOfMonth < 10)
                            dday += "0" + dayOfMonth;
                        else
                            dday += dayOfMonth;

                        today.setText(year + "년" + (month+1) + "월" + dayOfMonth + "일");

                    }
                };
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), R.style.DatePickerStyle,
                        listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
                break;
            }
            case R.id.okButton:{
                DdayDatabase db = Room.databaseBuilder(getActivity(), DdayDatabase.class, "dday-db")
                        .allowMainThreadQueries().build();
                ddayDAO = db.ddayDAO();

                Dday dday = new Dday(dday_Title.getText().toString(), this.dday);
                ddayDAO.insert(dday);

                if (fragment != null){
                    DialogFragment dialogFragment = (DialogFragment) fragment;
                    dialogFragment.dismiss();
                }

                break;
            }
            case R.id.cancelButton:{
                if (fragment != null){
                    DialogFragment dialogFragment = (DialogFragment) fragment;
                    dialogFragment.dismiss();
                }
                break;
            }
        }
    }
}
