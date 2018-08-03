package com.passcrypt.passcrypt;
/**
 * Created by breck on 11/20/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.Date;
import java.util.Calendar;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;

/**
 * Created by breck on 11/19/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "passwords_db.db";
    public static final String TABLE_NAME = "passwords";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_KEY = "key";
    public static final String COLUMN_DATE = "date";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //****************
        //  DESIGN WITH KEY(PIN)
        //****************
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text," + COLUMN_KEY + " text" +")"
        );*/
        //****************
        //  DESIGN WITH USER ID
        //****************
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text,"+COLUMN_USER_ID +" text)"
        );*/
        //****************
        //  DESIGN WITH DATE
        //****************
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text,"+COLUMN_USER_ID + "text)"

        );*/
        //****************
        //  ORIGINAL DESIGN (company, passwords)
        //****************
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text)"
        );

    } public void createDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        /*db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text," + COLUMN_KEY + " text" +")"
        );*/
       /* db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text,"+COLUMN_USER_ID +" text)"
        );*/
        //****************
        //  ORIGINAL DESIGN (company, passwords)
        //****************
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_COMPANY + " text primary key," + COLUMN_PASSWORD + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }
    public void upgrade(){
        SQLiteDatabase db = this.getWritableDatabase();
        String table = "passwords";
        String userIDCol = "user_id";
        String dateCol="date";


        //test for USER_ID
        if(!FieldExists(db,table,userIDCol)){
            Log.d("DEBUG", "Upgradeing DB: adding userID col");
            String sql = "ALTER TABLE passwords ADD COLUMN user_id text";
            db.execSQL(sql);
        }

        //test for DATE
       /* if(!FieldExists(db,table,dateCol)){
           Log.d("DEBUG", "Upgradeing DB: adding data col");
            String sql = "ALTER TABLE passwords ADD COLUMN date text";
            db.execSQL(sql);

            Date currentTime = Calendar.getInstance().getTime();

            Log.d("CurrentTime: -->", currentTime.toString());
            if(setDate(currentTime)){
                Log.d("DEBUG: ", "Date set Successfully");
            }else{
                Log.d("DEBUG: ", "Date NOT set Successfully");
            }

        }*/
    }
    public boolean FieldExists(SQLiteDatabase db, String tableName, String fieldName)
    {
        boolean isExist = false;

        Cursor res = null;

        try {

            res = db.rawQuery("Select * from "+ tableName +" limit 1", null);

            int colIndex = res.getColumnIndex(fieldName);
            if (colIndex!=-1){
                isExist = true;
            }

        } catch (Exception e) {
        } finally {
            try { if (res !=null){ res.close(); } } catch (Exception e1) {}
        }

        return isExist;
    }

    public boolean insertPassword( String company, String password,String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("id", id);
        contentValues.put("company", company);
        contentValues.put("password", password);
        contentValues.put("user_id", user_id);
        //Log.d("USER_ID: (DBHELPER) ",user_id);
        //contentValues.put("key", key);
        db.insert("passwords","null", contentValues);
        if (contentValues != null) {
            //Log.d("insertPsswd(DBHELPER)",Boolean.TRUE.toString());
            //Log.d("insertPsswd(DBHELPER)",contentValues.getAsString("user_id"));
            return true;
        } else { return false;}//Log.d("insertPsswd(DBHELPER)",Boolean.FALSE.toString());return false;}
    }
    public boolean insertUserID(String company, String user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("company", company);
        contentValues.put("user_id", user_id);

        //db.insert("passwords","null", contentValues);
        db.update("passwords", contentValues, "company = ? ", new String[] { company } );
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
                            COLUMN_COMPANY, COLUMN_PASSWORD,COLUMN_USER_ID}, COLUMN_COMPANY + "=?",
                    new String[]{company/*String.valueOf(company)*/}, null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();


            password = new Password(cursor.getString(0), cursor.getString(1),cursor.getString(2));
            //password = new Password(cursor.getString(0), cursor.getString(1));


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
                password.setUserID(result.getString(2));
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
    public String getDatabasePath(){
        SQLiteDatabase db = this.getWritableDatabase();
        String path =  db.getPath();
        return  path;
    }

}
