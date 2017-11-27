package com.example.passcrypt;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
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
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;

import java.util.ArrayList;

public class PassList extends AppCompatActivity {


    ArrayList pass_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_list);
        final DBHelper db = new DBHelper(this);
        final SwipeRefreshLayout swipeRefresh;

        if(exists("passwords_db.db",db)) {

             pass_list = getAllRecords(db);
            //Log.d("DEBUG","DEBUG");
        } else{
            db.createDatabase();
            pass_list = getAllRecords(db);
        }
        final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_multiple_choice,pass_list);
        //final AbsListView adapter = AbsListView.MultiChoiceModeListener();//new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,pass_list);

        final ListView listView = (ListView)findViewById(R.id.listview);
        //final ListView listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount +" Seclected");
                adapter.setNotifyOnChange(checked);
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
                Intent intent = new Intent(getApplicationContext(),ShowPassData.class);
                startActivity(intent);
                // Show Alert
//                Toast.makeText(getApplicationContext(),
//                        "Position :"+itemPosition+"  ListItem : " +itemValue, Toast.LENGTH_LONG)
//                        .show();
            }
        });
        Button btnAdd = (Button)findViewById(R.id.btnAddVal);
        Button btnDel = (Button)findViewById(R.id.btnDelVal);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(PassList.this, PassData.class);
                startActivity(intent);
            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteAllPasswords();
                pass_list.clear();
                adapter.notifyDataSetChanged();
            }
        });

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

    public ArrayList<String> getAllRecords(DBHelper dbHelper) {
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
    }
    public boolean exists(String table, DBHelper db){
        String dbName = db.getDatabaseName();

        if(dbName.equals(table)){
            Log.d("DEBUG",dbName);
            Log.d("DEBUG","DEBUG");

            return true;
        } else{
            return false;
        }
        /*try{
            db.("Select * FROM " +table,null);
            return true;
        }catch (SQLException e){
            return false;
        }*/
    }
    public ArrayList<String> updateList(DBHelper db,ArrayList<String> ps_list){
        ArrayList<String> updated_pass_list= getAllRecords(db);
        ps_list.clear();
        for(String password: updated_pass_list){
            ps_list.add(password);
        }
        return ps_list;
    }
}
