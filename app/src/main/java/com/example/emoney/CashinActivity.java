package com.example.emoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
/*
    Danny Jay M. Flores (BSIT-3D)
    E-Money Activity - Cash In
 */
public class CashinActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashin);

        //calling attributes
        TextView current=(TextView)findViewById(R.id.currentbal);
        final EditText amount=(EditText)findViewById(R.id.amount);
        Button btnCashin=(Button)findViewById(R.id.cashin);

        //getting balance info from user with shared preference
        final SharedPreferences shInfo = getSharedPreferences("PassingUserInfo", MODE_PRIVATE);
        String bal = shInfo.getString("Balance", " ");
        current.setText("Current Balance: P" + bal);

        final String currentbal = bal;

        //new spinner with arrayadapter for choosing amount
        //choices from spinner stored at string.xml
        final Spinner spinner=findViewById(R.id.spinAmount);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.amount, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //if user clicks btnCashin button
        btnCashin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //convert string to int
                int addAmount = Integer.parseInt(amount.getText().toString());
                int oldAmount = Integer.parseInt(currentbal);

                //if the cash in amount is 100+ proceed to calculation and new intent else transaction will terminate
                if (addAmount > 99) {
                    //calculation
                    int add = addAmount + oldAmount;
                    String sum = Integer.toString(add);
                    String cashinAmount = Integer.toString(addAmount);
                    String newAmount = sum;

                    //new intent, toast, and passing the updated balance back to the main activity
                    Intent backmain = new Intent(CashinActivity.this,MainActivity.class);
                    SharedPreferences shInfo = getSharedPreferences("UserInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editInfo = shInfo.edit();
                    editInfo.putString("Balance", newAmount);
                    editInfo.putString("Cashin", cashinAmount);
                    editInfo.apply();
                    startActivity(backmain);

                    Toast.makeText(CashinActivity.this, "Successful Cash In", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CashinActivity.this, "Transaction Terminated\nEnter atleast 100", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //update edit text in real time when user choose amount on spinner
        EditText amount=(EditText)findViewById(R.id.amount);

        String text=parent.getItemAtPosition(position).toString();
        amount.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
