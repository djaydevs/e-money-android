package com.example.emoney;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
    E-Money Activity - Cash Transfer
 */
public class CashtransferActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cashtransfer);

        //calling attributes
        TextView balance = (TextView)findViewById(R.id.currentbalance);
        final EditText accNum = (EditText)findViewById(R.id.accountNum);
        final EditText accName = (EditText)findViewById(R.id.accountName);
        final EditText transAmount=(EditText)findViewById(R.id.transferamount);
        Button btnTransfer = (Button)findViewById(R.id.btnCashtransfer);

        //getting balance info from user with shared preference
        SharedPreferences shInfo = getSharedPreferences("PassingUserInfo", MODE_PRIVATE);
        String bal = shInfo.getString("Balance", " ");
        balance.setText("Current Balance: P" + bal);

        final String currentbal = bal;

        //new spinner with arrayadapter for choosing amount
        //choices from spinner stored at string.xml
        Spinner spinner=findViewById(R.id.spinAmount);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this, R.array.amount, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //if user clicks btnTransfer button
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getting info of another account
                final String nameTransfer = accName.getText().toString();
                String accNumber = accNum.getText().toString();

                //convert string to int
                int balance = Integer.parseInt(currentbal);
                final int transfer = Integer.parseInt(transAmount.getText().toString());

                //if the transfer amount is 100+ proceed to calculation and new intent else transaction will terminate
                if (balance > transfer) {
                    if(transfer > 99){
                        //calculation
                        int diff = balance - transfer;
                        String difference = Integer.toString(diff);
                        final String transferAmount = Integer.toString(transfer);
                        final String newAmount = difference;

                        //new dialog box for successful transfer
                        AlertDialog.Builder alert=new AlertDialog.Builder(CashtransferActivity.this);
                        alert.setTitle("CASH TRANSFER");
                        alert.setMessage("P" + transfer + " was successfully sent to...\n" + "\nAccount Name  : " + nameTransfer +
                                "\nAccount No.       : " + accNumber + "\nReference No.    : 0123456789");
                        //if user clicks OK
                        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //new intent, toast, and passing the updated balance back to the main activity
                                Intent backmain = new Intent(CashtransferActivity.this,MainActivity.class);
                                SharedPreferences shInfo = getSharedPreferences("UserInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editInfo = shInfo.edit();
                                editInfo.putString("Balance", newAmount);
                                editInfo.putString("Transfer", transferAmount);
                                editInfo.apply();
                                startActivity(backmain);

                                Toast.makeText(CashtransferActivity.this, "Successful Cash Transfer", Toast.LENGTH_LONG).show();
                            }
                        });
                        alert.show();
                    }else {
                        Toast.makeText(CashtransferActivity.this, "Transaction Terminated\nEnter atleast 100", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CashtransferActivity.this, "Transaction Terminated\nInsufficient Balance", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //update edit text in real time when user choose amount on spinner
        EditText transAmount=(EditText)findViewById(R.id.transferamount);

        String text=parent.getItemAtPosition(position).toString();
        transAmount.setText(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
