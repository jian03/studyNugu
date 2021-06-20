package com.sonogong.studynugu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sonogong.studynugu.Dday.DdayAdapter;
import com.sonogong.studynugu.Dday.DdayAddDialog;
import com.sonogong.studynugu.Dday.DdayDatabase;
import com.sonogong.studynugu.Todo.TodoAdapter;
import com.sonogong.studynugu.Todo.TodoAddDialog;
import com.sonogong.studynugu.Todo.TodoDAO;
import com.sonogong.studynugu.Todo.TodoDatabase;

import java.util.ArrayList;

public class TodoFragment extends Fragment {

    ViewGroup viewGroup;
    FloatingActionButton write;
    TodoDAO todoDAO;
    CheckBox checkBox;
    TodoDatabase db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_todo, container, false);

        write = v.findViewById(R.id.add_todo);
        checkBox = v.findViewById(R.id.comp);

        //db에서 data를 arraylist로 넘겨줌
        db = Room.databaseBuilder(getActivity(), TodoDatabase.class, "todo-db")
                .allowMainThreadQueries().build();
        todoDAO = db.todoDAO();
        ArrayList<String> titleList = (ArrayList<String>) todoDAO.findTitle();
        ArrayList<Boolean> compList = (ArrayList<Boolean>) todoDAO.findCompleted();

        RecyclerView recyclerView = v.findViewById(R.id.TodoList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        TodoAdapter adapter = new TodoAdapter(titleList, compList);
        recyclerView.setAdapter(adapter);

        write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TodoAddDialog dialog = new TodoAddDialog();
                dialog.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });

        return v;
    }

}