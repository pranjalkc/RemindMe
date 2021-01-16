package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class RegistrationActivity extends AppCompatActivity {
    private EditText user,pass;
    private Button register;
    private TextView login;
    private Db appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        user = findViewById(R.id.editText);
        pass = findViewById(R.id.editText2);
        register = findViewById(R.id.button);
        login = findViewById(R.id.login);

        appDatabase = Db.getDb(RegistrationActivity.this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser(user.getText().toString().trim(),pass.getText().toString().trim());
            }
        });

    }


    public void registerUser(String user,String pass){

        RoomDAO roomDAO = appDatabase.getRoomDAO();
        Login temp = new Login();
        temp.setUsename(user);
        temp.setPassword(pass);
        temp.setIsloggedIn(0);

        Login temp2 = roomDAO.getUserwithUsername(user);
        if(temp2==null) {
            roomDAO.Insert(temp);
            Toast.makeText(RegistrationActivity.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
            Db.destroyInstance();
            Intent intent = new Intent(RegistrationActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
            Toast.makeText(RegistrationActivity.this,"User Already Registered",Toast.LENGTH_SHORT).show();

    }
}
