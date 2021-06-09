package com.sonogong.studynugu;

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

    @Insert
    void insert(Dday dday);

    @Delete
    void delete(Dday dday);
}
