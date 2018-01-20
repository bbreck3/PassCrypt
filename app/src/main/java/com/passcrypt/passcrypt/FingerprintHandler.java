package com.passcrypt.passcrypt;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

/**
 * Created by Rob on 1/13/2018.
 */

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback{
    private Context context;
    private DBPinHelper dbPin;

    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;
        dbPin = new DBPinHelper(context);
    }

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString);
    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString);
    }

    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.");
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        ((Activity) context).finish();

        Intent intent = new Intent(context,AuthSuccess.class);
        context.startActivity(intent);

        /*if(exists("pins_db.db",dbPin)) {
            //Intent intent = new Intent(context, PassList.class);
            //context.startActivity(intent);
            String pin = dbPin.getPin();
            *//*if(!pin.equals("")){
                Intent intent = new Intent(context, PassList.class);
                context.startActivity(intent);
            }*//*
        } else {
            Intent intent = new Intent(context, LockScreen.class);
            context.startActivity(intent);
        }
*/
    }

    private void update(String e){
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);
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
