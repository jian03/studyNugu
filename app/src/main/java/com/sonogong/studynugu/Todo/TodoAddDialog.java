package com.sonogong.studynugu.Todo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.sonogong.studynugu.Dday.Dday;
import com.sonogong.studynugu.Dday.DdayDatabase;
import com.sonogong.studynugu.R;

public class TodoAddDialog extends DialogFragment implements View.OnClickListener {

    private Fragment fragment;
    private EditText todoTitle;
    private CheckBox comp;
    private Button okBtn, cancleBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_add, container, false);

        todoTitle = v.findViewById(R.id.todoTitle);
        okBtn = (Button)v.findViewById(R.id.okButtontd);
        cancleBtn = (Button)v.findViewById(R.id.cancelButtontd);

        okBtn.setOnClickListener(this);
        cancleBtn.setOnClickListener(this);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

//

        return v;
    }

    private TodoDAO todoDAO;

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.okButtontd:{
                TodoDatabase db = Room.databaseBuilder(getActivity(), TodoDatabase.class, "todo-db")
                        .allowMainThreadQueries().build();
                todoDAO = db.todoDAO();

                Todo todo = new Todo(todoTitle.getText().toString(), false);
                todoDAO.insert(todo);

                if (fragment != null){
                    DialogFragment dialogFragment = (DialogFragment) fragment;
                    dialogFragment.dismiss();
                }

                break;
            }
            case R.id.cancelButtontd:{
                if (fragment != null){
                    DialogFragment dialogFragment = (DialogFragment) fragment;
                    dialogFragment.dismiss();
                }
                break;
            }
        }
    }
}
