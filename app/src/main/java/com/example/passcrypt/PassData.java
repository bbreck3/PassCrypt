package com.example.passcrypt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

public class PassData extends AppCompatActivity {

    String companyStr;
    String passwordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data);
        Button btnAdd = (Button) findViewById(R.id.btnAddPass);
        final EditText company = (EditText) findViewById(R.id.et_company);
        final EditText password = (EditText) findViewById(R.id.et_password);
        final DBHelper db = new DBHelper(this);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                companyStr = company.getText().toString();
                passwordStr = password.getText().toString();
                //Toast.makeText(getApplicationContext(),"Company: " + companyStr + "\n Password: " + passwordStr,Toast.LENGTH_LONG).show();

                /*if(companyStr.equals("") && password.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Fields cannot be blank",Toast.LENGTH_LONG).show();
                } */
                if (companyStr.equals("")) {
                    Toast.makeText(getApplicationContext(), "Company Field cannot be blank", Toast.LENGTH_LONG).show();
                } else if (passwordStr.equals("")) {
                    Toast.makeText(getApplicationContext(), "Password Field cannot be blank", Toast.LENGTH_LONG).show();
                } else if (!companyStr.equals("") && !passwordStr.equals("")) {
                    String companyStr = company.getText().toString();
                    String passwordStr = password.getText().toString();
                    db.insertPassword(companyStr, passwordStr);
                    //Toast.makeText(getApplicationContext(),"Company: " + companyStr + "\n Password: " + passwordStr,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(), "Passwords Stored Successfully!", Toast.LENGTH_LONG);
                    for(long i =0;i<300000000;i++){
                        int temp=0;
                        temp+=i;
                    }
                    Intent intent = new Intent(getApplicationContext(), PassList.class);
                    startActivity(intent);
                }
            }
        });
    }
}