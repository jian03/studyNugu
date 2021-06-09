package com.sonogong.studynugu;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Dday.class}, version = 1)
public abstract class DdayDatabase extends RoomDatabase {

    public abstract DdayDAO ddayDAO();
}
