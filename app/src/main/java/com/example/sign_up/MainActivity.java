package com.example.sign_up;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    com.example.sign_up.DatabaseHelper databaseHelper;

    EditText et_username, et_password, et_cpassword;
    Button btn_register, btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper= new com.example.sign_up.DatabaseHelper(this);
        et_username= (EditText) findViewById(R.id.et_username);
        et_password= (EditText) findViewById(R.id.et_password);
        et_cpassword= (EditText) findViewById(R.id.et_cpassword);
        btn_register= (Button) findViewById(R.id.btn_register);
        btn_login= (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(com.example.sign_up.MainActivity.this, login.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String confirm_password= et_cpassword.getText().toString();
                if(!(username.matches("piyush.*"))){
                    Toast.makeText(getApplicationContext(), "Username must contain 'piyush' ", Toast.LENGTH_SHORT).show();
                }
                else if ((!(password.matches(".*[0-9]{1,}.*") &&password.matches(".*[@#$]{1,}.*") &&password.length()>=8 &&password.length()<=20))) {
                    Toast.makeText(getApplicationContext(), "Your password should be greater than 8 character and less than 20 character and it must contain atleat one alphabet,one digit and one special character", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (password.equals(confirm_password)) {
                        Boolean checkusername= databaseHelper.CheckUsername(username);
                        if (checkusername== true) {
                            Boolean insert = databaseHelper.Insert(username, password);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                                et_username.setText("");
                                et_password.setText("");
                                et_cpassword.setText("");
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Username already taken", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
