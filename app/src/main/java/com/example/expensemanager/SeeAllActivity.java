package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.List;

public class SeeAllActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_all);


        getroomdata();
    }


    public void getroomdata(){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "expenseroom_dbbb").allowMainThreadQueries().build();

        ExpenseDao expenseDao = db.expenseDao();
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Expense> expenses = expenseDao.getallexpenses();
        MyAdapter adapter = new MyAdapter(expenses);
        recyclerView.setAdapter(adapter);

    }
}