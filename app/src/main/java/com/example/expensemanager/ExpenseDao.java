package com.example.expensemanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ExpenseDao {

    @Insert
    void insertrecord(Expense expenses);

    @Update
    void updaterecord(Expense expenses);

    @Delete
    void delete(Expense expenses);

    @Query("SELECT EXISTS(SELECT * FROM Expense WHERE id = :Id)")
    Boolean is_exist(int Id);

    @Query("DELETE FROM Expense WHERE id = :id")
    void deleterecord(int id);

    @Query("SELECT * FROM Expense")
    List<Expense> getallexpenses();


}