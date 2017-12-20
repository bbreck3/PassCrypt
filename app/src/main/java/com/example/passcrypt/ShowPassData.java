package com.example.passcrypt;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
//        Button btnUpdate = (Button) findViewById(R.id.btnUpdatePass);
        String userPassword;
        String userCompany;
        String userKey;
        AESHelper AESHelper = new AESHelper();
        DBPinHelper dbPinHelper = new DBPinHelper(this);
        String pin = dbPinHelper.getPin();
//        final TextView company = (TextView) findViewById(R.id.tv_company_data);
        final TextView password = (TextView) findViewById(R.id.tv_password_data);
        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {
                userPassword = bundle.getString("password");
                //userCompany = bundle.getString("company");
                String userPass = AESHelper.decryption(pin,userPassword);
                password.setText(userPass);
            }
        } catch (Exception e) {
            Log.d("Error: ", e.toString());
        }
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),"Reached update Password",Toast.LENGTH_LONG).show();
//            }
//        });
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, LockScreen.class));

    }


    /*public String encryption(String strNormalText) {
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
