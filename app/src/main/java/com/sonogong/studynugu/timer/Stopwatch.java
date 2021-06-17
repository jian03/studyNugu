package com.sonogong.studynugu.timer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Stopwatch {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String swTitle;
    private String swTime;

    public Stopwatch(String swTitle, String swTime){
        this.swTitle = swTitle;
        this.swTime = swTime;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getSwTitle() {
        return swTitle;
    }
    public void setSwTitle(String swTitle) {
        this.swTitle = swTitle;
    }
    public String getSwTime() {
        return swTime;
    }
    public void setSwTime(String swTime) {
        this.swTime = swTime;
    }

}
