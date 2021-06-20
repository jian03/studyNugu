package com.sonogong.studynugu.Todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonogong.studynugu.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private ArrayList<String> mTodoTitle = null;
    private ArrayList<Boolean> mComplete = null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleItem;
        CheckBox cbItem;

        ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            titleItem = itemView.findViewById(R.id.todoTitle);
            cbItem = itemView.findViewById(R.id.comp);
        }
    }

    public TodoAdapter(ArrayList<String> title, ArrayList<Boolean> Complete){
        mTodoTitle = title;
        mComplete = Complete;
    }

    @NonNull
    @NotNull
    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.todo_item, parent, false);
        TodoAdapter.ViewHolder vh = new TodoAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull TodoAdapter.ViewHolder holder, int position) {
        String textTitle = mTodoTitle.get(position);
        Boolean ch = mComplete.get(position);
        holder.titleItem.setText(textTitle);
        holder.cbItem.setChecked(ch);
    }

    @Override
    public int getItemCount() {
        return mTodoTitle.size();
    }
}
