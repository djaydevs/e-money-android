package com.example.emoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
/*
    Danny Jay M. Flores (BSIT-3D)
    E-Money Activity - Pay Bills
 */
public class PaybillsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paybills);

        //calling attributes
        TextView balance = (TextView)findViewById(R.id.tvCurrent);
        final EditText accNum = (EditText)findViewById(R.id.accNum);
        final EditText accName = (EditText)findViewById(R.id.accName);
        Button btnNext = (Button)findViewById(R.id.btnNext);
        final Button btnPay = (Button)findViewById(R.id.btnPay);
        final LinearLayout layout = (LinearLayout)findViewById(R.id.layoutBill);

        //Bill temporary invisible
        layout.setVisibility(View.INVISIBLE);

        //getting info from user with shared preference
        final SharedPreferences shInfo = getSharedPreferences("PassingUserInfo", MODE_PRIVATE);
        String bal = shInfo.getString("Balance", " ");
        final String contact = shInfo.getString("Contact", " ");
        final String name = shInfo.getString("Name", " ");
        final String bill = shInfo.getString("Bill", " ");
        balance.setText("Current Balance: P" + bal);

        final String currentbal = bal;

        //if user clicks btnProceed button Bill details will be visible
        //Bill will not display if account number and name is invalid
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView mBill = (TextView)findViewById(R.id.bill);
                TextView partnerMerchant = (TextView)findViewById(R.id.partnermerchant);
                TextView paid = (TextView)findViewById(R.id.paid);
                TextView payment = (TextView)findViewById(R.id.payment);
                TextView duedate = (TextView)findViewById(R.id.duedate);

                if (accNum.getText().toString().equals(contact) && accName.getText().toString().equals(name)) {
                    layout.setVisibility(View.VISIBLE);
                    mBill.setText("Electric Bill");
                    partnerMerchant.setText("Meralco");
                    paid.setText("Last Payment: P1285 (PAID)\nDue Date: October 1, 2022");
                    payment.setText("Pending Payment: P" + bill);
                    duedate.setText("Due Date: Decemeber 1, 2022");

                    //proceed to payment if user clicks btnPay
                    btnPay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //convert string to int
                            int balance = Integer.parseInt(currentbal);
                            int payment = Integer.parseInt(bill);

                            //calculation
                            int diff = balance - payment;
                            String difference = Integer.toString(diff);
                            String billsAmount = Integer.toString(payment);
                            String newAmount = difference;

                            //new intent, toast, and passing the updated balance back to the main activity
                            Intent backmain = new Intent(PaybillsActivity.this,MainActivity.class);
                            SharedPreferences shInfo = getSharedPreferences("UserInfo", MODE_PRIVATE);
                            SharedPreferences.Editor editInfo = shInfo.edit();
                            editInfo.putString("Balance", newAmount);
                            editInfo.putString("BillsAmount", billsAmount);
                            editInfo.apply();
                            startActivity(backmain);

                            Toast.makeText(PaybillsActivity.this, "Payment Successful", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(PaybillsActivity.this, "Transaction Terminated\nInvalid Account", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
