package com.passcrypt.passcrypt;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class SaveToFile extends AppCompatActivity {

    ArrayList<String> pass_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_to_file);
        final DBHelper db = new DBHelper(this);
        final DBPinHelper dbPin = new DBPinHelper(this);
        String pin = dbPin.getPin();
        if (exists("passwords_db.db", db)) {
            //db.upgrade();
            //main list containing all the passwords
            pass_list = db.getAllPasswords();//getAllRecords(db);
            FileWriter backup = null;
            try {
                File sd = Environment.getExternalStorageDirectory();
                File data = Environment.getDataDirectory();
                String packageName = "com.passcrypt.passcrypt";
                if (data.canWrite()) {
                    String currentDBPath = "//data//" + packageName + "//passwords.txt//";
                    Toast.makeText(getBaseContext(), "Database backed up successfully!", Toast.LENGTH_LONG).show();
                    backup = new FileWriter(currentDBPath);
                }
                backup.write(pin + System.getProperty("line.separator"));
            } catch (Exception e) {
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
            for (int i = 0; i < pass_list.size(); i++) {
                try {
                    Password password = db.getPassword(pass_list.get(i));
                    //String userPass = AESHelper.decryption(pin,userPassword);
                    String pass = "Company: " + password.getCompany() + ", Password: " + AESHelper.decryption(pin,password.getPassword()) + System.getProperty("line.separator");
                    //backup.write(Integer.toString(i) + System.getProperty("line.sepator"));
                    backup.write(pass);
                } catch (Exception e) {
                    String err = e.getMessage();
                    Log.d("ERROR:", err);
                }
            }
            try {
                backup.close();
            } catch (Exception e) {
                String err = e.getMessage();
                Log.d("ERROR:", err);
            }
        }
    }//end oncreate

    public boolean exists(String table, DBHelper db) {
        String dbName = db.getDatabaseName();

        if (dbName.equals(table)) {
            return true;
        } else {
            return false;
        }
    }//end exists
}//enc class
