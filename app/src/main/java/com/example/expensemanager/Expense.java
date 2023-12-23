package com.example.expensemanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expense {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "amount")
    public long amount;

    @ColumnInfo(name = "paymentType")
    public String paymentType;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "incomeexpense")
    public boolean incomeExpense;

    //==============created Constructor====================
    public Expense(int id, long amount, String paymentType, String description, boolean incomeExpense) {
        this.id = id;
        this.amount = amount;
        this.paymentType = paymentType;
        this.description = description;
        this.incomeExpense = incomeExpense;
    }
    //=============================================



    //====================created getter and setter Method=============================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIncomeExpense() {
        return incomeExpense;
    }

    public void setIncomeExpense(boolean incomeExpense) {
        this.incomeExpense = incomeExpense;
    }

    //======================================================================
}
