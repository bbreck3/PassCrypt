package com.example.passcrypt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowPassData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pass_data);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdatePass);
        String userPassword;
        String userCompany;
//        final TextView company = (TextView) findViewById(R.id.tv_company_data);
        final TextView password = (TextView) findViewById(R.id.tv_password_data);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            userPassword = bundle.getString("password");
            userCompany = bundle.getString("company");
//            company.setText(userCompany);
            password.setText(userPassword);
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Reached update Password",Toast.LENGTH_LONG).show();
            }
        });
    }
}
