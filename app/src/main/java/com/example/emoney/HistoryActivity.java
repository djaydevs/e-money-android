package com.example.emoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
/*
    Danny Jay M. Flores (BSIT-3D)
    E-Money Activity - Transaction History
 */
public class HistoryActivity extends AppCompatActivity {
    //global declarations
    ListView listView;
    ArrayList<String> historyList;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //calling listview attribute
        listView =(ListView)findViewById(R.id.listView);

        //getting user info transanctions with shared preference
        SharedPreferences shared = getSharedPreferences("UserInfo", MODE_PRIVATE);
        String balance = shared.getString("Balance", " ");
        String cashin = shared.getString("Cashin", " ");
        String transfer = shared.getString("Transfer", " ");
        String bills = shared.getString("BillsAmount", " ");

        //setting new arraylist and arrayadapter
        historyList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, historyList);

        //adding transactions to listview
        String cashinHistory = ("CASH IN: " + "(+) " + cashin + "\nNew Balance: P" + balance + "\n");
        String transferHistory = ("\nTRANSFER: " + "(—) " + transfer + "\nNew Balance: P" + balance + "\n");
        String billsHistory = ("\nBILLS: " + "(—) " + bills + "\nNew Balance: P" + balance + "\n");
        historyList.add(cashinHistory);
        historyList.add(transferHistory);
        historyList.add(billsHistory);

        //setting listview and update changes
        listView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }
}
