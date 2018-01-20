package com.passcrypt.passcrypt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AuthSuccess extends AppCompatActivity {
    DBPinHelper dbPin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_success);
        dbPin = new DBPinHelper(this);
        if (exists("pins_db.db", dbPin)) {
            Log.d("DEBUG-->", "pins_db exists");
            //Intent intent = new Intent(context, PassList.class);
            //context.startActivity(intent);
            String pin = dbPin.getPin();
            Log.d("DEBUG-->Pin:  ", pin);
            if (pin.equals("")) {
                Log.d("DEBUG-->", "pins needs to be set ");
                Intent intent = new Intent(getApplicationContext(), LockScreen.class);
                startActivity(intent);
            } else {
                Log.d("DEBUG-->", "pin not set");
                Intent intent = new Intent(getApplicationContext(), PassList.class);
                startActivity(intent);
            }
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Intent intent = new Intent(getApplicationContext(),FingerprintActivity.class);
        startActivity(intent);
    }

    public boolean exists(String table, DBPinHelper db){
        String dbName = db.getDatabaseName();

        if(dbName.equals(table)){
           /* Log.d("DEBUG",dbName);
            Log.d("DEBUG","DEBUG");*/

            return true;
        } else{
            return false;
        }
    }
}
