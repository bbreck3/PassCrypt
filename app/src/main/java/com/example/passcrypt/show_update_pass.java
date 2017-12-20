package com.example.passcrypt;

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

public class show_update_pass extends AppCompatActivity {

    String companyStr;
    String passwordStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_update_pass);
        Button btnUpdate = (Button) findViewById(R.id.btnUpdatePass);
        final TextView company = (TextView) findViewById(R.id.tv_company_data);
        final EditText password = (EditText) findViewById(R.id.et_password_data);
        final DBHelper db = new DBHelper(this);
        DBPinHelper dbPin = new DBPinHelper(this);
        final String pin = dbPin.getPin();

        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            companyStr = bundle.getString("company");
            //passwordStr = bundle.getString("password");
        }
        company.setText(companyStr);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Made it to Update password",Toast.LENGTH_LONG).show();
                String passwordStr = password.getText().toString();
                Log.d("Comapny + Password",companyStr + " , "+ passwordStr);
                String userPass = AESHelper.encryption(pin,passwordStr);
                Log.d("Debug Str btn clicked: ", userPass);
                db.updatePassword(companyStr,userPass);
                Toast.makeText(getApplicationContext(), "Password Updated Successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),PassList.class);
                startActivity(intent);
            }

        });
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, LockScreen.class));

    }
   /* public String encryption(String strNormalText) {
        String seedValue = "YourSecKey";
        String normalTextEnc = "";
        try {
            normalTextEnc = AESHelper.encrypt(seedValue, strNormalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }

    public String decryption(String strEncryptedText) {
        String seedValue = "YourSecKey";
        String strDecryptedText = "";
        try {
            strDecryptedText = AESHelper.decrypt(seedValue, strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }*/
}
