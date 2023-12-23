package com.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText id,t1Amount,t2Payment,t3Desc,t4InEx;

    CardView cardViewInsert;

    RadioGroup radioGroup;
    RadioButton radioButton1,radioButton2;
    ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        id = findViewById(R.id.Id);
        t1Amount = findViewById(R.id.t1);
        t2Payment = findViewById(R.id.t2);
        t3Desc = findViewById(R.id.t3);
        //t4InEx = findViewById(R.id.t4);
        radioGroup = findViewById(R.id.radioGrpId);
        radioButton1 = findViewById(R.id.incomeradio);
        radioButton2 = findViewById(R.id.expenseradio);

        cardViewInsert = findViewById(R.id.add_Btn);




        //=====================Data Insert========================
        cardViewInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "expenseroom_dbbb").allowMainThreadQueries().build();

                ExpenseDao expenseDao = db.expenseDao();

                //RadioButton income_expenseButton;
                //int selectedId  =   radioGroup.getCheckedRadioButtonId();
                //income_expenseButton = findViewById(selectedId);

                //String value = income_expenseButton.getText().toString();
                //income_expenseButton.setText(value);
                Boolean check = expenseDao.is_exist(Integer.parseInt(id.getText().toString()));//used for  record id exist or not
                //Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                //intent.putExtra("tag",check);
                //startActivity(intent);

                //String s1 = getIntent().getStringExtra("tag");
                //id.setText(s1);

                boolean isIncome = radioButton1.isChecked();//used for check radioButton

                //new Expense((Integer.parseInt(id.getText().toString()));

                if(check==false)
                {
                    expenseDao.insertrecord(new Expense((Integer.parseInt(id.getText().toString())), ((Long.parseLong(t1Amount.getText().toString()))), t2Payment.getText().toString(), t3Desc.getText().toString(), isIncome));
                    t1Amount.setText("");
                    t2Payment.setText("");
                    t3Desc.setText("");
                    t4InEx.setText("");
                    Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_LONG).show();
                }
                else {

                    t1Amount.setText("");
                    t2Payment.setText("");
                    t3Desc.setText("");
                    t4InEx.setText("");

                    Toast.makeText(getApplicationContext(),"record no is existing",Toast.LENGTH_LONG).show();

                }



                /*
                String strInput = t1Amount.getText().toString();
                long l = Long.parseLong(strInput);
                String type = t2Payment.getText().toString();
                String description = t3Desc.getText().toString();
                //String value = income_expenseButton.getText().toString();
                //income_expenseButton.setText(value);


                Expense expense = new Expense(l,type,description);
                expenseDao.insertrecord(expense);
                t1Amount.setText("");
                t2Payment.setText("");
                t3Desc.setText("");
                Toast.makeText(getApplicationContext(),"Inserted",Toast.LENGTH_SHORT).show();

                 */


            }
        });

        //=============================================================================
    }


}