package com.example.appmenu.database.dao;

import android.database.sqlite.SQLiteConstraintException;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.appmenu.database.entity.TypeEntity;

import java.util.List;
@Dao
public interface TypeDao
{
    @Query("SELECT * FROM type WHERE id = :id")
    LiveData<TypeEntity> getById(int id);

    @Insert
    void insert(TypeEntity client) throws
            SQLiteConstraintException;

    @Query("SELECT * FROM type")
    LiveData<List<TypeEntity>> getAll();
    @Update
    void update(TypeEntity type);
    @Delete
    void delete(TypeEntity type);
    @Query("DELETE FROM type")
    void deleteAll();
}
