package com.example.demoapp.database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.demoapp.model.ResultsItem;

import java.util.List;

@Dao
public interface DataItemDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllData(List<ResultsItem> resultsItems);

    @Transaction
    @Query("SELECT * FROM ResultsItem")
    List<ResultsItem> getAllData();

    @Query("DELETE FROM ResultsItem")
    void deleteAllData();
}
