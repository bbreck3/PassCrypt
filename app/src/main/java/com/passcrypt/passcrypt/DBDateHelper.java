package com.passcrypt.passcrypt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBDateHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "date_db.db";
    private static final String TABLE_NAME = "dates";
    private static final String COLUMN_DATE = "date";


    public DBDateHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_DATE + " text)"
        );
    }
    public void createDateBase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(
                "create table " + TABLE_NAME + " (" + COLUMN_DATE + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public Date getLastDate(){
        SQLiteDatabase db = this.getReadableDatabase();
        //Date date = null;
        Date theDate=null;
        String dateStr =null;
        try{
            //String sql = "SELECT * FROM passwords WHERE COLUMN = date";
            Cursor cursor = db.query(TABLE_NAME, new String[]{
                            null},null,
                    new String[]{}, null, null, null, null);

            if (cursor != null){
                Log.d("DEBUG -->", "asdf");
                Log.d("Get Collumn count: ", Integer.toString(cursor.getColumnCount()));
                cursor.moveToFirst();

                Log.d("DEBUG -->", "ghjhjk");
            }
            Log.d("DEBUG -->", "qwerty");
            dateStr = cursor.getString(0);
            Log.d("DEBUG -->", "zxvc");

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            df.format("dd/MM/yyyy");
            theDate = df.parse(dateStr);


        }catch(Exception e){
            Log.d("THe Date is NULLL","WHAAAAAAA?!?!?!");
        }
        return theDate;
    }
    public boolean setDate (  Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(date);
        contentValues.put("date", currentDate);
        Log.d("DEBUG --> theDate", currentDate);
        //contentValues.put("password", password);
        /*String sql = "UPDATE passwords SET date=" +currentDate +" WHERE date = NULL";
        db.execSQL(sql);*/
        //db.update("passwords", contentValues, "date = ?", new String[] { "date" } );

        if (contentValues!= null){

            return true;
        }
        else{
            db.close();

            return false;
        }
    }
}
