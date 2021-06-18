package com.sonogong.studynugu.timer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StopwatchDAO {

    @Query("SELECT swTitle FROM stopwatch")
    List<String> findTitle();

    @Query("SELECT swTime FROM stopwatch")
    List<String> findTime();

    @Query("SELECT swTime FROM stopwatch WHERE swTitle IN(:title)")
    String findTimetoTitle(String title);

    @Query("DELETE FROM stopwatch WHERE swTitle IN(:titles)")
    void findAndDELETE(String titles);

    @Query("SELECT COUNT(*) FROM stopwatch WHERE swTitle = (:title)")
    int countTitle(String title);

    @Query("UPDATE stopwatch SET swTime = (:time) WHERE swTitle = (:title)")
    void updateTIME(String title, String time);

    @Insert
    void insert(Stopwatch sw);

    @Delete
    void delete(Stopwatch sw);

}
