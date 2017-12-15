package com.example.passcrypt;

/**
 * Created by breck on 11/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by breck on 11/19/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "passwords_db.db";
    public static final String TABLE_NAME = "passwords";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_KEY = "key";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text," + COLUMN_KEY + " text" +")"
        );*/
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text)"
        );

    } public void createDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text," + COLUMN_KEY + " text" +")"
        );*/
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertPassword( String company, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id", id);
        contentValues.put("company", company);
        contentValues.put("password", password);
        //contentValues.put("key", key);
        db.insert("passwords","null", contentValues);
        if (contentValues != null) {
            return true;
        } else return false;
    }
    public boolean updatePassword ( String company, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("company", company);
        contentValues.put("password", password);
        db.update("passwords", contentValues, "company = ? ", new String[] { company } );

        if (contentValues!= null){
            return true;
        }
        else{
            db.close();

            return false;
        }
    }
    public Password getPassword(String company){
        Password password=null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME, new String[]{
                            COLUMN_COMPANY, COLUMN_PASSWORD}, COLUMN_COMPANY + "=?",
                    new String[]{company/*String.valueOf(company)*/}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();


           password = new Password(cursor.getString(0), cursor.getString(1));


        } catch (Exception e) {
            e.printStackTrace();
        }
        // return password
        return password;
    }

   /* public Password getKey(String key){
        Password password = null;
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_NAME, new String[]{
                            COLUMN_COMPANY, COLUMN_PASSWORD}, COLUMN_KEY + "=?",
                    new String[]{key*//*String.valueOf(company)*//*}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();


            password = new Password(cursor.getString(0), cursor.getString(1));


        } catch (Exception e) {
            e.printStackTrace();
        }
        // return password
        return password;
    }*/
    public ArrayList<String> getAllPasswords(){
        ArrayList<String> passwordList = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from passwords", null);// +company +"",new String[] {company});
        if (result.moveToFirst()) {
            do {
                Password password = new Password();

                password.setCompany(result.getString(0));
                password.setPassword(result.getString(1));
                // Adding contact to list
                passwordList.add(password.getCompany());
            } while (result.moveToNext());
        }
        //db.close();
        //result.close();
        return passwordList;
    }
    public void deleteAllPasswords(){
        SQLiteDatabase db =this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

    public Integer deletePassword(String company){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("passwords", "company=? ",new String[]{company});

    }
}
