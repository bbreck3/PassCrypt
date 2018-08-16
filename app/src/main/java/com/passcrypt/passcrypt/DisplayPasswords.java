package com.passcrypt.passcrypt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class DisplayPasswords extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_passwords);
        DBPinHelper dbPin = new DBPinHelper(this);
        DBHelper db = new DBHelper(this);
        String name = db.getDatabaseName();
        String path = db.getDatabasePath();
        String databaseInfo = "Name: " + name + "\n Path: " + path;
        Toast.makeText(getApplicationContext(),databaseInfo,Toast.LENGTH_LONG).show();
        String pin = dbPin.getPin();
        ArrayList<String> passwords = db.getAllPasswords();
        ArrayList<String> password_data= new ArrayList<String>();
        Collections.sort(passwords);
        String pinStr = "Pin: " + pin;
        password_data.add(pinStr);
        for(int i=0;i<passwords.size();i++){
            Password temp_pass = db.getPassword(passwords.get(i));
            String temp_pass_str = "Company: "+temp_pass.getCompany()+"\nPassword: " + AESHelper.decryption(pin,temp_pass.getPassword());
            password_data.add(temp_pass_str);
        }
        //passwords.add(0,pin);
        final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,password_data);
        //final AbsListView adapter = AbsListView.MultiChoiceModeListener();//new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,pass_list);

        final ListView listView = (ListView)findViewById(R.id.listview);
        //final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    }
}
