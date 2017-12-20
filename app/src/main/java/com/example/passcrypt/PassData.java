package com.example.passcrypt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
//import java.util.Base64;
import android.util.Base64;
//import android.util.B

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import com.example.passcrypt.AESHelper;

public class PassData extends AppCompatActivity {

    String companyStr;
    String passwordStr;
   //SecretKey secretKey;
    SecretKey secret;
    AESHelper aesHelper = new AESHelper();
    DBPinHelper dbPin = new DBPinHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_data);
        Button btnAdd = (Button) findViewById(R.id.btnAddPass);
        final EditText company = (EditText) findViewById(R.id.et_company);
        final EditText password = (EditText) findViewById(R.id.et_password);
        final DBHelper db = new DBHelper(this);
        final String pin = dbPin.getPin();
        //final AESHelper AESHelper =new AESHelper();
//        SecretKey secretKey = generateKey("password");
        try {
            //secret = AESHelper.generateKey();
        }catch (Exception e){
            Log.d("Error: ", e.toString());
        }
            btnAdd.setOnClickListener(new View.OnClickListener(){
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
                    try {
                            String keyStr="";
                            Bundle bundle = getIntent().getExtras();
                            if(bundle!=null){
                                keyStr=bundle.getString("key");
                            }
                        // decode the base64 encoded string

                        //byte[] decodedKey = Base64.decode(keyStr, Base64.DEFAULT);//keyStr.getBytes();//Base64.getDecoder().decode(encodedKey);

                        // rebuild key using SecretKeySpec
                        /*SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
                        aesHelper.setSecretKey(originalKey);*/

                       // Log.d("Plaintext: ", passwordStr);
                        String enc = AESHelper.encryption(pin,passwordStr);
                        //Log.d("Encrypted: ", enc);
                        //String dec = AESHelper.decryption(enc);
                        //Log.d("Original: ", dec);
                        //aesHelper.setSecretKey(keyStr);
                       //  byte[] passwordEnc = aesHelper.encrypt(passwordStr,aesHelper.getSecretKey());
                        //String passwordEncStr = passwordEnc.toString();
                        //Log.d("Encrypt String: " , passwordEncStr);

                        //String passwordDecStr = aesHelper.decrypt(passwordEnc,aesHelper.getSecretKey());
                        //Log.d("Decrypt String: ", passwordDecStr);

                       /* String companyStr = company.getText().toString();
                        String passwordStr = password.getText().toString();
*/

                      /*  byte[] passwordEnc = AESHelper.encrypt(passwordStr,secret);
                        String passwordEncStr = passwordEnc.toString();*/
                      //  Log.d("Password Enc String: ", passwordEncStr);
                        db.insertPassword(companyStr, enc);
                        //Toast.makeText(getApplicationContext(),"Company: " + companyStr + "\n Password: " + passwordStr,Toast.LENGTH_LONG).show();
                        Toast.makeText(getApplicationContext(), "Passwords Stored Successfully!", Toast.LENGTH_LONG).show();
                        for (long i = 0; i < 300000000; i++) {
                            int temp = 0;
                            temp += i;
                        }
                        Intent intent = new Intent(getApplicationContext(), PassList.class);
                        startActivity(intent);
                    }catch (Exception e){
                        Log.d("Error: ",e.toString() );
                    }
                }
            }
        });

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, LockScreen.class));

    }
    /*public String encryption(String strNormalText){
        String seedValue = "YourSecKey";
        String normalTextEnc="";
        try {
            normalTextEnc = AESHelper.encrypt(seedValue, strNormalText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return normalTextEnc;
    }
    public String decryption(String strEncryptedText){
        String seedValue = "YourSecKey";
        String strDecryptedText="";
        try {
            strDecryptedText = AESHelper.decrypt(seedValue, strEncryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDecryptedText;
    }*/

  /* public SecretKey generateKey(String password) throws NoSuchAlgorithmException,InvalidKeySpecException{
        SecretKey secret = new SecretKeySpec(password.getBytes(),"AES");
        return secret;
    }*/

    /*public byte[] encrypt(String msg, SecretKey secret) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Cipher cipher = null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,secret);
        byte[] cipherText = cipher.doFinal(msg.getBytes("UTF-8"));
        return cipherText;
    }
    public String decrypt(byte[] cipherText, SecretKey secretKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException{
        Cipher cipher =null;
        cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,secretKey);
        String decryptString = new String(cipher.doFinal(cipherText),"UTF-8");
        return decryptString;
    }*/
}