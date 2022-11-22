package com.example.emoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
/*
    Danny Jay M. Flores (BSIT-3D)
    E-Money Activity - Main
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //calling attributes
        final TextView Balance=(TextView)findViewById(R.id.balance);
        final TextView Contact=(TextView)findViewById(R.id.contact);
        final TextView Name=(TextView)findViewById(R.id.accname);
        final Button btnCashin=(Button)findViewById(R.id.btnCashin);
        final Button btnTransfer=(Button)findViewById(R.id.btnTransfer);
        final Button btnBills=(Button)findViewById(R.id.btnBills);
        Button btnHistory=(Button)findViewById(R.id.btnHistory);
        Button btnLogout=(Button)findViewById(R.id.btnLogout);

        //getting updated info for balance with shared preference
        SharedPreferences shared = getSharedPreferences("UserInfo", MODE_PRIVATE);
        final String balance = shared.getString("Balance", " ");
        Balance.setText(balance);

        //getting info from user with shared preference
        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        final String contact = sharedPreferences.getString("Contact", " ");
        Contact.setText(contact);
        final String name = sharedPreferences.getString("Name", " ");
        Name.setText(name);
        final String bill = sharedPreferences.getString("Bill", " ");

        //if user clicks btnCashin button
        btnCashin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new intent and passing user info to the next activity
                Intent cashin=new Intent(MainActivity.this,CashinActivity.class);
                SharedPreferences shInfo = getSharedPreferences("PassingUserInfo", MODE_PRIVATE);
                SharedPreferences.Editor editInfo = shInfo.edit();
                editInfo.putString("Balance",balance);
                editInfo.apply();
                startActivity(cashin);
            }
        });

        //if user clicks btnTransfer button
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new intent and passing user info to the next activity
                Intent transfer=new Intent(MainActivity.this,CashtransferActivity.class);
                SharedPreferences shInfo = getSharedPreferences("PassingUserInfo", MODE_PRIVATE);
                SharedPreferences.Editor editInfo = shInfo.edit();
                editInfo.putString("Balance",balance);
                editInfo.apply();
                startActivity(transfer);
            }
        });

        //if user clicks btnBills button
        btnBills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new intent and passing user info to the next activity
                Intent bills=new Intent(MainActivity.this,PaybillsActivity.class);
                SharedPreferences shInfo = getSharedPreferences("PassingUserInfo", MODE_PRIVATE);
                SharedPreferences.Editor editInfo = shInfo.edit();
                editInfo.putString("Balance",balance);
                editInfo.putString("Contact",contact);
                editInfo.putString("Name",name);
                editInfo.putString("Bill",bill);
                editInfo.apply();
                startActivity(bills);
            }
        });

        //if user clicks btnHistory button
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent history=new Intent(MainActivity.this,HistoryActivity.class);
                startActivity(history);
            }
        });

        //if user clicks btnLogout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(logout);
            }
        });
    }
}
