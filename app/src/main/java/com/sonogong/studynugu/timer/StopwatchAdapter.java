package com.sonogong.studynugu.timer;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sonogong.studynugu.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StopwatchAdapter extends RecyclerView.Adapter<StopwatchAdapter.ViewHolder> {

    private ArrayList<String> mDataTitle = null;
    private ArrayList<String> mDataTime = null;

    public ArrayList<String> getmDataTitle() {
        return mDataTitle;
    }
    public void setmDataTitle(ArrayList<String> mDataTitle) {
        this.mDataTitle = mDataTitle;
    }
    public ArrayList<String> getmDataTime() {
        return mDataTime;
    }
    public void setmDataTime(ArrayList<String> mDataDday) {
        this.mDataTime = mDataDday;
    }

    private StopwatchAdapter.OnItemLongClickListener mLongListener = null;

    public interface OnItemLongClickListener{
        void onItemLongClick(View v, int pos);
    }

    public void setOnItemLongClickListener(StopwatchAdapter.OnItemLongClickListener listener){
        this.mLongListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleItem;
        TextView timeItem;

        ViewHolder(View itemView){
            super(itemView);

            titleItem = itemView.findViewById(R.id.titleItem);
            timeItem = itemView.findViewById(R.id.ddayItem);

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        mLongListener.onItemLongClick(v, pos);
                    }
                    return true;
                }
            });
        }
    }

    public StopwatchAdapter(ArrayList<String> title, ArrayList<String> time){
        mDataTitle = title;
        mDataTime = time;
    }

    @NonNull
    @NotNull
    @Override
    public StopwatchAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull StopwatchAdapter.ViewHolder holder, int position) {
        String textTitle = mDataTitle.get(position);
        String textTime = mDataTime.get(position);
        holder.titleItem.setText(textTitle);
        holder.timeItem.setText(textTime);
    }

    @Override
    public int getItemCount() {
        return mDataTitle.size();
    }
}
