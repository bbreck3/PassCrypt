package com.passcrypt.passcrypt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowPassData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pass_data);
//        Button btnUpdate = (Button) findViewById(R.id.btnUpdatePass);
        String userPassword;
        String userCompany;
        String userKey;
        String userID;
        AESHelper AESHelper = new AESHelper();
        DBPinHelper dbPinHelper = new DBPinHelper(this);
        String pin = dbPinHelper.getPin();
//        final TextView company = (TextView) findViewById(R.id.tv_company_data);
        final TextView password = (TextView) findViewById(R.id.tv_password);
        final TextView user_id = (TextView)findViewById(R.id.tv_user_id);
        final TextView reallyLongUserID = (TextView)findViewById(R.id.reallyLongUserID);
        Bundle bundle = getIntent().getExtras();
        try {
            if (bundle != null) {
                //GET UDER DATA
                userPassword = bundle.getString("password");
                //userCompany = bundle.getString("company");
                userID = bundle.getString("user_id");
                String userPass = AESHelper.decryption(pin,userPassword);

                //SET USER DATA
                password.setText(userPass);
                Log.d(" ***DEBUG User ID Len: ",Integer.toString(userID.length()));
                if(userID.length()<=23) {
                    user_id.setText(userID);
                    Log.d("****DEBUG******","User id is 23 or less");
                    //reallyLongUserID.setText(userID);
                } else{
                    reallyLongUserID.setText(userID);
                }

                //Log.d("SHOWPASSDATA: USER_ID: ",userID);
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
        startActivity(new Intent(this, FingerprintActivity.class));

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
