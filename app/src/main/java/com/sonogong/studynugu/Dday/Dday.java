package com.sonogong.studynugu.Dday;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Dday {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ddayTitle;
    private String date;

    public Dday(String ddayTitle, String date){
        this.ddayTitle = ddayTitle;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getDdayTitle() {
        return ddayTitle;
    }
    public void setDdayTitle(String ddayTitle) {
        this.ddayTitle = ddayTitle;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

}
