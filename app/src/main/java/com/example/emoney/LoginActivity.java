package com.example.emoney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
/*
    Danny Jay M. Flores (BSIT-3D)
    E-Money Activity - Login
 */
public class LoginActivity extends AppCompatActivity {

    //new arraylist for users
    public ArrayList<User> mUsers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setting users info
        mUsers=new ArrayList<>();
        mUsers.add(new User("dannyflores", "flores123", "Danny Flores", "09951866751", "8000", "3590"));
        mUsers.add(new User("taylorswift", "swift123", "Taylor Swift", "09131234567", "13000", "8135"));
        mUsers.add(new User("selenagomez", "gomez123", "Selena Gomez", "09991234567", "5500", "2140"));

        //calling attributes
        final AutoCompleteTextView uName=(AutoCompleteTextView)findViewById(R.id.username);
        final EditText uPass=(EditText)findViewById(R.id.password);
        Button btnLogin=(Button)findViewById(R.id.btnLogin);

        //setting username suggestions
        String userChoice[] = {"dannyflores", "taylorswift", "selenagomez"};

        //autocompletetextview with arrayadapter for username suggestions
        ArrayAdapter<String> userAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,userChoice);
        uName.setThreshold(1);
        uName.setAdapter(userAdapter);

        //if user clicks the btnLogin button
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating private shared preference for users info
                SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
                SharedPreferences.Editor editInfo = sharedPreferences.edit();

                //app will go to next activity if the inputted username and password is equal to the stored arraylist
                if (uName.getText().toString().equals(mUsers.get(0).getmUsername())
                        && uPass.getText().toString().equals(mUsers.get(0).getmPassword())) {
                    //displaying successful login
                    Toast.makeText(LoginActivity.this, "Login Successfully",Toast.LENGTH_SHORT).show();

                    //new intent and storing users info to shared preference
                    Intent main=new Intent(LoginActivity.this,MainActivity.class);
                    editInfo.putString("Name",mUsers.get(0).getmName());
                    editInfo.putString("Contact",mUsers.get(0).getmContact());
                    editInfo.putString("Balance",mUsers.get(0).getmBalance());
                    editInfo.putString("Bill",mUsers.get(0).getmBill());
                    editInfo.apply();
                    startActivity(main);
                }
                else if (uName.getText().toString().equals(mUsers.get(1).getmUsername())
                        && uPass.getText().toString().equals(mUsers.get(1).getmPassword())) {
                    //displaying successful login
                    Toast.makeText(LoginActivity.this, "Login Successfully",Toast.LENGTH_SHORT).show();

                    //new intent and storing users info to shared preference
                    Intent main=new Intent(LoginActivity.this,MainActivity.class);
                    editInfo.putString("Name",mUsers.get(1).getmName());
                    editInfo.putString("Contact",mUsers.get(1).getmContact());
                    editInfo.putString("Balance",mUsers.get(1).getmBalance());
                    editInfo.putString("Bill",mUsers.get(1).getmBill());
                    editInfo.apply();
                    startActivity(main);
                }
                else if (uName.getText().toString().equals(mUsers.get(2).getmUsername())
                        && uPass.getText().toString().equals(mUsers.get(2).getmPassword())) {
                    //displaying successful login
                    Toast.makeText(LoginActivity.this, "Login Successfully",Toast.LENGTH_SHORT).show();

                    //new intent and storing users info to shared preference
                    Intent main=new Intent(LoginActivity.this,MainActivity.class);
                    editInfo.putString("Name",mUsers.get(2).getmName());
                    editInfo.putString("Contact",mUsers.get(2).getmContact());
                    editInfo.putString("Balance",mUsers.get(2).getmBalance());
                    editInfo.putString("Bill",mUsers.get(2).getmBill());
                    editInfo.apply();
                    startActivity(main);
                }
                else {
                    //setting editText to empty if the username and password is incorrect
                    uName.setText("");
                    uPass.setText("");
                    Toast.makeText(LoginActivity.this, "Incorrect username or password!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
