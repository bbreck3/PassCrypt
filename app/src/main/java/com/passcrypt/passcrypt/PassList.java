package com.passcrypt.passcrypt;

import android.content.Context;
import android.content.Intent;
import android.icu.text.UnicodeSetSpanner;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Calendar;
import java.util.Date;


public class PassList extends AppCompatActivity {


    ArrayList pass_list;
    ArrayList<Password> deleteList = new ArrayList<Password>();
    ArrayList<Password> updList = new ArrayList<Password>();
    AESHelper aesHelper = new AESHelper();
    static Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_list);
        final DBHelper db = new DBHelper(this);
        final DBDateHelper dbDate = new DBDateHelper(this);
        final SwipeRefreshLayout swipeRefresh;
        final DBPinHelper dbPin = new DBPinHelper(this);
        String pin = null;
        context=this;
        if(exists("pins_db.db",dbPin)){
            pin = dbPin.getPin();

        }else{
            dbPin.createDatabase();
            Intent intent = new Intent(getApplicationContext(),SetPin.class);

            startActivity(intent);
        }

        if(exists("passwords_db.db",db)) {
            db.upgrade();
            //main list containing all the passwords
            //////////////////////////////////////////////
            /// DO NOT DELETE THIS!!!!!!
            //////////////////////////////////////////////

            pass_list = db.getAllPasswords();//getAllRecords(db);

        } else{
            db.createDatabase();
            //////////////////////////////
            ///
            /// NEED TO FILL THE DATABASE FROM THE BACK UP
            //////////////////////////////
            pass_list = db.getAllPasswords();

        }




        final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,pass_list);
        //final AbsListView adapter = AbsListView.MultiChoiceModeListener();//new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,pass_list);

        final ListView listView = (ListView)findViewById(R.id.listview);
        //final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        Collections.sort(pass_list);
        adapter.notifyDataSetChanged();

        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " Seclected");
                //adapter.setNotifyOnChange(checked);
                String itemValue = (String) listView.getItemAtPosition(position);

                Password password = db.getPassword(itemValue);
                 deleteList.add(password);
                 updList.add(password);
                if(checkedCount==1){
                    SparseBooleanArray checkedItemPositions =  listView.getCheckedItemPositions();
                    for(int i=0;i<checkedItemPositions.size();i++) {
                        if (checkedItemPositions.valueAt(i)) {
                            String item = listView.getAdapter().getItem(checkedItemPositions.keyAt(i)).toString();
                            Password temp = db.getPassword(item);

                            updList.clear();
                            updList.add(temp);
                            adapter.setNotifyOnChange(checked);
                        }
                    }
                }else {
                    adapter.setNotifyOnChange(checked);

                    }
                }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB

                switch (item.getItemId()) {
                    case R.id.action_settings:
                        //   deleteSelectedItems();
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu_pass_list, menu);
                return true;
            }


            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
                deleteList.clear();
                updList.clear();
            }


            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                Password password = db.getPassword(itemValue);
                String key = password.getKey();
                String passwordStr = password.getPassword();
                if (password.getUserID() == null) {
                    Intent intent = new Intent(getApplicationContext(),AddUserID.class);
                    intent.putExtra("company", itemValue);
                    startActivity(intent);
                } else {
                    String userID = password.getUserID();
                    Intent intent = new Intent(getApplicationContext(), ShowPassData.class);
                    intent.putExtra("password", passwordStr);
                    intent.putExtra("company", itemValue);
                    //intent.putExtra("key", key);
                    intent.putExtra("user_id", userID);
                    startActivity(intent);
                }
            }
        });
        ////////////////////////////////////////////////////
        ////
        //// Button Sections
        ////////////////////////////////////////////////////
        Button btnAdd = (Button)findViewById(R.id.btnAddVal);
        Button btnDel = (Button)findViewById(R.id.btnDelVal);
        Button btnUpd = (Button)findViewById(R.id.btnUpdatePass);
        //Button btnDspl = (Button)findViewById(R.id.btnDisplayAll);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                try {
                    Intent intent = new Intent(PassList.this, PassData.class);
                    startActivity(intent);
                }catch (Exception e){
                    Log.d("Error: ",e.toString());
                }
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //db.deleteAllPasswords();
                for(Password password: deleteList){
                    db.deletePassword(password.getCompany());
                    int index = pass_list.indexOf(password.getCompany());
                    pass_list.remove(index);
                }
                if(deleteList.size()==0){
                    Toast.makeText(getApplicationContext(),"Select a company to delete!", Toast.LENGTH_LONG).show();
                }
                else if(deleteList.size()==1){
                    Toast.makeText(getApplicationContext(),"Password deleted successfully!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Passwords deleted successfully!", Toast.LENGTH_LONG).show();
                }
                deleteList.clear();
                adapter.notifyDataSetChanged();
                listView.clearChoices();

            }
        });

        btnUpd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(updList.size()==1) {
                    listView.clearChoices();
                    Password tempPass = updList.get(0);
                    String passwordStr = tempPass.getPassword();
                    String companyStr = tempPass.getCompany();
                    //String userIDStr = tempPass.getUserID();
                    Intent intent = new Intent(getApplicationContext(), show_update_pass.class);
                    intent.putExtra("company", companyStr);
                    intent.putExtra("password", passwordStr);
                   // intent.putExtra("user_id",userIDStr);
                    startActivity(intent);
                } else if(updList.size()==0) {
                    Toast.makeText(getApplicationContext(),"Select a password to update!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Select ONLY 1 to update!",Toast.LENGTH_LONG).show();
                }
            }
        });

        /*btnDspl.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               Intent intent = new Intent(getApplicationContext(),DisplayPasswords.class);
               startActivity(intent);
           }
        });*/
        /**
         *  Spinner really isnt needed...leaving for aesthetics only...?
         *
         * */
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pass_list = updateList(db,pass_list);
                        swipeRefresh.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        startActivity(new Intent(this, FingerprintActivity.class));

    }

    //Works But not needed
   /* public ArrayList<String> getAllRecords(DBHelper dbHelper) {
        //DBHelper dbHelper = new DBHelper(getApplicationContext());
        Cursor cursor = dbHelper.getAllPasswords();
        ArrayList<String> passwords = new ArrayList<String>();
        Password password;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();

                password = new Password();
                password.setCompany(cursor.getString(0));
                password.setPassword(cursor.getString(1));

                passwords.add(password.getCompany());
            }
        }
        cursor.close();
        dbHelper.close();

        return passwords;
    }*/
   //Works but not needed currently
   /* public String getPassword(String company, DBHelper dbHelper){
        Password password= dbHelper.getPassword(company);
        String passwordStr = password.getPassword();
        Log.d("Password", passwordStr);
        dbHelper.close();

        return passwordStr;
    }*/
    public boolean exists(String table, DBHelper db){
        String dbName = db.getDatabaseName();

        if(dbName.equals(table)){
            return true;
        } else{
            return false;
        }
    }

    public boolean exists(String table, DBPinHelper db){
        String dbName = db.getDatabaseName();

        if(dbName.equals(table)){
            return true;
        } else{
            return false;
        }
    }

    public boolean exists(String table, DBDateHelper db){
        String dbName = db.getDatabaseName();

        if(dbName.equals(table)){
            return true;
        } else{
            return false;
        }
    }

    /**
     *  Is used for Spinner only and Spinner really isnt needed...leaving for aesthetics only...?
     *
     * */
    public ArrayList<String> updateList(DBHelper db,ArrayList<String> ps_list){
        ArrayList<String> updated_pass_list= db.getAllPasswords();
        ps_list.clear();
        for(String company: updated_pass_list){
          /*  Log.d("Company:",company);*/
            ps_list.add(company);
        }
        Collections.sort(ps_list);
        return ps_list;
    }

}
