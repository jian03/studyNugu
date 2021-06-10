package com.sonogong.studynugu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DdayAdapter extends RecyclerView.Adapter<DdayAdapter.ViewHolder> {

    private ArrayList<String> mDataTitle = null;
    private ArrayList<String> mDataDday = null;

    private OnItemLongClickListener mLongListener = null;

    public interface OnItemLongClickListener{
        void onItemLongClick(View v, int pos);
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.mLongListener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleItem;
        TextView ddayItem;

        ViewHolder(View itemView){
            super(itemView);

            titleItem = itemView.findViewById(R.id.titleItem);
            ddayItem = itemView.findViewById(R.id.ddayItem);

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

    DdayAdapter(ArrayList<String> title, ArrayList<String> dday){
        mDataTitle = title;
        mDataDday = dday;
    }

    @NonNull
    @NotNull
    @Override
    public DdayAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.dday_item, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DdayAdapter.ViewHolder holder, int position) {
        String textTitle = mDataTitle.get(position);
        String textDday = mDataDday.get(position);
        holder.titleItem.setText(textTitle);
        holder.ddayItem.setText(textDday);
    }

    @Override
    public int getItemCount() {
        return mDataTitle.size();
    }
}
