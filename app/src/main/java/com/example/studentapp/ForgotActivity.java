package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import  android.widget.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ForgotActivity extends AppCompatActivity {
    EditText e1,e2;
    Button btn2;
    DbHelper obj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        startconf();
        obj = new DbHelper(this);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String uemail1 = e1.getText().toString();
                Cursor c = obj.dupliregistration(uemail1);
                int result = c.getCount();
                try {
                    if (result > 0) {
                        String uemail11 = e1.getText().toString();
                        String upassword1 = e2.getText().toString();
                        if (obj.updatepwd(uemail1, upassword1)) {
                        /*Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);*/
                            showmsg("Password Changed");
                        } else {
                            showmsg("New Password Not Changed");
                        }
                    } else {
                        showmsg("Not Valid Email");
                    }
                }
                catch(Exception ex)
                {
                    showmsg(ex.toString());
                }
            }
        });
    }
    public void startconf() {
        e1 = (EditText)findViewById(R.id.txtEmail2);
        e2 = (EditText)findViewById(R.id.txtPwd2);
        btn2 = (Button)findViewById(R.id.btnUpdate);
    }
    public void showmsg(String msg) {
        Toast.makeText(ForgotActivity.this, msg, Toast.LENGTH_LONG).show();
    }
    public void clrtext() {
        e1.setText("");
        e2.setText("");
    }
    //Validation
    private boolean isNullRec(String n)
    {
        if(n!="" && n.length() > 0)
        {
            return true;
        }
        return false;
    }
    private boolean isValidpassword(String p)
    {
        if(p!=null&&p.length()>6)
        {
            return true;
        }
        return false;
    }
    private boolean isValidEmail(String em)
    {
        String epattern="^[A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*@\"\n" +
                "                + \"[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";
        Pattern pattern=Pattern.compile(epattern);
        Matcher matcher=pattern.matcher(em);
        return  matcher.matches();
    }
    public void gologinclick(View view) {
        Intent i = new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(i);
    }
}
