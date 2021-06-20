package com.sonogong.studynugu.Todo;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDAO {

    @Query("SELECT todoTitle FROM todo")
    List<String> findTitle();

    @Query("SELECT completed FROM todo")
    List<Boolean> findCompleted();

    @Query("UPDATE todo SET completed = 1 WHERE todoTitle = (:title)")
    void updateCompleted(String title);

    @Query("DELETE FROM todo WHERE todoTitle IN(:titles)")
    void findAndDELETE(String titles);

    @Insert
    void insert(Todo todo);

    @Delete
    void delete(Todo todo);

}
