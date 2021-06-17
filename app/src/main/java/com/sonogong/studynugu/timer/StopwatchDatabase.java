package com.sonogong.studynugu.timer;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Stopwatch.class}, version = 1)
public abstract class StopwatchDatabase extends RoomDatabase {

    public abstract StopwatchDAO stopwatchDAO();

}
