package com.example.expensemanager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    FloatingActionButton addFabButton;

    long expense = 0;
    long income = 0;

    TextView t1,t2,totalamount;

    CardView seeall;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFabButton = findViewById(R.id.addFabButtonId);
        t1 = findViewById(R.id.incomeid);
        t2 = findViewById(R.id.expenseId);
        totalamount = findViewById(R.id.totalAmountId);
        seeall = findViewById(R.id.seeallId);

        addFabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), AddActivity.class));

            }
        });


        seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), SeeAllActivity.class));
            }
        });


        getroomdata();
    }


    public void getroomdata(){

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "expenseroom_dbbb").allowMainThreadQueries().build();

        ExpenseDao expenseDao = db.expenseDao();
        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Expense> expenses = expenseDao.getallexpenses();

        ///================This part has been using for arraylist theke position find  kore income and expense er value create korer jonno
        for (int i = 0;i<expenses.size();i++)
        {
            if(expenses.get(i).isIncomeExpense()){
                income = income + expenses.get(i).getAmount();
                t1.setText(" "+income);
            }else {
                expense = expense + expenses.get(i).getAmount();
                t2.setText(""+expense);

            }
            //=====================================================================

            //=====Total Amount balance================
            long balance = income - expense;
            totalamount.setText(balance+""+"/=");
            //============================================


        }

        MyAdapter adapter = new MyAdapter(expenses);
        recyclerView.setAdapter(adapter);

    }



    //====================Exit on App method start==========================

    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder;
        alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setTitle(R.string.title_text);
        alertDialogBuilder.setMessage(R.string.message_text);
        alertDialogBuilder.setCancelable(false);


        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.cancel();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }




}