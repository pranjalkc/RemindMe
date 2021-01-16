package com.example.studentapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btnlogin;
    DbHelper obj;

    private Db appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startconf();
        obj = new DbHelper(this);

        appDatabase = Db.getDb(LoginActivity.this);

        RoomDAO roomDAO = appDatabase.getRoomDAO();
        Login temp = roomDAO.getLoggedInUser();

        if(temp!=null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(e1.getText().toString().trim(),e2.getText().toString().trim());
            }
        });
    }
    public void startconf() {
        e1 = (EditText)findViewById(R.id.txtEmail);
        e2 = (EditText)findViewById(R.id.txtPwd);
        btnlogin = (Button)findViewById(R.id.btnLogin);
    }
    public void showmsg(String msg)
    {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
    }
    public void clrtext() {
        e1.setText("");
        e2.setText("");
    }
    public void newregistration(View view) {
        Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
        startActivity(intent);
        finish();
    }

    public void fpwdclick(View view) {
        Intent i = new Intent(getApplicationContext(),ForgotActivity.class);
        startActivity(i);
    }

    public void loginUser(String user,String pass){

        RoomDAO roomDAO = appDatabase.getRoomDAO();
        Login temp = roomDAO.getUserwithUsername(user);
        //Toast.makeText(this, temp.getPassword(), Toast.LENGTH_SHORT).show();
        if(temp==null){
            Toast.makeText(LoginActivity.this,"Invalid username",Toast.LENGTH_SHORT).show();
        }
        else{
            if(temp.getPassword().equals(pass)){
                temp.setIsloggedIn(1);
                roomDAO.Update(temp);
                Db.destroyInstance();
                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this,"Invalid Password",Toast.LENGTH_SHORT).show();
            }
        }

    }

}
