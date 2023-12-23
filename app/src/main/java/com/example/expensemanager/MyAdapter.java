package com.example.expensemanager;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myviewholder> {

    List<Expense> expenses;


    //=======created constructor only for List
    public MyAdapter(List<Expense> expenses) {
        this.expenses = expenses;
    }
    //===================================


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_view,parent,false);

        return new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyAdapter.myviewholder holder, int position) {

        holder.id.setText(String.valueOf(expenses.get(position).getId()));
        holder.amount.setText(String.valueOf(expenses.get(position).getAmount()));
        holder.isType.setText(expenses.get(position).getPaymentType());
        holder.isDescription.setText(expenses.get(position).getDescription());
        //holder.isIncome.setText(expenses.get(position).getIncomeExpense());

        //==========Used for Income and Expense Status Check======================
        if(expenses.get(position).isIncomeExpense())
        {
            holder.isIncome.setText("Income");
        }
        else {

            holder.isIncome.setText("Expense");
        }
        //======================================


        //===========Delete==============================
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder;
                alertDialogBuilder = new AlertDialog.Builder(holder.linearLayout.getContext());
                alertDialogBuilder.setMessage("Do you want to Delete data?");
                alertDialogBuilder.setCancelable(false);


                alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AppDatabase db = Room.databaseBuilder(holder.id.getContext(),
                                AppDatabase.class, "expenseroom_dbbb").allowMainThreadQueries().build();

                        ExpenseDao expenseDao = db.expenseDao();

                        expenseDao.deleterecord(expenses.get(position).getId());

                        expenses.remove(position);
                        
                        notifyDataSetChanged();

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
        });
        //================================================================





    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }




    class  myviewholder extends RecyclerView.ViewHolder
    {

        TextView id,amount,isType,isDescription,isIncome;
        LinearLayout linearLayout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);


            id = itemView.findViewById(R.id.id);
            amount = itemView.findViewById(R.id.amount);
            isType = itemView.findViewById(R.id.isType);
            isDescription = itemView.findViewById(R.id.isDescription);
            isIncome = itemView.findViewById(R.id.isIncome);
            linearLayout = itemView.findViewById(R.id.linearLayoutId);


        }


    }


}