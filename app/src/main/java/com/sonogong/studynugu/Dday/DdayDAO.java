package com.sonogong.studynugu.Dday;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DdayDAO {

    @Query("SELECT ddayTitle FROM dday")
    List<String> findTitle();

    @Query("SELECT date FROM dday")
    List<String> findDate();

    @Query("DELETE FROM dday WHERE ddaytitle IN(:titles)")
    void findAndDELETE(String titles);

    @Insert
    void insert(Dday dday);

    @Delete
    void delete(Dday dday);

    @Query("DELETE FROM dday")
    void deleteAll();

}
