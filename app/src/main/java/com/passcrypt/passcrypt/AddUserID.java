package com.passcrypt.passcrypt;

import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddUserID extends AppCompatActivity {

    String company;
    String user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_id);
        final DBHelper dbHelper = new DBHelper(this);
        final TextView tv_company = (TextView)findViewById(R.id.tv_company_data);
        final EditText et_user_id = (EditText)findViewById(R.id.et_user_id);
        Button btnAddUserId = (Button)findViewById(R.id.btnAddUserID);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            company = bundle.getString("company");
            //Log.d("DEBUG: COMPANY::", company);
        }
        tv_company.setText(company.toString());
        btnAddUserId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_id = et_user_id.getText().toString();
                Log.d("Debug: from ADDUserID: ",user_id);
                dbHelper.insertUserID(company,user_id);
                Toast.makeText(getApplicationContext(),"UserID addedd successfully", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),PassList.class);
                startActivity(intent);
            }
        });
    }

    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, FingerprintActivity.class));
    }
}
